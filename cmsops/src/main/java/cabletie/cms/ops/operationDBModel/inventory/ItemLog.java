package cabletie.cms.ops.operationDBModel.inventory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemLog {

	@Id 
	private int logID;
	private int itemID;
	private String infraID;
	private Timestamp modDate;
	private String modBy;
	private int befQuantity;
	private int aftQuantity;
	
	public ItemLog() {
	
	}
	
	public ItemLog(int itemID, String infraID, String modBy, int befQuantity, int aftQuantity) {
		
		super();
		int randomNum = ThreadLocalRandom.current().nextInt(1, 999999999+1);
		this.logID = randomNum;
		
		this.itemID = itemID;
		
		this.infraID = infraID;
		
		//Set current time 
		Date date = new Date();
		long time = date.getTime();
		this.modDate = new Timestamp(time);
		
		this.modBy = modBy;
		this.befQuantity = befQuantity;
		this.aftQuantity = aftQuantity;
		
	}
	
	
	
	public String getInfraID() {
		return infraID;
	}

	public void setInfraID(String infraID) {
		this.infraID = infraID;
	}

	public long getLogID() {
		return logID;
	}
	public void setLogID(int logID) {
		this.logID = logID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	public int getBefQuantity() {
		return befQuantity;
	}
	public void setBefQuantity(int befQuantity) {
		this.befQuantity = befQuantity;
	}
	public int getAftQuantity() {
		return aftQuantity;
	}
	public void setAftQuantity(int aftQuantity) {
		this.aftQuantity = aftQuantity;
	}
	
	
	
}
