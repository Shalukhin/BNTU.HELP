package dao;

import java.util.List;

import entity.User;
import exception.DAOException;

public interface UserDAO extends BaseDAO<User>{
	
	List<User> read(String login, String password) throws DAOException;
}
