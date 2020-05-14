package service;

import java.util.List;

import entity.Role;
import exception.ServiceException;

public interface RoleService {
	
	List<Role> takeAllRole() throws ServiceException;

}
