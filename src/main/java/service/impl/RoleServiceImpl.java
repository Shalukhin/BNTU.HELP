package service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.RoleDAO;
import entity.Role;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class.getName());
	
	private RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();

	@Override
	public List<Role> takeAllRole() throws ServiceException {
		
		try {
			return roleDAO.findAllRole();
		} catch (DAOException e) {
			LOGGER.error("Error take all role", e);
			throw new ServiceException("Error take all role", e);
		}
	}
	
}
