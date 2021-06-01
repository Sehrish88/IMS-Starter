package com.qa.ims.controller;

import java.util.List;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import com.qa.ims.persistence.domain.Order;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(); 
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils; 
		
	}
	
	
	

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		List<Order> orders = orderDAO.readAll();
		for (Order order: orders) {
			LOGGER.info(order); 
		}
		
		return orders;
	} 

	@Override
	public Order create() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter a customer id");
		Long customer_id = utils.getLong();
		Order order = orderDAO.create(new Order (customer_id)); 
		
		
		return order;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

} 
