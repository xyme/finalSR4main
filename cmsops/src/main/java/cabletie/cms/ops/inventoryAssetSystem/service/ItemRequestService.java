package cabletie.cms.ops.inventoryAssetSystem.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.inventoryDAO.InvDAO;
import cabletie.cms.ops.operationDBDao.inventoryDAO.ItemRequestDAO;
import cabletie.cms.ops.operationDBModel.inventory.Item;
import cabletie.cms.ops.operationDBModel.transferItem.ItemRequest;

@Service
public class ItemRequestService {
	
		@Autowired
		private InventoryService invSvc;
		@Autowired
		private InvDAO invDAO;
		@Autowired
		private ItemRequestDAO itemReqDAO;
		
		public List<ItemRequest> getRequest(int requestID){
			
			return itemReqDAO.findByrequestID(requestID);
			
		}
		
		
		public void createItemRequest(String requestBy, String requestLocale, String requestDescription, int itemID, String itemName, int qtyRequested) {
				
			ItemRequest itemReq = new ItemRequest(requestBy, requestLocale, requestDescription, itemID, itemName, qtyRequested);
			
			itemReqDAO.save(itemReq);
			
			
		}
		
		public void approveItemRequest(int requestID, String approvedBy) {
			
			
			//Approve the Item Request
			ItemRequest itemReq = getRequest(requestID).get(0);
			
			
			itemReq.setApprovedBy(approvedBy);
			Date date = new Date();
			long time = date.getTime();
			itemReq.setApprovedDate(new Timestamp(time));
			
			itemReq.setApprovalStatus(1);
			itemReqDAO.save(itemReq);
			
			
			
			//Simultaneously Process Business Logic
			
			
			//Deduct quantity from store.
			int itemID = itemReq.getItemID();
		
			Item storeItem = invSvc.getItem(itemID, "Central Warehouse").get(0);
			storeItem.setQuantity(storeItem.getQuantity()-itemReq.getQtyRequested());
			invDAO.save(storeItem);
			
			//Add quantity to location inventory
			
			//Check if inventory already exist
			if(!invSvc.getItem(itemID, itemReq.getRequestLocale()).isEmpty()) {
				
				//If exist get item from location and update its quantity
				Item locationItem = invSvc.getItem(itemID, itemReq.getRequestLocale()).get(0);
				
				String name = locationItem.getName();
				String category = locationItem.getCategory();
				int lowStock = locationItem.getLowStock();
				String description = locationItem.getDescription();
				
				invSvc.updateItem(itemID, itemReq.getRequestLocale(), name, category, description, 
						locationItem.getQuantity()+ itemReq.getQtyRequested(), lowStock, approvedBy);
	
				
			}else {
				
				//If does not exist create a new entry in location
				String infraID = itemReq.getRequestLocale();
				
				invSvc.createItem(itemID, infraID, storeItem.getName() , storeItem.getCategory(), 
						storeItem.getDescription(), itemReq.getQtyRequested(), approvedBy);
			}
			
		}
		
		public void rejectItemRequest(int requestID, String approvedBy) {
			
			///Approve the Item Request
			ItemRequest itemReq = getRequest(requestID).get(0);
			
			
			itemReq.setApprovedBy(approvedBy);
			Date date = new Date();
			long time = date.getTime();
			itemReq.setApprovedDate(new Timestamp(time));
			
			itemReq.setApprovalStatus(-1);
			
			itemReqDAO.save(itemReq);
			
			
			
		}
		
		public List<ItemRequest> getAllRequest(){
			
			return itemReqDAO.findAll();
			
		}
		
	}

