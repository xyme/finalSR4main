package cabletie.cms.ops.operationDBModel.transferItem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cabletie.cms.ops.operationDBModel.inventory.Item;

/**
 * @author xY
 *
 * Item Request form object that tracks item request by locations store staff and approval by location store manager
 * Serves as "Internal Purchase Order"
 *
 */
@Entity
public class ItemRequest {
	
	//Request Details (Created by New Item Request by Location Staff)
	@Id
	private int requestID;
	private Timestamp requestDate;
	private String requestBy;
	private String requestLocale;
	private String requestDescription;
	
	//Approval Details (Approved by Warehouse Manager)
	private int approvalStatus;
	private Timestamp approvedDate;
	private String approvedBy;
	
	//Item Requested
	private int itemID;
	private String itemName;
	private int qtyRequested;



	public ItemRequest() {
	}

	public ItemRequest(String requestBy, String requestLocale, String requestDescription, int itemID, String itemName, int qtyRequested) {
		
		super();
		
		int randomNum = ThreadLocalRandom.current().nextInt(1, 999999999+1);
		this.requestID = randomNum;
		
		//Set current time 
		Date date = new Date();
		long time = date.getTime();
		this.requestDate = new Timestamp(time);
		this.requestDescription = requestDescription;
		
		this.requestBy = requestBy;
		this.requestLocale = requestLocale;
		
		this.itemID = itemID;
		this.itemName = itemName;
		this.qtyRequested = qtyRequested;
		
//		this.approvalStatus = 0;
//		this.approvedDate = new Timestamp(time);
//		this.approvedBy = "";
		
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public Timestamp getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestLocale() {
		return requestLocale;
	}

	public void setRequestLocale(String requestLocale) {
		this.requestLocale = requestLocale;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
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

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQtyRequested() {
		return qtyRequested;
	}

	public void setQtyRequested(int qtyRequested) {
		this.qtyRequested = qtyRequested;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
		
	}

