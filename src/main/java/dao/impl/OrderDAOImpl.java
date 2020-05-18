package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.OrderBuilder;
import dao.FinishFileDAO;
import dao.OrderDAO;
import dao.TaskDAO;
import dao.UserDAO;
import dao.constant.DBColumnNameConstant;
import entity.Order;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class OrderDAOImpl implements OrderDAO{
	
	private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class.getName());
	
	private static final String SQL_REQUEST_CREATE_ORDER = "INSERT INTO `order` (`idUser`, `idTask`, `note`, `adjustedPriceTask`, `priceOrder`) "
															+ "VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_ORDER_BY_ID = "SELECT * FROM `order` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_ALL_ORDER_FOR_EXECUTE_BY_ID_USER = "SELECT * FROM `order` WHERE NOT isCompleted AND (idRealizer = ? OR idRealizer IS NULL);";
	private static final String SQL_REQUEST_FIND_ALL_COMPLETE_ORDER = "SELECT * FROM `order` WHERE isCompleted;";
	private static final String SQL_REQUEST_FIND_ALL_ORDER_FOR_EXECUTE_BY_SUBJECT_NAME_BY_ID_USER = "SELECT * FROM `order` JOIN `task` ON order.idTask = task.id"
			+ " JOIN `subject` ON task.idSubject = subject.id WHERE NOT isCompleted AND nameSubject = ? AND (idRealizer = ? OR idRealizer IS NULL);";
	private static final String REQUEST_FIND_ALL_COMPLETE_ORDER_BY_SUBJECT_NAME_BY_ID_USER = "SELECT * FROM `order` JOIN `task` ON order.idTask = task.id"
			+ " JOIN `subject` ON task.idSubject = subject.id WHERE isCompleted AND nameSubject = ? AND idRealizer = ?;";
	private static final String SQL_REQUEST_FIND_ALL_ORDER_BY_ID_USER = "SELECT * FROM `order` WHERE idUser = ?;";
	private static final String SQL_REQUEST_UPDATE_ORDER = "UPDATE `order` SET `idUser` = ?, `idTask` = ?, `idRealizer` = ?, `note` = ?, `adjustedPriceTask` = ?, "
			+ "`priceOrder` = ?, `isProcessed` = ?, `isConfirmed` = ?, `isPaid` = ?, `isCompleted` = ?, `dateCreate` = ?, `dateProcess` = ?, "
			+ "`dateConfirm` = ?, `datePay` = ?, `dateComplete` = ?, `idFinishFile` = ? WHERE id = ?;";
	private static final String SQL_REQUEST_DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE id = ?";
	
	private OrderBuilder orderBuilder = new OrderBuilder();
	private UserDAO userDAO = new UserDAOImpl();
	private TaskDAO taskDAO = new TaskDAOImpl();
	private FinishFileDAO finishFileDAO = new FinishFileDAOImpl();
	
	@Override
	public Order create(Order entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create order - order null");
			throw new DAOException("Error create order - order null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_ORDER);) {
			
			preparedStatement.setInt(1, entity.getUser().getId());
			preparedStatement.setInt(2, entity.getTask().getId());
			preparedStatement.setString(3, entity.getNote());
			preparedStatement.setBigDecimal(4, entity.getAdjustedPriceTask());
			preparedStatement.setBigDecimal(5, entity.getPriceOrder());

			result = preparedStatement.executeUpdate();			

		} catch (SQLException e) {
			LOGGER.error("Error create order - SQL error, e");
			throw new DAOException("Error create order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create order - pool error, e");
			throw new DAOException("Error create uorderser - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error create order - order not create");
		}
				
		return entity;
	}

	@Override
	public Order findById(int id) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ORDER_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - order not found by id");				
				return null;
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}
	
	@Override
	public List<Order> findAllOrderByIdUser (int idUser) throws DAOException{
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_ALL_ORDER_BY_ID_USER);) {

			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - order not found by id user");
				return null;
			}
			return result;

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}
	
	@Override
	public List<Order> findAllOrderForExecuteByIdUser(int idUser) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_ALL_ORDER_FOR_EXECUTE_BY_ID_USER);) {
						
			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - all order for execute not found");
				return null;
			}
			return result;

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}
	@Override
	public List<Order> findAllCompleteOrder() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_ALL_COMPLETE_ORDER);) {						
		
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - all complete order not found");
				return null;
			}
			return result;

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}

	@Override
	public List<Order> findAllOrderForExecuteBySubjectNameByIdUser (String nameSubject, int idUser) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_ALL_ORDER_FOR_EXECUTE_BY_SUBJECT_NAME_BY_ID_USER);) {

			preparedStatement.setString(1, nameSubject);
			preparedStatement.setInt(2, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - order for execute not found");
				return null;
			}
			return result;

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}
	
	@Override
	public List<Order> findAllCompleteOrderBySubjectNameByIdUser(String nameSubject, int idUser) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(REQUEST_FIND_ALL_COMPLETE_ORDER_BY_SUBJECT_NAME_BY_ID_USER);) {

			preparedStatement.setString(1, nameSubject);
			preparedStatement.setInt(2, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Order> result = buildOrderList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error - complete order not found");
				return null;
			}
			return result;

		} catch (SQLException e) {
			LOGGER.error("Error find order - SQL error, e");
			throw new DAOException("Error find order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find order - pool error, e");
			throw new DAOException("Error find order - pool error", e);
		}
	}
	
	private List<Order> buildOrderList(ResultSet resultSet) throws DAOException {
		List<Order> orderList = new ArrayList<>();
		Order order;
		String realizerIdStr;
		String dateOrderProcess;
		String dateOrderConfirm;
		String dateOrderPay;
		String dateOrderComplete;
		String idFinishFile;
		try {
			while (resultSet.next()) {				

				realizerIdStr = resultSet.getString(DBColumnNameConstant.ORDER_ID_REALIZER);
				dateOrderProcess = resultSet.getString(DBColumnNameConstant.ORDER_DATE_PROCESS);
				dateOrderConfirm = resultSet.getString(DBColumnNameConstant.ORDER_DATE_CONFIRM);
				dateOrderPay = resultSet.getString(DBColumnNameConstant.ORDER_DATE_PAY);
				dateOrderComplete = resultSet.getString(DBColumnNameConstant.ORDER_DATE_COMPLETE);
				idFinishFile = resultSet.getString(DBColumnNameConstant.ORDER_ID_FINISH_FILE);
				
				order = orderBuilder
						.createNewOrder()
						.withId(resultSet.getInt(DBColumnNameConstant.ORDER_ID))
						.withUser(userDAO.findById(resultSet.getInt(DBColumnNameConstant.ORDER_ID_USER)))
						.withTask(taskDAO.findById(resultSet.getInt(DBColumnNameConstant.ORDER_ID_TASK)))
						.withNote(resultSet.getString(DBColumnNameConstant.ORDER_NOTE))
						.withAdjustedPriceTask(resultSet.getBigDecimal(DBColumnNameConstant.ORDER_ADJUSTED_PRICE_TASK))
						.withPriceOrder(resultSet.getBigDecimal(DBColumnNameConstant.ORDER_PRICE_ORDER))
						.withIsProcessed(resultSet.getBoolean(DBColumnNameConstant.ORDER_IS_PROCESSED))
						.withIsConfirmed(resultSet.getBoolean(DBColumnNameConstant.ORDER_IS_CONFIRNED))
						.withIsPaid(resultSet.getBoolean(DBColumnNameConstant.ORDER_IS_PAID))
						.withIsCompleted(resultSet.getBoolean(DBColumnNameConstant.ORDER_IS_COMPLETED))
						.withDateCreate(LocalDateTime.parse(resultSet.getString(DBColumnNameConstant.ORDER_DATE_CREATE).replace(" ", "T")))
						.build();

				if (realizerIdStr != null) {
					order.setRealizer(userDAO.findById(Integer.valueOf(realizerIdStr)));
				}
								
				if (dateOrderProcess != null) {
					order.setDateProcess(LocalDateTime.parse(dateOrderProcess.replace(" ", "T")));
				}
				
				if (dateOrderConfirm != null) {
					order.setDateConfirm(LocalDateTime.parse(dateOrderConfirm.replace(" ", "T")));
				}
				
				if (dateOrderPay != null) {
					order.setDatePay(LocalDateTime.parse(dateOrderPay.replace(" ", "T")));
				}
				
				if (dateOrderComplete != null) {
					order.setDateComplete(LocalDateTime.parse(dateOrderComplete.replace(" ", "T")));
				}
				
				if (idFinishFile != null) {
					order.setFinishFile(finishFileDAO.findById(Integer.valueOf(idFinishFile)));
				}
				
				orderList.add(order);
			}
			
		} catch (Exception e) {
			LOGGER.error("Error build order - SQL error", e);
			throw new DAOException("Error build order - SQL error", e);
		}
		return orderList;
	}

	@Override
	public Order update(Order entity) throws DAOException {

		if (entity == null) {
			LOGGER.error("Error update order - order null");
			throw new DAOException("Error update order - order null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_UPDATE_ORDER);) {

			preparedStatement.setInt(1, entity.getUser().getId());
			preparedStatement.setInt(2, entity.getTask().getId());
			preparedStatement.setInt(3, entity.getRealizer().getId());
			preparedStatement.setString(4, entity.getNote());
			preparedStatement.setBigDecimal(5, entity.getAdjustedPriceTask());
			preparedStatement.setBigDecimal(6, entity.getPriceOrder());
			preparedStatement.setBoolean(7, entity.isProcessed());
			preparedStatement.setBoolean(8, entity.isConfirmed());
			preparedStatement.setBoolean(9, entity.isPaid());
			preparedStatement.setBoolean(10, entity.isCompleted());
			preparedStatement.setString(11, entity.getDateCreate().toString().replace('T', ' '));			
			
			if (entity.getDateProcess() != null) {
				preparedStatement.setString(12, entity.getDateProcess().toString().replace('T', ' '));
			} else {
				preparedStatement.setNull(12, Types.NULL);
			}
			
			if (entity.getDateConfirm() != null) {
				preparedStatement.setString(13, entity.getDateConfirm().toString().replace('T', ' '));
			} else {
				preparedStatement.setNull(13, Types.NULL);
			}
			
			if (entity.getDatePay() != null) {
				preparedStatement.setString(14, entity.getDatePay().toString().replace('T', ' '));
			}else {
				preparedStatement.setNull(14, Types.NULL);
			}
			
			if (entity.getDateComplete() != null) {
				preparedStatement.setString(15, entity.getDateComplete().toString().replace('T', ' '));
			}else {
				preparedStatement.setNull(15, Types.NULL);
			}
			
			if (entity.getFinishFile() != null) {
				preparedStatement.setInt(16, entity.getFinishFile().getId());
			}else {
				preparedStatement.setNull(16, Types.NULL);
			}
			
			preparedStatement.setInt(17, entity.getId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error update order - SQL error, e");
			throw new DAOException("Error update order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error update order - pool error, e");
			throw new DAOException("Error update order - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error update order - order not update");
		}
				
		return findById(entity.getId());
	}	

	@Override
	public boolean delete(int id) throws DAOException {
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_ORDER_BY_ID);) {		
			
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error delete order - SQL error, e");
			throw new DAOException("Error delete order - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete order - pool error, e");
			throw new DAOException("Error delete order - pool error", e);
		}		
				
		return (result == 1);
	}

	@Override
	public boolean delete(Order entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error delete order - order null");
			throw new DAOException("Error delete order - order null");
		}

		return delete(entity.getId());
	}
	
}
