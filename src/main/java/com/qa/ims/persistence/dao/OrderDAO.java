package com.qa.ims.persistence.dao;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.persistence.domain.OrderItem;




public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		Long id = resultSet.getLong("id");
		Long customer_id = resultSet.getLong("fk_customer_id");
		//List<Item> orderList = resultSet.getString("orderList"); 
		
		return new Order(id, customer_id);
	} 
	
	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage()); 
		} 
		return new ArrayList<>();
		
		
	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		
		
		return null;
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order create(Order order) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(fk_customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer_id());
			statement.executeUpdate();
			return readLatest();
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		return null;
		
		
	}
	
	public OrderItem addItem(OrderItem orderItem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO `order-items`(`quantity`, `fk_order_id`, `fk_item_id`) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderItem.getQuantity()); 
			statement.setLong(2, orderItem.getOrder_id());
			statement.setLong(3, orderItem.getItem_id());
			statement.executeUpdate();
			//return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null; 
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE orders SET id = " + order.getId() 
			+  " WHERE id = " + order.getId()); 
			return read(order.getId());
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM orders WHERE id = " + id);
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return 0;
	}

	public int removeItem(Long orderId, Long itemId){
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM `order-items` WHERE `fk_order_id` = ? AND `fk_item_id` = ?");){
			statement.setLong(1, orderId );
			statement.setLong(2, itemId );
			return statement.executeUpdate(); 
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}}

	

