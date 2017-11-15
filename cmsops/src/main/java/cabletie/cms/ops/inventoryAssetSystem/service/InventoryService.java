package cabletie.cms.ops.inventoryAssetSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.inventoryDAO.InvDAO;
import cabletie.cms.ops.operationDBDao.inventoryDAO.ItemLogDAO;
import cabletie.cms.ops.operationDBModel.assets.AssetLog;
import cabletie.cms.ops.operationDBModel.inventory.InventoryID;
import cabletie.cms.ops.operationDBModel.inventory.Item;
import cabletie.cms.ops.operationDBModel.inventory.ItemLog;


/**
 * @author xY
 *
 */
@Service
public class InventoryService {
	
	@Autowired
	private InvDAO invDAO;
	@Autowired
	private ItemLogDAO itemLogDAO;


	
	/**
	 * @param itemID
	 * @param infraID
	 * @return List<Item>
	 * *Get Method* - Get Item Object as first object in a list 
	 */
	public ArrayList<Item> getItem(int itemID, String infraID) {

		
		List<Item> allItems = getAllItem();
		
		ArrayList<Item> localisedItem = new ArrayList<Item>();
		
		for (Item it : allItems) {
			
			if(it.getInfraID().equals(infraID) && it.getItemID() == itemID) {
				
				localisedItem.add(it);
				
			}
			
		}
		
		return localisedItem;
		
	}


	//
	/**
	 * @param infraID
	 * @param name
	 * @param category
	 * @param description
	 * @param quantity
	 * *Create Method* - New Item in Inventory
	 */
	public void createItem(int itemID, String infraID, String name, String category, String description, int quantity, String userID) {

		Item item = new Item(infraID,name,category,description,quantity);
		
		invDAO.save(item);
		
		ItemLog itemLog = new ItemLog(itemID,infraID,userID,0,quantity);
		
		itemLogDAO.save(itemLog);
		
	}
	

	/**
	 * @param itemID
	 * @param infraID
	 * @param name
	 * @param category
	 * @param description
	 * @param aftQty
	 * @param modBy
	 * 
	 * *Update Method* - Update Inventory Entry
	 */
	public void updateItem(int itemID, String infraID, String name, String category, String description, int aftQty, int lowStock, String modBy) {

		Item item = getItem(itemID, infraID).get(0);
		
		item.setName(name);
		item.setCategory(category);
		item.setDescription(description);
		item.setLowStock(lowStock);
		int befQty = item.getQuantity();
		item.setQuantity(aftQty);
		
		if(aftQty == 0) {
			item.setStatus(-1);
		}else if(aftQty<lowStock) {
			item.setStatus(0);
		}else if(aftQty>lowStock) {
			item.setStatus(1);
		}
		
		//Log the quantity changes
		ItemLog itemLog = new ItemLog(itemID,infraID,modBy,befQty,aftQty);
		
		invDAO.save(item);
		itemLogDAO.save(itemLog);
		
	}
	

	/**
	 * @param infraID
	 * @return List<Item> itemList
	 * 
	 * Return a List of all inventory for the current location
	 */
	public ArrayList<Item> getAllItemLocalised(String infraID){

		List<Item> allItems = getAllItem();
		ArrayList<Item> localisedItem = new ArrayList<Item>();
		
		for (Item it : allItems) {
			
			if(it.getInfraID().equals(infraID)) {
				localisedItem.add(it);
				
			}
			
		}
		
		return localisedItem;
		
	}
	
	//
	
	/**
	 * @return
	 * 
	 * Get a list of all storeItems
	 * 
	 */
	public List<Item> getAllStoreItem(){
		
		List<Item> allItems = getAllItem();
		ArrayList<Item> localisedItem = new ArrayList<Item>();
		
		for (Item it : allItems) {
			
			if(it.getInfraID().equals("Central Warehouse")) {
				localisedItem.add(it);
				
			}
			
		}
		
		return localisedItem;
		
	}
	
	/**
	 * @return
	 * 
	 * Get a list of all storeItems - For Store
	 * 
	 */
	public List<Item> getAllItem(){
		
		return invDAO.findAll();
		
	}
	
	//--------------Store CRUD function------------------------
	
	/**
	 * @param name
	 * @param category
	 * @param description
	 * @param quantity
	 * @param userID
	 * 
	 * - Create a new item in Store
	 */
	public void createStoreItem(String name, String category, String description, int quantity, String userID) {

		Item item = new Item("Central Warehouse",name,category,description,quantity);
		
		invDAO.save(item);
		
		ItemLog itemLog = new ItemLog(item.getItemID(),"Central Warehouse",userID,0,quantity);
		
		itemLogDAO.save(itemLog);
		
	} 
	
	public List<ItemLog> getAllItemLog(int itemID){
		
		return itemLogDAO.findByitemID(itemID);
	}
		

}
	
