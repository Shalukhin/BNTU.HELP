package dao;

import java.util.List;

import entity.Role;
import exception.DAOException;

public interface RoleDAO extends AbstractDAO<Role> {
	
	Role findByName(String nameRole) throws DAOException;
	Role findByNameAndSubject(String nameRole, String nameSubject) throws DAOException;
	List<Role> findAllRole()  throws DAOException;

}
