package cabletie.cms.ops.operationDBModel.procurement;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * @author xY
 * ProcurementTask created by Procurement staff for purchase tracking
 * Must be tag to one request for purchase
 * Serves as "Ordering Task"
 */
@Entity
public class ProcurementTask {
	@Id
	private String procID;
	private Timestamp dateCreated;
	
	//-1 if Cancelled | 0 if In-Process | 1 if Completed
	private int status;
	
	//Purchaser Details
	private String purchaseBy;
	private Timestamp purchaseDate;
	
	//Mapping One to One with RFP
	@OneToOne
	@JoinColumn(name="RequestForPurchase_rfpID")
	private RequestForPurchase rfp;

	public ProcurementTask() {
	}

	public ProcurementTask(String procID, Timestamp dateCreated) {
		this.procID = procID;
		this.dateCreated = dateCreated;
		this.status = 0;
	}

	public String getProcID() {
		return procID;
	}

	public void setProcID(String procID) {
		this.procID = procID;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public RequestForPurchase getRfp() {
		return rfp;
	}

	public void setRfp(RequestForPurchase rfp) {
		this.rfp = rfp;
	}
	
	
	
}
