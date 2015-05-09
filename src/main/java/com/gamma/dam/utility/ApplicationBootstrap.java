package com.gamma.dam.utility;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import java.util.Iterator;
import java.util.Properties;

public class ApplicationBootstrap {

	private Properties props;
	private boolean isAlreadyBootDone;
	private static ApplicationBootstrap applicationBootstrap;
	public static synchronized ApplicationBootstrap instance() {
		if(applicationBootstrap == null){
			applicationBootstrap = new ApplicationBootstrap();
		}
		return applicationBootstrap;
	}
	
	public ApplicationBootstrap(){
		try {
    		PropertiesConfiguration configuration = new PropertiesConfiguration("application.properties");
			configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
			props = new Properties();
			Iterator<String> itr =  configuration.getKeys();
			while (itr.hasNext()) {
				String key = (String) itr.next();
				props.put(key, configuration.getString(key));
			}
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
	}

	public void boot() {
		if(!isAlreadyBootDone){
			String home = props.getProperty("HOME_DIRECTORY");
			System.setProperty("DAM_HOME", home);
		}
	}
	
	public String getHomeDirectory(){
		return props.getProperty("HOME_DIRECTORY");
	}

}
