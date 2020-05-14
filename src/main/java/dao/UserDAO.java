package dao;

import java.util.List;
import entity.User;
import exception.DAOException;

public interface UserDAO extends AbstractDAO<User>{
	
	User findByLoginAndPassword(String login, String password) throws DAOException;
	List<User> findAllUser() throws DAOException;
	boolean checkExistLogin(String login) throws DAOException;
}
