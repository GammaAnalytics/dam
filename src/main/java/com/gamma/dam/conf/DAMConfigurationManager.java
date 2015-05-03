package com.gamma.dam.conf;

import com.gamma.dam.conf.db.DBConfig;
import com.gamma.dam.conf.db.DBConfigReader;

import java.util.Map;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class DAMConfigurationManager {

    private static DAMConfigurationManager manager;
    private DBConfigReader dbReader;

    public static synchronized DAMConfigurationManager instance(){
        if(manager == null){
            manager = new DAMConfigurationManager();
        }
        return manager;
    }

    private DAMConfigurationManager(){
        dbReader = DBConfigReader.instance();
    }

    public Map<String, DBConfig> getDBConfigs() {
        return dbReader.getDBConfigs();
    }
}
