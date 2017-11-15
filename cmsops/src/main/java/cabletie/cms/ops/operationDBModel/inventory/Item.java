package cabletie.cms.ops.operationDBModel.inventory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Item {
	
	@Id
	private int inventoryID;
	
	private int itemID;
	private String infraID;
	
//	@EmbeddedId
//	private InventoryID inventoryID;

	private String name;
	private String category; // Pre-defined list
	private String description;
	private int quantity;
	private int lowStock;
	private int status; //System field
	
	public Item() {
	}
	
	//Constructed randomly generated itemID
	public Item(String infraID, String name, String category, String description, int quantity) {

		super();
		
		this.inventoryID = ThreadLocalRandom.current().nextInt(1, 999999999+1);
		this.itemID = ThreadLocalRandom.current().nextInt(1, 999999999+1);
		this.infraID = infraID;
		
		//InventoryID invID = new InventoryID();
		
//		invID.setItemID(randomNum);
//		invID.setInfraID(infraID);
//		this.inventoryID = invID;
		
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.status = 1; //SerialID 
		
	}
	
	//constructor that defines the itemID
	public Item(int itemID, String infraID, String name, String category, String description, int quantity) {

		super();

		
		this.itemID = itemID;
		this.infraID = infraID;
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.status = 1; //SerialID 
		
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLowStock() {
		return lowStock;
	}

	public void setLowStock(int lowStock) {
		this.lowStock = lowStock;
	}

	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getInfraID() {
		return infraID;
	}

	public void setInfraID(String infraID) {
		this.infraID = infraID;
	}


	
	
}