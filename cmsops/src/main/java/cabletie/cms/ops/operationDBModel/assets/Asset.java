package cabletie.cms.ops.operationDBModel.assets;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;



/**
 * @author xY
 *
 */

@Entity
public class Asset {
	
	
	@Id
	private String assetSerialID; //fill in when created
	
	private String infraID;
	
	private String name;
	private String description;
	private String category;
	private Timestamp purchaseDate;
	private Timestamp warrantyDate;
	private String depPeriod;
	private int status; //-1 Disposed | 0- In-Use/Out of Warranty | 1- In-Use
	private int maintainStatus;
	
	public Asset() {
		
	}

	public Asset(String assetSerialID, String infraID, String name, String description, String category, 
			Timestamp puchaseDate, Timestamp warrantyDate, String depPeriod) {
		
		this.assetSerialID = assetSerialID;
		this.infraID = infraID;
		this.name = name;
		this.description = description;
		this.category = category;
		this.purchaseDate = puchaseDate;
		this.warrantyDate = warrantyDate;
		this.depPeriod = depPeriod;
		this.maintainStatus = 0;
		this.status = 1;
	}

	public String getAssetSerialID() {
		return assetSerialID;
	}

	public void setAssetSerialID(String assetSerialID) {
		this.assetSerialID = assetSerialID;
	}

	public String getInfraID() {
		return infraID;
	}

	public void setInfraID(String infraID) {
		this.infraID = infraID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Timestamp getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(Timestamp warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public String getDepPeriod() {
		return depPeriod;
	}

	public void setDepPeriod(String depPeriod) {
		this.depPeriod = depPeriod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getMaintainStatus() {
		return maintainStatus;
	}

	public void setMaintainStatus(int maintainStatus) {
		this.maintainStatus = maintainStatus;
	}
	
	
	
	
}