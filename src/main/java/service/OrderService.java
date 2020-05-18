package service;

import java.util.List;

import entity.FinishFile;
import entity.Order;
import entity.User;
import exception.ServiceException;

public interface OrderService {
	
	Order addNewOrder(String nameTask, String note, User user) throws ServiceException;
	List<Order> takeExecuteOrders(User user)  throws ServiceException;
	List<Order> takeCompleteOrders(User user)  throws ServiceException;
	List<Order> takeAllUserOrders(User user) throws ServiceException;
	Order takeOrderById(String idOrderStr, User user) throws ServiceException;	
	boolean processOrder(String idOrderStr, String adjustedPriceTaskStr, User user) throws ServiceException;
	boolean confirmOrder(String idOrderStr, User user) throws ServiceException;
	boolean payOrder(String idOrderStr, User user) throws ServiceException;
	boolean completeOrder(String idOrderStr, FinishFile FinishFile, User user) throws ServiceException;
	boolean deleteOrder(String idOrderStr) throws ServiceException;
	
}
