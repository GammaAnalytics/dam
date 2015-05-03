package com.gamma.dam.conf;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;


public class ResourceProperties {
	
	private static Properties props ;
	private static ResourceProperties conf;
	
	public static final String DATABASE_CONF = "DATABASE_CONF";
	public static final String ADAPTER_DIR = "ADAPTER_DIR";
	public static final String MAIL_CONF = "MAIL_CONF";
	public static final String CACHE_CONF = "CACHE_CONF";
    public static final String CONTEXT_PARAM_CONF = "CONTEXT_PARAM_CONF";
    public static final String REPORT_CONF = "REPORT_CONF";
	
	
	public synchronized static ResourceProperties instance(){
		if(conf == null){
			conf = new ResourceProperties();
        	try {
        		String path = "conf/resource.properties";
        		String home = System.getProperty("DAM_HOME");
        		if(home != null){
        			path = home + File.separator + path;
        		}
        		
        		PropertiesConfiguration configuration = new PropertiesConfiguration(path);
				configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
				props = new Properties();
				Iterator<String> itr =  configuration.getKeys();
				while (itr.hasNext()) {
					String key = itr.next();
					props.put(key, configuration.getString(key));
				}
			} catch (ConfigurationException e1) {
				e1.printStackTrace();
			}
		}
		return conf;
	}
	
	public String getValue(String key) {
        return props.getProperty(key);
    }
}
