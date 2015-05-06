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

import com.gamma.dam.db.wrapper.BaseDAO;

import java.util.List;
import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * The Interface MySqlDAO.
 *
 * @author abhijit
 */
public interface MySqlDAO extends BaseDAO {
	
public void update(String query);
	
	public void update(String query, boolean remote);

	public List<Map<String, Object>> select(String query);
	
	
	public List<Map<String, Object>> select(String query, boolean remote);

	public void insert(String query);
	
	public void insert(String query, boolean remote);

	public void delete(String query);
	
	public void delete(String query, boolean remote);

}
