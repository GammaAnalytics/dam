/*
 * *********************************************************************************************
 * Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dam.db.mysql.helper;

import com.gamma.dam.db.MySQLDbConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*import com.gamma.portal.db.DatasourcePool;*/


/**
 * The Class MySqlQueryExecuter.
 * 
 * @author abhijit
 */
public class MySqlQueryExecuter {
	static MySQLDbConnectionPool pool = MySQLDbConnectionPool.instance();
	
	public static List<Map<String, Object>> executeSelectStatement(String query, boolean remote) {
		List<Map<String, Object>> result = new LinkedList<>();
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			meta = rs.getMetaData();
			Map<String, String> colHeaders = getColumnHeader(meta);
			while (rs.next()) {
				Map<String, Object> r = new HashMap<>();
				for (String col : colHeaders.keySet()) {
					if (colHeaders.get(col).toLowerCase().endsWith("string")) {
						r.put(col, rs.getString(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("integer")) {
						r.put(col, rs.getInt(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("float")) {
						r.put(col, rs.getFloat(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("double")) {
						r.put(col, rs.getDouble(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("boolean")) {
						r.put(col, rs.getBoolean(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("date") ||colHeaders.get(col).toLowerCase().endsWith("timestamp")) {
						r.put(col, rs.getTimestamp(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("long")) {
						r.put(col, rs.getLong(col));
					} else if (colHeaders.get(col).toLowerCase().endsWith("bigdecimal")) {
						r.put(col, rs.getBigDecimal(col));
					}
				}
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @return
	 * @throws java.sql.SQLException
	 */
	private static Map<String, String> getColumnHeader(ResultSetMetaData meta) throws SQLException {
		Map<String, String> columns = new HashMap<>();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columns.put(meta.getColumnName(i), meta.getColumnClassName(i));
		}
		return columns;
	}

	public static void upsert(String query, boolean remote) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete(String query, boolean remote) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
