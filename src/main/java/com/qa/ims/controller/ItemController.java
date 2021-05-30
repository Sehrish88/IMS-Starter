package com.qa.ims.controller;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import com.qa.ims.persistence.dao.ItemDAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemController implements CrudController<Item>{
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	
	
	@Override
	public List<Item> readAll() {
		// TODO Auto-generated method stub
		List<Item> items = itemDAO.readAll();
		for (Item item: items) {
			LOGGER.info(item);
			
		}
		
		
		return items;
	}

	@Override
	public Item create() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter an item name");
		String name = utils.getString();
		LOGGER.info("Please enter the item cost");
		Double cost = utils.getDouble();
		Item item = itemDAO.create(new Item(name, cost));
		LOGGER.info("Item has been created"); 
		return item;
	}

	@Override
	public Item update() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter the ID of the item you would like to update:");
		Long id = utils.getLong();
		LOGGER.info("Please enter a name:");
		String name = utils.getString();
		LOGGER.info("Please enter a cost:");
		Double cost = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, cost));
		LOGGER.info("Item updated!\n");
		return item;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter the ID of the item you would like to delete:");
		Long id = utils.getLong();
		LOGGER.info("Item deleted!\n");
		return itemDAO.delete(id);
	}

}
