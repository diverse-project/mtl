$Id: readme.txt,v 1.1 2004-06-22 17:04:54 edrezen Exp $
Description :

Sample of a view that underlines the tree architecture of metamodels.
It mainly consists in a metaclass Container that owns other Containers.
A Container has a name and a role (a role provides a name for a level in 
the hierarchy of containers). At instanciation, a container is supposed to
be a root container that contains the whole hierarchy of containers.

There are several examples of adapters for this view. For example, the MOF
metamodel is adapted for the ContainmentView. At instanciation, the root MOF 
container holds the packages of the adapted MOF instance, each package holds
metaclasses and each metaclasses holds features (such as attributes, references,
operations...)

Other sample of adapters should follow (such as the SimpleFilesystem metamodel 
that is surely an example of hierarchycal metamodel)

The idea is to provide a set of commands that act on ContainmentView, regardless
of the underlying adapted metamodel. Examples of generic ContainmentView commands
are : 
	- ToString     : "nice" display of a ContainmentView content (ie with identation)
	- Find/Replace : find/replace command on containers name
	
It is also possible to imagine generic structural transformations on the container
hierachy of a ContainmentView (for this, the role is likely to be used). An example
could be a 'flatten' like transformation that will put at the same containment level 
all objects having the same role; for example in the UML adapter case, classes contained
in other classes would be put at an upper (one or more) containment level (note however
in this case the possible issue concerning visibility)


History :
---------
$Log: not supported by cvs2svn $

