#!/usr/bin/env python
# $Id :$
# --------------------------------------------------@RisingSun//Python//1.0//EN
# Module			:	Antimtl.py
# Version			:	0.1
# Type				:	class and main method (single file project)
# License			:	LGPL
# Copyright			:	irisa
# -----------------------------------------------------------------------------
# Author			:	Zoe Drey
# -----------------------------------------------------------------------------
# Description		: This program generate ant files using quite only DOM
#                     implementation.
# Creation date		:	15-Jan-2005
# Last mod.			:
# NOTES : 
#    - Use XPath (with libxml2) instead of DOM implementation methods?
#

__doc__="""\
Generate multiple ant files in order to compile and run a MTL project outside Eclipse

Usage: antimtl/Antimtl.py 

Options:
    -h / --help
        Print this message and exit.

    --cp=your .classpath location
        .classpath contains all the .jar files or class directories to include
        for the compilation of the generated .java
    --mtlcp=your .mtlclasspath location
        .mtlclasspath contains all the .tll files or library directories to
        include for the compilation of the .mtl files
    --output=the output directory where to place the generated files

    --demo : use on of the examples in ./samples/ directory; this option overrides the
    others.
"""

__version__ = "0.1"

# in an __init__ file?
import sys, getopt, string, re, os
import xml.dom.minidom
import xml

class DOMAntimtl :
    """
    Ant file generator for a MTL project.

    DOM NOTE : we chose this method because we have to modify specific parts of an
    existing XML file. Default : cannot access a specific property by its attributes
    (namely "name")

    The following files must already exist, located in *templates/* directory :

    - common_build.xml : the common_build.xml file not modified (does not
      change through the different MTL projects) 
    - tll_build.template.xml : a template for the specific <lib>_build.xml file
    - build.template.xml : a template for the main build.xml file
    
    The following files are generated :

    - the common_build.xml (in fact, it is simply copied in the output directory as
      the other *_build.xml file
    - the main_build.xml main file
    - a <lib>_build.xml for each MTL library (seen also as a folder)
 
    Some notes : 
    - common_build.xml uses the property definitions that are contained in
      build.xml
    - the template tll_build.template.xml already contains the correct targets
      (we don't have to modify them, provided "common_build.xml" is in current
      directory (TODO : common_build in any directory of the project?)
    """

    # the path name where the .jar needed for the compilation .mtl
    COMPILERJARPATH = "MTLcompiler.jar.path"
    
    def __init__(self, ftemplate, inbuild, inclasspath, inmtlclasspath, 
    output=""):
        """
            ftemplate : the template build for each MTL library
            inclasspath : the .classpath template
            inmtlclasspath : the .mtlclasspath template
            inbuild : the template for the global build
        """
        self.outputpath = os.path.normpath(output)
        self.incp = os.path.normpath(inclasspath)
        self.inmtlcp = os.path.normpath(inmtlclasspath)
        self.ftemplate = os.path.normpath(ftemplate)
        self.lib_list = []
        
        self.ftemplate_dom = None
        self.dom_mtlclasspath = self.initDom(self.inmtlcp)
        self.dom_classpath = self.initDom(self.incp)
        self.inbuild = os.path.normpath(inbuild)
        self.BASICMTL_BIN = ""
        
    def run(self):
        """
        The main method that calls all the others to generate the ant files
        Get info from classpath, mtlclasspath, and an environment variable 
        () to construct the following ant files :
            - <lib[i]>_build.xml for each mtl library lib[i] defined in the
              project
        """
        # Get the libs for which we must create a lib_build.xml file
        self.lib_list = self.getProjectLibs()
        for lib in self.lib_list :
            # Read the lib_build.xml template and 
            # initialize the dom for the build (infp)
            self.initOutput()
        
            lib_name = lib.split("/")[-1]
            # The build filename
            ofname = lib_name+"_build.xml"
            # Set the common properties
            self.process_classpath()   
         
            # Set the ant targets (only common_build to add)
            self.setTargets(lib_name, "common_build.xml", " common")

            # Set the user.needed.TLL.path property and others
            tll_path_str = self.process_mtlclasspath()
            self.setProperty("Runtime.TLL.path", tll_path_str)
            self.setProperty("library.name", lib_name, type="value")
            self.setProperty("user.src.path", lib, type="location")

            # Set the compiler.jar.path that allow to access the basic jars for
            # the compilation (and log display) of the .mtl
            self.setMTLcompilerJarPathProperty()
        
            # Create the <lib>_build.xml output file 
            self.finishOutput(ofname, self.ftemplate_dom)

        self.createGlobalBuild()


    def setBasicMTLBin(self, env_var):
        """This method set the directory where we can find the .jars necessary for the compilation and
        the execution of BasicMTL programs. It answers to the problem of access/setting an environment
        variable (os.environ['BASICMTL_BIN'] returns a key error when executing Antimtl through
        MTL plugin)
        """
        self.BASICMTL_BIN = env_var
        
    
    def initOutput(self):
        """Initialize the output dom for an ant build output file"""
        self.ftemplate_dom = self.initDom(self.ftemplate)

    def initDom(self, filename):
        """
        Create a DOM file from filename (XML file)
        """
        import os
       
        xml_dom_object = xml.dom.minidom.parse(os.path.abspath(filename))
        return xml_dom_object 

    def setTargets(self, lib, build_file, keyword=""):
        """Set the ant <target name="name">
            The following method would be much faster if targets had an ID
            attribute...
            FIXME : hard coded values
        """
        index = 0
        new,t = None, None
   
        # set the attr. name of tag <project> 
        self.ftemplate_dom.documentElement.setAttribute("name", lib)
        self.ftemplate_dom.documentElement.setAttribute("default",
        "compile %s"%lib)
        
        for tname in ("init","clean", "compile", "run"):
            
            targets = self.ftemplate_dom.getElementsByTagName("target")
            # if target already exists (cond. to be deprecated)
            if len(targets) > 0 :
                t = self.getTarget(tname)
            if t is None :
                t = self.ftemplate_dom.createElement("target")
            t.setAttribute("name", tname+" "+lib)
            # add an <ant> element
            new = self.setAntfile(tname, build_file, keyword)
            t.appendChild(new)
            self.ftemplate_dom.documentElement.appendChild(t)

    def getTarget(self, target_name):
        """
        Get the target defined by target_name (name attribute)
        """
        ts = self.ftemplate_dom.getElementsByTagName("target")
        t = None
        index = 0
        if len(ts) > 0:
            while index < len(ts) and t is None:
                if ts[index].getAttribute("name")==target_name:
                    t = ts[index]
                index+=1
        return t
        

    def setAntfile(self, tname, dep_build, keyword_build=""):
        """
        Params 
            tname : target name (init, clean, etc)
            dep_build : build file dependency
            keyword_build : the dep_build target postfix
        """
        new = self.ftemplate_dom.createElement("ant")
        new.setAttribute("antfile", dep_build)
        new.setAttribute("dir", ".")
        new.setAttribute("target", tname+keyword_build)
        if tname in ("run","compile"):
            new.setAttribute("inheritrefs", "true")

        # compile needs a 'depends' attribute FIXME here
#        if tname == "compile" and keyword_build==" common": 
#            new.setAttribute("depends", "init"+keyword_build)
        return new

    def createProperty(self, name, type_val, type="location"):
        """Set the ant <target name="name">
            The following method would be much faster if targets had an ID
            attribute...
            FIXME : hard coded values
        """
        new = self.ftemplate_dom.createElement("property")
        new.setAttribute("name", name)
        new.setAttribute(type, type_val)
        return new

    def getProjectLibs(self):
        """Get in .mtlclasspath the <classpathentry> tags which attribute kind=
        "src. They
        correspond to the libraries directories in the user's project, or
        the libraries directories that come from projects used by this
        project
           """
        classpathentries = self.dom_mtlclasspath.getElementsByTagName("classpathentry")
        lib_list = []
        for node in classpathentries:
            path = node.getAttribute("path")
            # is kind value "lib"?
            kind_val = node.getAttribute("kind")
            if kind_val == "src" :
                lib_list.append(node.getAttribute("path"))
        return lib_list

    def process_mtlclasspath(self):
        """Get in .mtlclasspath the Runtime.TLL.Path, and the other TLL
        libraries that could come from the inclusion of a project
            Returns : in a tuple : 
                - a string that is the TLL runtime path
                - a list that is the list of projects to compile
            (kind="lib")
        """
        classpathentries = self.dom_mtlclasspath.getElementsByTagName("classpathentry")
        path = ""
        
        # We can have many different paths where the tll come from. We set them
        # in user.needed.TLL.path, separating the diff. path by a semi-colon
        path_list, prj_path_list = [], []
        # We, for now, only consider the first node (all tll "should" FIXME be
        # in the same directory)
        index = 0;
        properties = self.ftemplate_dom.getElementsByTagName("property")
        while index < len(classpathentries):
            node = classpathentries[index]
            path = node.getAttribute("path")
            kind_val = node.getAttribute("kind")
            # is kind value "lib"?
            if kind_val == "lib" :
                path_split = path[:path.rfind("/")-len(path)]
                if (path_split not in path_list):
                    path_list.append(path_split)
#            elif node.getAttribute("kind") == "prj":
#                prj_path_list.append(node.getAttribute("path"))
            # in mtlclasspath we find also those kinds    
            elif (kind_val == "output") :
                name = "build.src"
                self.setProperty(name, path, properties)
            elif (kind_val == "output_tll") :
                name = "build.tll"
                self.setProperty(name, path, properties)
            index+=1
        # note : if there is only one elt in path_list, no semi-colon is added.
        return ";".join(path_list)

    def generatePathName(self, path):
        """ path can be the complete path of a .jar, or the path of a directory
        that contains .classes. For the latter case, we have to find a unique
        name, which is not composed only of the last part of the path
        """
        if (path.endswith(".jar")):
            name = path.split("/")[-1] # assume that path contains "/"!!
        elif (path.endswith("build/bin")):
            name = path.split("/")[-3]
        else : # do not try to get a raccourci
            name = path
        return name
        

    def process_classpath(self):
        """Get information from .classpath to get the jars needed for java
           compilation. Typically, we have the following lines :
           <classpathentry 
                sourcepath="/udd/zdrey/Eclipse/eclipse/plugins/org.inria.BasicMTL.MDRDriver_0.0.1/lib/MDRDriver.jar"
                kind="lib" >
           get the "kind" attribute, and the basename of the .jar, then create
           a node <property> telling its path
        """
        properties = self.ftemplate_dom.getElementsByTagName("property")
        
        # Get each jar path, and set <property> for it
        classpathentries = self.dom_classpath.getElementsByTagName("classpathentry")
        name = ""
        for node in classpathentries :
            
            path = node.getAttribute("path")
            # is kind value "lib"?
            kind_val = node.getAttribute("kind")
            if (kind_val == "lib"):
                name = self.generatePathName(path)
            #elif (kind_val == "src"): 
                # with lib_builds
            #    name = "build.src"
            # in mtlclasspath we find those kinds    
            elif (kind_val == "output") :
                name = "build.bin"

            # Special handling for tll path (fixme)
            #self.setProperty("build.tll", "build/tll", properties)
            # do not set a property for the kind "con"
            if kind_val in ("lib", "src", "output", "output_tll"):
                self.setProperty(name, path, properties)
            
            if (kind_val == "lib"):
                self.setCurrentClassPath(name)


    def setProperty(self, name, path, properties=None, type="location"):
        """
        properties : if not None, the reason could be that we access a list
        many times, so it is useful to have assigned this list previously
        """
        index = 0
        found_property = 0
        pnode = None
        if properties is None : 
            properties = self.ftemplate_dom.getElementsByTagName("property")
            
        while index < len(properties) and not found_property:
            pnode = properties[index]
            property_name = pnode.getAttribute("name")
            if property_name == name:
                found_property = 1
            index+=1
        # If property exists in template, we only change its attr.
        # Otherwise we create a specific node.
        if found_property == 0:
            pnode = self.createProperty(name, path)
            self.ftemplate_dom.documentElement.appendChild(pnode)
        else:
            pnode.setAttribute(type, path)

    def setCurrentClassPath(self, property_name):
        """Set the <path> tag, defined by the id 'current.class.path'"""
        # Does not work for reasons I ignore (such an element though exists!)
#        node = self.ftemplate_dom.getElementById("current.class.path")
        # There should be only one <path> tag
        node = self.ftemplate_dom.getElementsByTagName("path")[0]
        new = self.ftemplate_dom.createElement("pathelement")
        new.setAttribute("path", "${%s}"%property_name)
        
        node.appendChild(new)
            
 
    def createGlobalBuild(self):
        """Create the main build.xml for which we call ant"""
        # the DOM of build.xml
        dom_build = self.initDom(self.inbuild)

        targets = dom_build.getElementsByTagName("target")
        # TODO : handle the case where the expected target does not exist
        index = 0
        new = None
        while index<len(targets):
            t = targets[index]
            tname = t.getAttribute("name")
            if tname in ["init", "clean","compile"]:
                # init for the lib builds
                for lib in self.lib_list :
                    lib_name = lib.split("/")[-1] 
                    new = dom_build.createElement("ant")
                    new.setAttribute("antfile", lib_name+"_build.xml")
                    new.setAttribute("dir", ".")
                    new.setAttribute("target", tname+" "+lib_name)
                    t.appendChild(new)
            if tname == "run":
                # last lib is assumed to be the main lib to execute
                new = dom_build.createElement("ant")
                new.setAttribute("antfile", self.lib_list[-1].split("/")[-1]+"_build.xml")
                new.setAttribute("dir", ".")
                new.setAttribute("target", tname+" "+self.lib_list[-1].split("/")[-1])
                t.appendChild(new)

            index+=1
#            dom_build.documentElement.appendChild(t)

        self.finishOutput( "main_build.xml", dom_build)

    def setMTLcompilerJarPathProperty(self):
        """We set here a specific property, that allows to access to the .jar
        that are necessary for the MTL compilation phase of the .mtl libraries"""
        try:
            self.setProperty(self.COMPILERJARPATH, os.environ['BASICMTL_BIN'])
        except KeyError:
            print "Could not find BASICMTL_BIN env var,",\
             "trying to use the var given in the command execution"
            self.setProperty(self.COMPILERJARPATH, self.BASICMTL_BIN)


    def finishOutput(self, ofilename, doc):
        """
        Close properly the input files and convert the dom document *doc* to text,
        and write it in *ofilename*
           Params :
                ofilename : the name of the build file
        """
        f = open(self.outputpath+"/"+ofilename, "w+")
        doc.writexml(f)
        f.close()


def usage(code, msg=''):
    print >> sys.stderr, __doc__
    if msg:
        print >> sys.stderr, msg
    sys.exit(code)

def main():

    import getopt, os
    
    try:
        opts, args = getopt.getopt(sys.argv[1:], "hdt:c:m:o:j:", ["help","demo",
        "templates_path=","cp=","mtlcp=","output=", "jardir="])
    except getopt.GetoptError:
        # print help information and exit:
        usage(2)
        sys.exit(2)

    
    # The path where we can find the build templates (set to current by default)
    templates_path = "."
    output = "."
    mtlcp, cp = None, None
    demo = False
    # boolean that is set to true if we can process our generation:)
    can_process = 0
    var = ""
    for o, value in opts:
        if o in ("-h", "--help"):
            usage(1)
            sys.exit(1)
        if o == "--templates_path":
            templates_path = value
        if o == "--cp":
            cp = value
        if o == "--mtlcp":
            mtlcp = value
        if o == "--output":
            output = value
        if o == "--demo": # use demo files
            demo = True
        if o == "--jardir":
            var = value

    if opts == []:
        usage(1)
        sys.exit(1)

    if demo == True :
        print "Use demo samples"
        cp = "samples/example-2/classpath"
        mtlcp = "samples/example-2/mtlclasspath"

    elif mtlcp is not None and cp is not None:

        if os.path.isfile(mtlcp) and os.path.isfile(cp):
            can_process = 1
        else:
            print >> sys.stderr, "The given files do not exist"
            print >> sys.stdout, "The given files do not exist"
            sys.exit(2)
        
    else :
        can_process = 1

    if can_process == 1:
        
        commonfile = open(os.path.abspath(os.path.join(templates_path,"common_build.xml")))
        data = commonfile.read()
        commonfile.close()

        print "Creating/Overriding the common_build.xml..."
        newfile = open(os.path.abspath(os.path.join(output, "common_build.xml")),"w+")
        newfile.write(data)
        newfile.close()
        print "Done"

        tll_build = templates_path+"/tll_build.template.xml"
        build = templates_path+"/build.template.xml"
  
        print >> sys.stdout, "Look for .mtlclasspath and .classpath in %s"%output
        print >> sys.stdout, "Look for templates of ant files in %s"%templates_path
        processor = DOMAntimtl(tll_build, build, output+"/.classpath", output+"/.mtlclasspath", output)
        # dirty ?
        processor.setBasicMTLBin(var)
        processor.run()
        
        
        
if __name__ == "__main__":
    main()
