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

import com.gamma.dam.db.mysql.helper.MySqlQueryExecuter;

import java.util.List;
import java.util.Map;


/**
 * The Class MySqlDAOImpl.
 * 
 * @author abhijit
 */
public class MySqlDAOImpl implements MySqlDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gamma.churn.dao.mysql.MySqlDAO#update()
	 */
	@Override
	public void update(String query) {
		MySqlQueryExecuter.upsert(query, false);
	}
	
	@Override
	public void update(String query, boolean remote) {
		MySqlQueryExecuter.upsert(query, remote);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gamma.churn.dao.mysql.MySqlDAO#select(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> select(String query) {
		return MySqlQueryExecuter.executeSelectStatement(query, false);
	}
	
	public List<Map<String, Object>> select(String query, boolean remote){
		return MySqlQueryExecuter.executeSelectStatement(query, remote);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gamma.churn.dao.mysql.MySqlDAO#insert()
	 */
	@Override
	public void insert(String query) {
		MySqlQueryExecuter.upsert(query, false);
	}
	
	@Override
	public void insert(String query, boolean remote) {
		MySqlQueryExecuter.upsert(query, remote);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gamma.churn.dao.mysql.MySqlDAO#delete()
	 */
	@Override
	public void delete(String query) {
		MySqlQueryExecuter.delete(query, false);

	}
	
	@Override
	public void delete(String query, boolean remote) {
		MySqlQueryExecuter.delete(query, remote);

	}

}
