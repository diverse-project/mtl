this example show the use of the View mechanism

a transformation use a SimpleUML view so it knows 
only SimpleUml concepts but by doing this it in 
fact transforms a SimpleRDBMS model

so the transformation can apply to both a simpleUml model or a SimpleRdbms model



Some raw ideas :
- implementing an observer may be a better idea : a set in an element may have impacts on other elements
	ex : setting the name of a primitive datatype do changes the attribute corresponding column "type" value
	so attribute may observe name changes on its type
	
perhaps simply implement an notifier that notifies all related elments (ie. linked through an association or an attribute)
=> pb in this case : to much notification to elements that doesn't care.