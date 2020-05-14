package service;

import java.util.List;

import entity.Order;
import entity.User;
import exception.ServiceException;

public interface OrderService {
	
	Order addNewOrder(Order order) throws ServiceException;
	List<Order> takeExecuteOrders(User user)  throws ServiceException;
	List<Order> takeCompleteOrders(User user)  throws ServiceException;
	List<Order> takeAllUserOrders(User user) throws ServiceException;	
	boolean confirmOrder(String idOrderStr, int idCurrentUser) throws ServiceException;
	
	boolean deleteOrder(String idOrderStr) throws ServiceException;
	
}
