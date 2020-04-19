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
import entity.Role;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class RoleDAOImpl implements RoleDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(RoleDAOImpl.class.getName());
	
	private RoleBuilder roleBuilder = new RoleBuilder();
	
	private String sqlRequestFindRoleByName = "SELECT * FROM `role` WHERE nameRole = ?;";

	@Override
	public Role create(Role bean) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Role findByName(String nameRole) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestFindRoleByName);) {
			
			preparedStatement.setString(1, nameRole);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Role> result = buildRoleList(resultSet);
			if (result.size() == 0) {
				throw new DAOException("Error RoleDAO - role not found");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find role - SQL error, e");
			throw new DAOException("Error find role - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find role - pool error, e");
			throw new DAOException("Error find role - pool error", e);
		}
	}
	
	private List<Role> buildRoleList(ResultSet resultSet) throws DAOException {
		List<Role> roleList = new ArrayList<Role>();		
		Role role;		
		try {
			while (resultSet.next()) {
				role = roleBuilder
						.createNewRole()
						.withId(resultSet.getInt("id"))
						.withNameRole(resultSet.getString("nameRole"))
						.build();				
				
				roleList.add(role);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error build user - SQL error", e);
			throw new DAOException("Error build user - SQL error", e);
		}
		return roleList;
	}

	@Override
	public Role findById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
