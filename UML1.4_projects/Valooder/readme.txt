$ID$

The purpose of this project is to proceed the Valooder transformation
that transforms a UML model into a simulable UML model in the following 
sense : the original model is transformed into a model that fits the
Valood framework; from this modified model, Eiffel code generation is
done to provide an API usable by the CADP/Simulator tool in order to
simulate the system described by the original model. It is also usable
by TGV in order to generate tests.

Actually, the transformation is already done in the Umlaut tool and is
written in the Eiffel language. 

The goal of this project is then to use the MTL transformation language 
in order to proceed the Valooder transformation.

The MTL transformation itself should keep the same strategy as before,
i.e. generating Eiffel code for providing the needed API by TGV.

So, one can see this project as a demonstration of the strength of the
model transformation through the MTL language in order to provide a
tool that helps to generate tests.
