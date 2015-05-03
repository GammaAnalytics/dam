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

import org.apache.commons.configuration.tree.ConfigurationNode;

/**
 * The Class DBConfig.
 *
 * @author abhijit
 */
public class DBConfig {

	/** The driver. */
	private String host, port, database, username, password, driver;
    private String idleMaxAgeInMinutes, idleConnectionTestPeriodInMinutes,maxConnectionsPerPartition;
    private String minConnectionsPerPartition, partitionCount, acquireIncrement, statementsCacheSize;
	
	/**
	 * Parses the.
	 *
	 * @param node the node
	 */
	public void parse(ConfigurationNode node) {
		if(node.getChildrenCount()>0){
			for(ConfigurationNode n : node.getChildren()){
				String name = n.getName();
				String val = (String) n.getValue();
				switch (name) {
				case "host":
					setHost(val);
					break;
				case "port":
					setPort(val);
					break;
				case "database":	
					setDatabase(val);
					break;
				case "username":	
					setUsername(val);
					break;
				case "password":	
					setPassword(val);
					break;
				case "driver":	
					setDriver(val);
					break;
                case "idleMaxAgeInMinutes":
                    setIdleMaxAgeInMinutes(val);
                    break;
                case "idleConnectionTestPeriodInMinutes":
                    setIdleConnectionTestPeriodInMinutes(val);
                    break;
                case "maxConnectionsPerPartition":
                    setMaxConnectionsPerPartition(val);
                    break;
                case "minConnectionsPerPartition":
                    setMinConnectionsPerPartition(val);
                    break;
                case "partitionCount":
                    setPartitionCount(val);
                    break;
                case "acquireIncrement":
                    setAcquireIncrement(val);
                    break;
                case "statementsCacheSize":
                    setStatementsCacheSize(val);
                    break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Sets the database.
	 *
	 * @param database the new database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

    public String getIdleMaxAgeInMinutes() {
        return idleMaxAgeInMinutes;
    }

    public void setIdleMaxAgeInMinutes(String idleMaxAgeInMinutes) {
        this.idleMaxAgeInMinutes = idleMaxAgeInMinutes;
    }

    public String getIdleConnectionTestPeriodInMinutes() {
        return idleConnectionTestPeriodInMinutes;
    }

    public void setIdleConnectionTestPeriodInMinutes(String idleConnectionTestPeriodInMinutes) {
        this.idleConnectionTestPeriodInMinutes = idleConnectionTestPeriodInMinutes;
    }

    public String getMaxConnectionsPerPartition() {
        return maxConnectionsPerPartition;
    }

    public void setMaxConnectionsPerPartition(String maxConnectionsPerPartition) {
        this.maxConnectionsPerPartition = maxConnectionsPerPartition;
    }

    public String getMinConnectionsPerPartition() {
        return minConnectionsPerPartition;
    }

    public void setMinConnectionsPerPartition(String minConnectionsPerPartition) {
        this.minConnectionsPerPartition = minConnectionsPerPartition;
    }

    public String getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(String partitionCount) {
        this.partitionCount = partitionCount;
    }

    public String getAcquireIncrement() {
        return acquireIncrement;
    }

    public void setAcquireIncrement(String acquireIncrement) {
        this.acquireIncrement = acquireIncrement;
    }

    public String getStatementsCacheSize() {
        return statementsCacheSize;
    }

    public void setStatementsCacheSize(String statementsCacheSize) {
        this.statementsCacheSize = statementsCacheSize;
    }
}
