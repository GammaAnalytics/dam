/*
 * **********************************************************************************************
 *  Copyright (C) 2013 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dam.conf.db;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.gamma.dam.conf.ResourceProperties;
import com.gamma.dam.exceptions.DAMConfigurationException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.apache.commons.configuration.tree.ConfigurationNode;


/**
 * The Class DBConfigReader.
 * 
 * @author abhijit
 */
public class DBConfigReader {
	
	/** The reader. */
	private static DBConfigReader reader;
	
	/** The dbs. */
	private Map<String, DBConfig> dbs = new HashMap<>();

	/**
	 * Instance.
	 *
	 * @return the DB config reader
	 */
	public static synchronized DBConfigReader instance(){
		if(reader == null){
			reader = new DBConfigReader();
		}
		return reader;
	}
	
	/**
	 * Instantiates a new DB config reader.
	 */
	private DBConfigReader(){
		String basePath = ResourceProperties.instance().getValue(ResourceProperties.DATABASE_CONF);
		String home = System.getProperty("DAM_HOME");
		if(home != null){
			basePath = home + File.separator +basePath;
		}
		try{
			XMLConfiguration xml = new XMLConfiguration(basePath);
			ReloadingStrategy strategy = new FileChangedReloadingStrategy(); 
			xml.setReloadingStrategy(strategy);
			Node node = xml.getRoot();
			if(node.hasChildren()){
				for(ConfigurationNode n : node.getChildren()){
					DBConfig db = new DBConfig();
					String name = null;
					if(n.getAttributeCount()>0){
						for(ConfigurationNode attr : n.getAttributes()){
							switch (attr.getName()) {
							case "id":
								
								break;
							case "name":
								name = (String) attr.getValue();
								break;

							default:
								break;
							}
						}
					}
					
					db.parse(n);
					dbs.put(name, db);
				}
			}
		}catch(ConfigurationException e){
			throw new DAMConfigurationException(e);
		}
	}

	/**
	 * Gets the DB configs.
	 *
	 * @return the DB configs
	 */
	public Map<String, DBConfig> getDBConfigs() {
		return dbs;
	}
	
	public DBConfig getDataBaseInfo(String dbName){
		return dbs.get(dbName);
	}
	
	
	public Collection<DBConfig> getAllDataBases(){
		return dbs.values();
	}
}
