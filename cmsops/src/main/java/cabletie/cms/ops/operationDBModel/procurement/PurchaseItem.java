package cabletie.cms.ops.operationDBModel.procurement;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class PurchaseItem {

	@Id
	private String itemID;//auto-generated
	private String name;
	private String description;
	private int quantity;
	private double unitCost;

	//Map to a itemRequest
	@OneToOne
	@JoinColumn(name="RequestForPurchase_rfpID")
	private RequestForPurchase rfp;

	public PurchaseItem() {
	}

	public PurchaseItem(String name, String description, int quantity, double unitCost) {
		this.itemID = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitCost = unitCost;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public RequestForPurchase getRfp() {
		return rfp;
	}

	public void setRfp(RequestForPurchase rfp) {
		this.rfp = rfp;
	}
	
	
	
}
