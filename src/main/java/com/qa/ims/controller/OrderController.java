package com.qa.ims.controller;

import java.util.List;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
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
		LOGGER.info("add or remove item");
		String update = utils.getString().toLowerCase().trim(); 
		
		switch(update) {
		case "add": 
			addItem(); 
			break; 
		case "remove":
			removeItem(); 
			break;
			
		default:
			update();  
			
		};
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Order deleted.");
		return orderDAO.delete(id);
	}
	
	
	public OrderItem addItem() {
	
			LOGGER.info("Please enter a order id");
			Long order_id = utils.getLong();
			LOGGER.info("Please enter a item id");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter a quantity");
			Long quantity = utils.getLong();
			OrderItem orderItem = orderDAO.addItem(new OrderItem(quantity, order_id, item_id)); 
			// order_id && item_id -> order_item order = 1 item = 2
			// join orders and order_item join order_item to your item
			// does this id == this id
			return orderItem; 
		}
	
	
	public int  removeItem() {
		LOGGER.info("Please enter a order id");
		Long order_id = utils.getLong();
		
		LOGGER.info("Please enter a item id of the item you want to remove");
		Long item_id = utils.getLong();
		
		
		return orderDAO.removeItem(order_id, item_id); 
		//create a new read order item which will grab the entry from order-item table in orderDAO
		//in order controller under remove item grab speific record which will give access to old quantity 
		//do orderDAO.removeItem() passing in old quatity passing in specified quantity the order id and item id
		
	}

} 
