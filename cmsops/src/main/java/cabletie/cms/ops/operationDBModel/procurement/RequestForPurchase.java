package cabletie.cms.ops.operationDBModel.procurement;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author xY
 *
 * Item Re-stocking/Purchase form object that tracks item request by locations store staff 
 * and approval by Department manager
 * Serves as "Request for purchase tracking"
 * 
 * 
 */
@Entity
public class RequestForPurchase {

	@Id
	//Request For Purchase Details
	private String rfpID;
	private Timestamp createdDate;
	private String createdBy;
	private String department;
	private String tagLocation;
	private String description;
	
	//Approval Status (Updated upon Department Manager Approval)
	private int approvalStatus;
	private Timestamp approvedDate;
	private String approvedBy;

	@OneToOne(mappedBy="rfp")
	private ProcurementTask procureTask;

	//List of PurchaseItem by IDs
	@OneToOne(mappedBy="rfp")
	private PurchaseItem purchaseItem;

	public RequestForPurchase() {
	}

	public RequestForPurchase(String rfpID, Timestamp createdDate, String createdBy, String department, String tagLocation, String description) {
		this.rfpID = rfpID;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.department = department;
		this.tagLocation = tagLocation;
		this.description = description;
		this.approvalStatus = 0;
	}

	public String getRfpID() {
		return rfpID;
	}

	public void setRfpID(String rfpID) {
		this.rfpID = rfpID;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTagLocation() { return tagLocation; }

	public void setTagLocation(String tagLocation) { this.tagLocation = tagLocation; }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Timestamp getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Timestamp approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseItem purchaseItem) { this.purchaseItem = purchaseItem;	}

	public ProcurementTask getProcureTask() {
		return procureTask;
	}

	public void setProcureTask(ProcurementTask procureTask) {
		this.procureTask = procureTask;
	}
}
