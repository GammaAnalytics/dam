package com.gamma.dam.db;

import com.gamma.dam.conf.DAMConfigurationManager;
import com.gamma.dam.conf.db.DBConfig;
import com.jolbox.bonecp.BoneCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class MySQLDbConnectionPool {

    private BoneCPDataSource connectionPool;
    private static MySQLDbConnectionPool mdbc;
    private static DAMConfigurationManager manager = DAMConfigurationManager.instance();
    public synchronized static MySQLDbConnectionPool instance() {
        if (mdbc == null){
            try {
                mdbc = new MySQLDbConnectionPool();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return mdbc;
    }

    private MySQLDbConnectionPool() throws ClassNotFoundException {
        connectionPool = boneCPDataSource();
    }

    public BoneCPDataSource boneCPDataSource() throws ClassNotFoundException {
        String userName, password, URL;
        DBConfig config = manager.getDBConfigs().get("mysql");
        String dbHost = config.getHost();
        String dbPort = config.getPort();
        String db = config.getDatabase();
        userName = config.getUsername();
        password = config.getPassword();
        Class.forName(config.getDriver());
        URL = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + db;

        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(config.getDriver());
        boneCPDataSource.setJdbcUrl(URL);
        boneCPDataSource.setUsername(userName);
        boneCPDataSource.setPassword(password);
        boneCPDataSource.setDefaultAutoCommit(false);
        boneCPDataSource.setIdleMaxAgeInMinutes(30);
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(Long.parseLong(config.getIdleConnectionTestPeriodInMinutes()));
        boneCPDataSource.setIdleMaxAgeInMinutes(Long.parseLong(config.getIdleMaxAgeInMinutes()));
        boneCPDataSource.setMaxConnectionsPerPartition(Integer.parseInt(config.getMaxConnectionsPerPartition()));
        boneCPDataSource.setMinConnectionsPerPartition(Integer.parseInt(config.getMinConnectionsPerPartition()));
        boneCPDataSource.setPartitionCount(Integer.parseInt(config.getPartitionCount()));
        boneCPDataSource.setAcquireIncrement(Integer.parseInt(config.getAcquireIncrement()));
        boneCPDataSource.setStatementsCacheSize(Integer.parseInt(config.getStatementsCacheSize()));

        return boneCPDataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
}
