This readme exposes some notes about how the ant files are generated. For the
use of the program, please run "python antimtl/Antimtl.py --help"

The following transformations are done : 

-----------------------------------------------------------------------------
.classpath to <lib>_build.xml :
-----------------------------------------------------------------------------
LIB
	<classpathentry  kind="lib" path="dir/of/yourfile.jar"/>
->  <property location="dir/of/yourfile.jar" name="yourfile.jar"/>

(included project)
	<classpathentry path="path/of/a/directory" kind="lib" path="/>
->  <property location="path/of/a/project/directory" name="name_of_the_project"/>

SRC
	<classpathentry kind="src" path="build/src"/>
->  <property name="build.src" location="build/src">

OUTPUT
    <classpathentry kind="output" path="build/bin">
->  <property name="build.bin" location="build/bin">


- The paths of all the tags having *kind="lib"* are then added as <pathelements> 
    to the element <path id="current.class.path"> :
    <pathelement path="${yourfile.jar}"/>

-----------------------------------------------------------------------------
.mtlclasspath to <lib>_build.xml :
-----------------------------------------------------------------------------

Note : The TLL location are expressed differently in .mtlclasspath and
  <lib>_build.xml : In the first case, we can either specify each .tll library file (absolute path) or only a directory. In
  the second one, only the path of the library files is specified (must appear
  only once in the TLL global path.

Transformations :

	<classpathentry kind="lib" path="/udd/zdrey/Eclipse/eclipse/plugins/org.inria.mtl_0.0.5/MTL/Runtime/src/TLL/ASTJava.tll"/>
-> we add "/udd/zdrey/Eclipse/eclipse/plugins/org.inria.mtl_0.0.5/MTL/Runtime/src/TLL/" to the Runtime.TLL.path. .mtlclasspath is completely parsed and each "new" directory that is encountered when parsing the .tll location paths is added to the path. Precisely, the directories are added (separated by a semicolon) in the property named Runtime.TLL.path.
    
	<classpathentry kind="src" path="src/Your_library_folder_name"/>
-> We create as many <Your_library_folder_name>_build as we encounter classpathentries with *kind="src"* (they specify the library folders contained in the project. So the name of the library is name "Your_library_folder_name"
    <property name="library.name" value="Your_library_folder_name"/>
    <project name="Your_library_folder_name"></project>


