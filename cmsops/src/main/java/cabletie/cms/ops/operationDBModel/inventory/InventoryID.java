package cabletie.cms.ops.operationDBModel.inventory;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author xY
 * Entity class to denote InventoryID
 */

@Embeddable
public class InventoryID implements Serializable {

	int itemID;
	String infraID;
	
	public InventoryID() {
		
		
	}
	public InventoryID(int itemID, String infraID) {
		super();
		this.itemID = itemID;
		this.infraID = infraID;
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
