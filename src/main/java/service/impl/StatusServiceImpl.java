package service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StatusDAO;
import entity.Status;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.StatusService;

public class StatusServiceImpl implements StatusService {
	
	private static final Logger LOGGER = LogManager.getLogger(StatusServiceImpl.class.getName());
	
	private StatusDAO satusDAO = DAOFactory.getInstance().getStatusDAO();

	@Override
	public List<Status> takeAllStatus() throws ServiceException {
		
		try {
			return satusDAO.findAllStatus();
		} catch (DAOException e) {
			LOGGER.error("Error take all status", e);
			throw new ServiceException("Error take all status", e);
		}
	}

}
