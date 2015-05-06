/*
 * **********************************************************************************************
 *  Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dam.db.mysql;


import com.gamma.dam.db.DAOFactory;
import com.gamma.dam.db.wrapper.BaseDAO;

/**
 * A factory for creating MySqlDAO objects.
 * 
 * @author abhijit
 */
public class MySqlDAOFactory extends DAOFactory<BaseDAO> {

	private MySqlDAO dao;

	public MySqlDAOFactory() {
		dao = new MySqlDAOImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gamma.churn.dao.DAOFactory#getDAO()
	 */
	@Override
	public MySqlDAO getDAO() {
		// TODO Auto-generated method stub
		return dao;
	}

}
