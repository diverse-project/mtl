/*
 * Created on 26 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

import org.irisa.triskell.MT.repository.API.Java.API;

/**
 * @author jpthibau
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BMTLRepositoryAPIInterface extends API,BMTLOclAnyInterface {

	API getRepositoryAPIDelegate();
	
	BMTLStringInterface BMTL_oclUid();
	
	BMTLOclTypeInterface BMTL_getType();

}
