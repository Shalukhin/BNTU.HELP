package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.RoleBuilder;
import dao.RoleDAO;
import dao.SubjectDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.Role;
import entity.Subject;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class RoleDAOImpl implements RoleDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(RoleDAOImpl.class.getName());
	
	private RoleBuilder roleBuilder = new RoleBuilder();
	private SubjectDAO subjectDAO = new SubjectDAOImpl();
	
	private static final String SQL_REQUEST_FIND_ROLE_BY_NAME = "SELECT * FROM `role` WHERE nameRole = ?;";
	private static final String SQL_REQUEST_FIND_ROLE_BY_NAME_AND_SUBJECT = "SELECT * FROM `role` JOIN `subject` ON role.idSubject = subject.id WHERE nameRole = ? AND nameSubject = ?;";
	private static final String SQL_REQUEST_FIND_ROLE_BY_ID = "SELECT * FROM `role` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_ALL_ROLE = "SELECT * FROM `role`;";

	@Override
	public Role create(Role bean) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Role findByName(String nameRole) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ROLE_BY_NAME);) {
			
			preparedStatement.setString(1, nameRole);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Role> result = buildRoleList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error role not found by name");
				throw new DAOException("Error role not found by name");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find role by name - SQL error, e");
			throw new DAOException("Error find role by name - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find role by name - pool error, e");
			throw new DAOException("Error find role by name - pool error", e);
		}
	}
	
	@Override
	public Role findByNameAndSubject(String nameRole, String nameSubject) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ROLE_BY_NAME_AND_SUBJECT);) {
			
			preparedStatement.setString(1, nameRole);
			preparedStatement.setString(2, nameSubject);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Role> result = buildRoleList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error role not found by name and subject");
				throw new DAOException("Error role not found by name and subject");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find role by name and subject - SQL error, e");
			throw new DAOException("Error find role by name and subject - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find role by name and subject - pool error, e");
			throw new DAOException("Error find role by name and subject - pool error", e);
		}
	}
	
	@Override
	public Role findById(int id) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ROLE_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Role> result = buildRoleList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error role not found by ID");
				throw new DAOException("Error role not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find role by ID - SQL error, e");
			throw new DAOException("Error find role by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find role by ID - pool error, e");
			throw new DAOException("Error find role by ID - pool error", e);
		}
	}
	
	@Override
	public List<Role> findAllRole() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_ROLE);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildRoleList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all role - SQL error, e");
			throw new DAOException("Error find all role - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all role - pool error, e");
			throw new DAOException("Error find all role - pool error", e);
		}
	}
	
	private List<Role> buildRoleList(ResultSet resultSet) throws DAOException {
		List<Role> roleList = new ArrayList<>();		
		Role role;
		Subject subject = null;
		try {
			while (resultSet.next()) {
				if (resultSet.getString(ROLE_ID_SUBJECT) != null) {
					subject = subjectDAO.findById(resultSet.getInt(ROLE_ID_SUBJECT));
				}
				role = roleBuilder
						.createNewRole()
						.withId(resultSet.getInt(ROLE_ID))
						.withNameRole(resultSet.getString(ROLE_NAME_ROLE))
						.withSubject(subject)
						.build();				
				
				roleList.add(role);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error build role - SQL error", e);
			throw new DAOException("Error build role - SQL error", e);
		}
		return roleList;
	}	
	
	@Override
	public Role update(Role bean) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Role bean) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}	
	
}
