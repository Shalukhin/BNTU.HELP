package service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.OrderBuilder;
import builder.PersonalDataBuilder;
import dao.OrderDAO;
import dao.PersonalDataDAO;
import dao.TaskDAO;
import dao.UserDAO;
import entity.FinishFile;
import entity.Order;
import entity.PersonalData;
import entity.Task;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class.getName());
	
	private final static String ADMINISTRATOR_ROLE = "admin";
	private final static String REALIZER_ROLE = "realizer";
	
	private OrderBuilder orderBuilder = new OrderBuilder();
	private PersonalDataBuilder personalDataBuilder = new PersonalDataBuilder();
	private PersonalDataDAO personalDataDAO = DAOFactory.getInstance().getPersonalDataDAO();
	private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
	private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	private TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();

	@Override
	public Order addNewOrder(String nameTask, String note, User user) throws ServiceException {	
	
		try {
			Task orderedTask = taskDAO.findByNameTask(nameTask);
			if (orderedTask != null) {
				Order order = orderBuilder
						.createNewOrder()
						.withUser(user)
						.withTask(orderedTask)
						.withNote(note)
						.withAdjustedPriceTask(orderedTask.getPriceTask())
						.build();			
				
				return orderDAO.create(order);
			} else {
				return null;
			}
		} catch (DAOException e) {
			LOGGER.error("Error add new order", e);
			throw new ServiceException("Error add new order", e);
		}
		
	}
	
	@Override
	public List<Order> takeAllUserOrders(User User) throws ServiceException {
		List<Order> orderList = new ArrayList<>();
		try {
			orderList = orderDAO.findAllOrderByIdUser(User.getId());
		} catch (DAOException e) {
			LOGGER.error("Error take all user's oders", e);
			throw new ServiceException("Error take all user's oders", e);
		}
		
		if (orderList != null) {
			Collections.reverse(orderList);
		}
		
		return orderList;
	}
	
	@Override
	public List<Order> takeExecuteOrders(User user) throws ServiceException {
		List<Order> executeOrderList = new ArrayList<>();
		try {
			switch (user.getRole().getNameRole()) {
			case ADMINISTRATOR_ROLE :
				executeOrderList = orderDAO.findAllOrderForExecuteByIdUser(user.getId());
				break;
			case REALIZER_ROLE:
				executeOrderList = orderDAO.findAllOrderForExecuteBySubjectNameByIdUser(user.getRole().getSubject().getNameSubject(), user.getId());
				break;
			default:					
			}			
		} catch (DAOException e) {
			LOGGER.error("Error take execute oders", e);
			throw new ServiceException("Error take execute oders", e);
		}
		
		if (executeOrderList != null) {
			Collections.reverse(executeOrderList);
		}		
		
		return executeOrderList;
	}
	
	@Override
	public Order takeOrderById(String idOrderStr, User user) throws ServiceException {
		
		try {
			Order order = orderDAO.findById(Integer.valueOf(idOrderStr));
			if (order.getUser().equals(user) || user.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
				return order;
			}
			LOGGER.error("Error - no access for view order");
			throw new ServiceException("Error - no access for view order");		 		
		} catch (Exception e) {
			LOGGER.error("Error take oder by id", e);
			throw new ServiceException("Error take oder by id", e);
		}
		
	}
	
	@Override
	public List<Order> takeCompleteOrders(User user) throws ServiceException {
		List<Order> completeOrderList = new ArrayList<>();
		try {
			switch (user.getRole().getNameRole()) {
			case ADMINISTRATOR_ROLE :
				completeOrderList = orderDAO.findAllCompleteOrder();
				break;
			case REALIZER_ROLE:
				completeOrderList = orderDAO.findAllCompleteOrderBySubjectNameByIdUser(user.getRole().getSubject().getNameSubject(), user.getId());
				break;
			default:					
			}			
		} catch (DAOException e) {
			LOGGER.error("Error take complete oders", e);
			throw new ServiceException("Error take complete oders", e);
		}
		
		if (completeOrderList != null) {
			Collections.reverse(completeOrderList);
		}
		
		return completeOrderList;
	}	
	
	@Override
	public boolean processOrder(String idOrderStr, String adjustedPriceTaskStr, User user) throws ServiceException {
		
		try {		
			
			Order orderForProcess = orderDAO.findById(Integer.valueOf(idOrderStr));
			
			if (orderForProcess == null || orderForProcess.isProcessed() ) {				
				return false;
			}		
			
			if (user.getRole().getSubject() != null) {
				if (!user.getRole().getSubject().getNameSubject().equals(orderForProcess.getTask().getSubject().getNameSubject())) {
					return false;
				}
			} else if (!user.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
				return false;
			}			
			
			orderForProcess.setAdjustedPriceTask(new BigDecimal(adjustedPriceTaskStr).setScale(2));
			orderForProcess.setPriceOrder(orderForProcess.getAdjustedPriceTask().multiply(new BigDecimal(orderForProcess.getUser().getStatus().getRatioPay())));
			orderForProcess.setRealizer(user);
			orderForProcess.setProcessed(true);
			orderForProcess.setDateProcess(LocalDateTime.now());	
			
			return orderDAO.update(orderForProcess) != null;
			
		} catch (Exception e) {
			LOGGER.error("Error process oder", e);
			throw new ServiceException("Error process oder", e);
		}
	}

	@Override
	public boolean confirmOrder(String idOrderStr, User user) throws ServiceException {
		
		try {			
			Order orderForConfirm = orderDAO.findById(Integer.valueOf(idOrderStr));
			if (orderForConfirm == null || orderForConfirm.isConfirmed() || orderForConfirm.getUser().getId() != user.getId()) {
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
	public boolean payOrder(String idOrderStr, User user) throws ServiceException {
		
		try {
			Order orderForPay = orderDAO.findById(Integer.valueOf(idOrderStr));		
			
			if (orderForPay == null || orderForPay.isPaid() ) {				
				return false;
			}		
			
			if (user.getRole().getSubject() != null) {
				if (!user.getRole().getSubject().getNameSubject().equals(orderForPay.getTask().getSubject().getNameSubject())) {
					return false;
				}
			} else if (!user.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
				return false;
			}
			
			int idInvitingUser = 0;
			if(orderForPay.getUser().getPersonalData() != null) {
				idInvitingUser = orderForPay.getUser().getPersonalData().getIdInvitingUser();
			}
			 try {
				User invitingUser = userDAO.findById(idInvitingUser);
				if (invitingUser.getPersonalData() == null) {
					PersonalData personalData = personalDataBuilder
													.createNewPersonalData()
													.withBonusMoney(orderForPay.getPriceOrder().multiply(new BigDecimal(0.1)))
													.build();
					invitingUser.setPersonalData(personalDataDAO.create(personalData));
					userDAO.update(invitingUser);
					
				} else {
					PersonalData personalData = invitingUser.getPersonalData();
					personalData.setBonusMoney(personalData.getBonusMoney().add(orderForPay.getPriceOrder().multiply(new BigDecimal(0.1))));
					personalDataDAO.update(personalData);						
				}	
			 } catch (DAOException e) {
				 LOGGER.error("Error bonus money");
			 }
			
			
			orderForPay.setPaid(true);
			orderForPay.setDatePay(LocalDateTime.now());
			
			return orderDAO.update(orderForPay) != null;
			
		} catch (Exception e) {
			LOGGER.error("Error pay oder", e);
			throw new ServiceException("Error pay oder", e);
		}		
	}
	
	@Override
	public boolean completeOrder(String idOrderStr, FinishFile finishFile, User user) throws ServiceException {
		
		try {
			Order orderForComplete = orderDAO.findById(Integer.valueOf(idOrderStr));		
			
			if (finishFile == null || orderForComplete == null || orderForComplete.isCompleted()) {				
				return false;
			}		
			
			if (user.getRole().getSubject() != null) {
				if (!user.getRole().getSubject().getNameSubject().equals(orderForComplete.getTask().getSubject().getNameSubject())) {
					return false;
				}
			} else if (!user.getRole().getNameRole().equals(ADMINISTRATOR_ROLE)) {
				return false;
			}
			
			orderForComplete.setFinishFile(finishFile);
			orderForComplete.setCompleted(true);
			orderForComplete.setDateComplete(LocalDateTime.now());
			
			return orderDAO.update(orderForComplete) != null;		
			
		} catch (Exception e) {
			LOGGER.error("Error complete oder", e);
			throw new ServiceException("Error complete oder", e);
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
