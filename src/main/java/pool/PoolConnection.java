package pool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.PoolException;
import util.Validator;

public enum PoolConnection {

	INSANCE;

	private final static Logger LOGGER = LogManager.getLogger(PoolConnection.class.getName());
	
	private final static String DB_PROPERTIES_FILE_NAME = "db.properties";	
	private final static String DB_PROPERTY_DRIVER = "driver";
	private final static String DB_PROPERTY_URL = "url";
	private final static String POOL_PROPERTIES_FILE_NAME = "pool.properties";
	private final static String POOL_PROPERTIES_CAPASITY = "capasity";
	private Properties dbInfo;
	private Properties poolInfo;
	private int capasityPool;

	private AtomicBoolean isInitPool = new AtomicBoolean(false);
	private BlockingQueue<Connection> availableConnection;
	private Queue<Connection> blockedConnection;

	public synchronized void initialization() throws PoolException {
		if (isInitPool.get()) {
			return;
		}
		
		initDBProperties();
		initPoolProperties();
		
		capasityPool = Integer.valueOf(poolInfo.getProperty(POOL_PROPERTIES_CAPASITY));
		
		availableConnection = new LinkedBlockingQueue<Connection>(capasityPool);
		blockedConnection = new ArrayDeque<Connection>();		
		
		try {
			Class.forName(dbInfo.getProperty(DB_PROPERTY_DRIVER));
			Connection connection;
			for (int i = 0; i < capasityPool; i++) {
				connection = DriverManager.getConnection(dbInfo.getProperty(DB_PROPERTY_URL), dbInfo);
				availableConnection.add(new ProxyConnection(connection));
			}
		} catch (SQLException e) {
			LOGGER.error("Error_init_pool_connection_sql_error");
			throw new PoolException("Error_init_pool_connection_sql_error", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error_init_pool_connection_sql_driver_error");
			throw new PoolException("Error_init_pool_connection_sql_driver_error", e);
		}
		
		isInitPool.set(true);
	}
	
	public Connection getConnection() throws PoolException {
		validateIsInitPool();
		
		Connection connection;
		try {
			connection = availableConnection.take();
		} catch (InterruptedException e) {
			LOGGER.error("Error_not_received_connection_from_pool");
			throw new PoolException("Error_not_received_connection_from_pool");
		}
		
		blockedConnection.add(connection);
		
		return connection;
	}
	
	public boolean releaseConnection(Connection connection) throws PoolException {
		validateIsInitPool();
		
		if (blockedConnection.remove(connection)) {
			if (availableConnection.add(connection)) {
				return true;
			}
		}
		LOGGER.error("Error_connection_not_release");
		throw new PoolException("Error_connection_not_release");		
	}
	
	public void closePool() throws PoolException, InterruptedException {
		validateIsInitPool();
		
		ProxyConnection proxyConnection;
		for (int i = 0; i < capasityPool; i++) {
			proxyConnection = (ProxyConnection) availableConnection.take();
			try {
				proxyConnection.closeInPool();
			} catch (SQLException e) {
				LOGGER.error("Error_close_pool_connection_sql_error");
				throw new PoolException("Error_close_pool_connection_sql_error", e);
			}
		}
	}
	
	private void validateIsInitPool() throws PoolException {
		if (!isInitPool.get()) {
			LOGGER.error("Error_pool_connection_not_initialized");
			throw new PoolException("Error_pool_connection_not_initialized");
		}
	}

	private void initDBProperties() throws PoolException {
		dbInfo = new Properties();
		try {
			dbInfo.load(
					new FileReader(this.getClass().getClassLoader().getResource(DB_PROPERTIES_FILE_NAME).getFile()));
		} catch (IOException e) {
			LOGGER.error("Error_DB_properties_file");
			throw new PoolException("Error_DB_properties_file", e);
		}
		if (!Validator.isValidPropertyDB(dbInfo)) {
			LOGGER.error("Error_content_DB_properties_file");
			throw new PoolException("Error_content_DB_properties_file");
		}
	}

	private void initPoolProperties() throws PoolException {
		poolInfo = new Properties();
		try {
			poolInfo.load(
					new FileReader(this.getClass().getClassLoader().getResource(POOL_PROPERTIES_FILE_NAME).getFile()));
		} catch (IOException e) {
			LOGGER.error("Error_pool_properties_file");
			throw new PoolException("Error_pool_properties_file", e);
		}
		if (!Validator.isValidPropertyPool(poolInfo)) {
			LOGGER.error("Error_content_pool_properties_file");
			throw new PoolException("Error_content_pool_properties_file");
		}
	}
	
	public void print() {
		System.out.println("avail " + availableConnection.size());
		System.out.println("block " + blockedConnection.size());
	}

}
