$Id: readme.txt,v 1.2 2004-02-18 11:02:48 edrezen Exp $
this projects aims to define transformations applicable to the MOF1.4 metamodel
It also stores versions of the MOF Metamodel

This purpose of this project is to define a view of the MOF.

Such a view can be seen as an abstract view of the MOF metamodel, with features to read, create,
update metamodels (more precisely M2 level models of the MOF M3 level metamodel).

In particular, abstract MTL classes are defined to reflect the MOF metaclasses with getters and
setters. For example, a MTL class ModelElement is defined as :

library MOFview;

class ModelElement
{
	getName ()             : Standard::String;
	getQualifiedName ()    : Standard::String;
	getAnnotation ()       : Standard::String;
	getContainer ()        : MOFview::Namespace;
	getRequiredElements () : Standard::Set;
	getConstraints ()      : Standard::Set;

	setName            (name : Standard::String);
	setQualifiedName   (qualifiedName : Standard::String);
	setAnnotation      (annotation : Standard::String);
	setContainer       (container : MOFview::Namespace);
	addRequiredElement (requiredElements : MOFview::ModelElement);
	addConstraint      (constraint : MOFview::Constraint);
}

This is abstract (in fact there is a 'null' implementation since MTL does not allow true
interfaces) like every other classes of the MOF view. 

Now, an implementation of the MOF view is needed; it is done by the MOF adapter view that 
maps the MOF abstract view into the real MOF 1.4. 

Another useful library is provided, called MOF helper, that holds methods to retrieve 
information about any MOF model.

As examples, some models (at the M2 level) of the MOF metamodels are given to test the features
of the MOF helper (including the M2 level MOF model itself).


The default example of the distribution (build.xml file) displays information retrieved from
the UML model (model of the MOF). You can change the analized model through the "model" property
used in the build.xml file.


Note : Actually, many setters methods are not yet implemented. When it will be done, it will 
be possible to modify existing metamodels through MTL programming.