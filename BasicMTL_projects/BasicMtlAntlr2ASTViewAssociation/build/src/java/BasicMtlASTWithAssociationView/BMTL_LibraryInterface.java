package BasicMtlASTWithAssociationView;


import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.*;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.*;
import org.irisa.triskell.MT.BasicMTL.TopTypes.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface BMTL_LibraryInterface extends BMTLObjectInterface, BasicMtlASTWithAssociationView.BMTL_ASTNodeInterface, BasicMtlASTView.BMTL_LibraryInterface
{
public BasicMtlASTWithAssociationView.BMTL_Library getRef_BMTL_BasicMtlASTWithAssociationView_5fLibrary();
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface BMTL_allKnownClasses(org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface libName);
public org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface BMTL_transformAllAssociations(BasicMtlASTWithAssociationView.BMTL_LibraryInterface BMTL_templatesLib) throws Throwable;
}
