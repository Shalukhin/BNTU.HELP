package dao;

import entity.User;
import exception.DAOException;

public interface UserDAO extends BaseDAO<User>{
	
	User findByLoginAndPassword(String login, String password) throws DAOException;
	boolean checkExistLogin(String login) throws DAOException;
}
