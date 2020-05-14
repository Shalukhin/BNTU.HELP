package service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.OrderDAO;
import entity.Order;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class.getName());
	
	private final static String ADMINISTRATOR = "admin";
	private final static String REALIZER = "realizer";
	
	private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

	@Override
	public Order addNewOrder(Order order) throws ServiceException {
		
		try {
			return orderDAO.create(order);
		} catch (DAOException e) {
			LOGGER.error("Error add new order", e);
			throw new ServiceException("Error add new order", e);
		}
	}
	
	@Override
	public List<Order> takeExecuteOrders(User user) throws ServiceException {
		List<Order> executeOrderList = new ArrayList<>();
		try {
			switch (user.getRole().getNameRole()) {
			case ADMINISTRATOR :
				executeOrderList = orderDAO.findAllOrderForExecuteByIdUser(user.getId());
				break;
			case REALIZER:
				executeOrderList = orderDAO.findAllOrderForExecuteBySubjectNameByIdUser(user.getRole().getSubject().getNameSubject(), user.getId());
				break;
			default:					
			}			
		} catch (DAOException e) {
			LOGGER.error("Error take execute oders", e);
			throw new ServiceException("Error take execute oders", e);
		}
		
		return executeOrderList;
	}
	
	@Override
	public List<Order> takeCompleteOrders(User user) throws ServiceException {
		List<Order> completeOrderList = new ArrayList<>();
		try {
			switch (user.getRole().getNameRole()) {
			case ADMINISTRATOR :
				completeOrderList = orderDAO.findAllCompleteOrder();
				break;
			case REALIZER:
				completeOrderList = orderDAO.findAllCompleteOrderBySubjectNameByIdUser(user.getRole().getSubject().getNameSubject(), user.getId());
				break;
			default:					
			}			
		} catch (DAOException e) {
			LOGGER.error("Error take complete oders", e);
			throw new ServiceException("Error take complete oders", e);
		}
		
		return completeOrderList;
	}

	@Override
	public List<Order> takeAllUserOrders(User User) throws ServiceException {
		
		try {
			return orderDAO.findAllOrderByIdUser(User.getId());
		} catch (DAOException e) {
			LOGGER.error("Error take all user's oders", e);
			throw new ServiceException("Error take all user's oders", e);
		}
	}

	@Override
	public boolean confirmOrder(String idOrderStr, int idCurrentUser) throws ServiceException {
		
		try {			
			Order orderForConfirm = orderDAO.findById(Integer.valueOf(idOrderStr));
			if (orderForConfirm == null || orderForConfirm.isConfirmed() || orderForConfirm.getUser().getId() != idCurrentUser) {
				return false;
			}
			
			orderForConfirm.setConfirmed(true);
			orderForConfirm.setDateConfirm(LocalDateTime.now());
			
			return orderDAO.update(orderForConfirm) != null;
		} catch (Exception e) {
			LOGGER.error("Error confirm oder", e);
			throw new ServiceException("Error confirm oder", e);
		}
	}

	@Override
	public boolean deleteOrder(String idOrderStr) throws ServiceException {
		try {
			Order orderForDelete = orderDAO.findById(Integer.valueOf(idOrderStr));
			if(orderForDelete == null || orderForDelete.isPaid() || orderForDelete.isCompleted()) {
				return false;
			}
			
			return orderDAO.delete(orderForDelete);
			
		} catch (Exception e) {
			LOGGER.error("Error delete oder", e);
			throw new ServiceException("Error delete oder", e);
		}		
	}
	
}
