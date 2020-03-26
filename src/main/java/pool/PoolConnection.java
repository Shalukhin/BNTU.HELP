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
			LOGGER.error("Error init pool connection sql error");
			throw new PoolException("Error init pool connection sql error", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error init pool connection sql driver error");
			throw new PoolException("Error init pool connection sql driver error", e);
		}
		
		isInitPool.set(true);
	}
	
	public Connection getConnection() throws PoolException {
		validateIsInitPool();
		
		Connection connection;
		try {
			connection = availableConnection.take();
		} catch (InterruptedException e) {
			LOGGER.error("Error not received connection from pool");
			throw new PoolException("Error not received connection from pool");
		}
		
		blockedConnection.add(connection);
		
		return connection;
	}
	
	public boolean releaseConnection(Connection connection) throws PoolException {
		validateIsInitPool();
		
		if (blockedConnection.remove(connection) && availableConnection.add(connection)) {
			return true;
		}
		LOGGER.error("Error connection not release");
		throw new PoolException("Error connection not release");		
	}
	
	public void closePool() throws PoolException, InterruptedException {
		validateIsInitPool();
		
		ProxyConnection proxyConnection;
		for (int i = 0; i < capasityPool; i++) {
			proxyConnection = (ProxyConnection) availableConnection.take();
			try {
				proxyConnection.closeInPool();
			} catch (SQLException e) {
				LOGGER.error("Error close pool connection sql error");
				throw new PoolException("Error close pool connection sql error", e);
			}
		}
	}
	
	private void validateIsInitPool() throws PoolException {
		if (!isInitPool.get()) {
			LOGGER.error("Error pool connection not initialized");
			throw new PoolException("Error pool connection not initialized");
		}
	}

	private void initDBProperties() throws PoolException {
		dbInfo = new Properties();
		try {
			dbInfo.load(new FileReader(this.getClass().getClassLoader().getResource(DB_PROPERTIES_FILE_NAME).getFile()));
		} catch (IOException e) {
			LOGGER.error("Error DB properties file", e);
			throw new PoolException("Error DB properties file", e);
		}
		if (!Validator.isValidPropertyDB(dbInfo)) {
			LOGGER.error("Error content DB properties file");
			throw new PoolException("Error content DB properties file");
		}
	}

	private void initPoolProperties() throws PoolException {
		poolInfo = new Properties();
		try {
			poolInfo.load(new FileReader(this.getClass().getClassLoader().getResource(POOL_PROPERTIES_FILE_NAME).getFile()));
		} catch (IOException e) {
			LOGGER.error("Error pool properties file");
			throw new PoolException("Error pool properties file", e);
		}
		if (!Validator.isValidPropertyPool(poolInfo)) {
			LOGGER.error("Error content pool properties file");
			throw new PoolException("Error content pool properties file");
		}
	}	
	
}
