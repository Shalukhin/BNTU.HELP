package dao;

import entity.Role;
import exception.DAOException;

public interface RoleDAO extends BaseDAO<Role> {
	
	Role findByName(String nameRole) throws DAOException;

}
