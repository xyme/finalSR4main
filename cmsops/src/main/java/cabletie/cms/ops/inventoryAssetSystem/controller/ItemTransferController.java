package cabletie.cms.ops.inventoryAssetSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.inventoryAssetSystem.service.InventoryService;
import cabletie.cms.ops.inventoryAssetSystem.service.ItemRequestService;
import cabletie.cms.ops.operationDBModel.inventory.Item;
import cabletie.cms.ops.operationDBModel.transferItem.ItemRequest;

@Controller
@SessionAttributes("name")
public class ItemTransferController {

	
	@Autowired
	private InventoryService invSvc;
	@Autowired
	private ItemRequestService itemReqSvc;
	
	@GetMapping("/itemreq-viewstore")
    public String viewStore(Model model) {
		
		
        List<Item> itemList = invSvc.getAllStoreItem();
        model.addAttribute("inventory", itemList);
    	
            
        return "itemreq-viewstore";
    }
	
	 @GetMapping("/itemreq-viewdetails")
	    public String viewDetails(@RequestParam("sentAssID") String invID, 
	    		@ModelAttribute("name") SystemAccount user, 
	    		@RequestParam("sentInfraID") String infraID,
	    		Model model) {
	    	
	    	//Get user location
	    	//String locale = user.getStaff().getLocation();
	        int itemID = Integer.valueOf(invID);
	        
	    	//Get Specific Item
	    	Item item = invSvc.getItem(itemID, infraID).get(0);
	    	model.addAttribute("inventory", item);

	    	return "itemreq-viewdetails";
	    	
	    }
	 
	 @GetMapping("/itemreq-request")
	    public String createRequest(@RequestParam("sentAssID") String invID, 
	    		@ModelAttribute("name") SystemAccount user, 
	    		@RequestParam("sentInfraID") String infraID,
	    		Model model) {
	    	
	    	//Get user location
	    	//String locale = user.getStaff().getLocation();
	        int itemID = Integer.valueOf(invID);
	        
	    	//Get Specific Item
	    	Item item = invSvc.getItem(itemID, infraID).get(0);
	    	model.addAttribute("inventory", item);

	    	return "itemreq-request";
	    	
	    }
	 
	 @PostMapping("/itemreq-request")
	    public String editDetailsPost(@RequestParam("itemID") String invID,
	    		@RequestParam("quantityReq") int quantityReq,
	    		@RequestParam("reqDescription") String reqDescription,
	    		@RequestParam("itemName") String itemName,
	    	    @ModelAttribute("name") SystemAccount user){
	    	
	    	//Get user location
	    	String locale = user.getStaff().getLocation();
	        int itemID = Integer.valueOf(invID);
	        String userID = user.getUserID();
	    	
	    	//Create new itemrequest LOGIC
	        itemReqSvc.createItemRequest(userID,locale,reqDescription,itemID,itemName,quantityReq);
			
	    	return "redirect:/itemreq-viewstore";
	    	
	    }
	 
	 
		@GetMapping("/itemreq-viewall")
	    public String allItemReq(Model model, @ModelAttribute("name") SystemAccount user) {
			
			//Get User location
			String locale = user.getStaff().getLocation();
			
			
	        List<ItemRequest> itemReqList = itemReqSvc.getAllRequest();
	        
	        if(locale.substring(0,1).equals("W")) {
	        model.addAttribute("itemReqList", itemReqList);
	        }else {
	        	ArrayList<ItemRequest> localeItemReq = new ArrayList<ItemRequest>();
	        	
	        	for(ItemRequest req : itemReqList) {
	        		
	        		if(req.getRequestLocale().equals(locale)) {
	        			localeItemReq.add(req);
	        		}
	        		
	        	}
	        	model.addAttribute("itemReqList", localeItemReq);
	        	
	        }
	        
	        return "itemreq-viewall";
	    }
		
		@GetMapping("/itemreq-viewrequest")
	    public String viewRequest(@RequestParam("sentAssID") int requestID,
	    		Model model, @ModelAttribute("name") SystemAccount user) {
			
			//Get User location
			String locale = user.getStaff().getLocation();
			//Show add button for warehouse only
	        boolean showBtn = locale.substring(0,1).equals("W");
	        model.addAttribute("useruser",showBtn);
	        
			ItemRequest request = itemReqSvc.getRequest(requestID).get(0);
			Item item = invSvc.getItem(request.getItemID(), "Central Warehouse").get(0);
			
			model.addAttribute("request", request);
			model.addAttribute("item", item);
			
			return "itemreq-viewrequest";
			
			
	    }
		
		@GetMapping("/itemreq-approve")
	    public String approveRequest(@RequestParam("sentRequestID") int requestID,
	    		@ModelAttribute("name") SystemAccount user ) {
			
			String approvedBy = user.getStaff().getName();
			
			itemReqSvc.approveItemRequest(requestID, approvedBy);
			
			return "redirect:/itemreq-viewall";
			
			
	    }
		
		@GetMapping("/itemreq-reject")
	    public String rejectRequest(@RequestParam("sentRequestID") int requestID,
	    		@ModelAttribute("name") SystemAccount user ) {
			
			String approvedBy = user.getStaff().getName();
			
			itemReqSvc.rejectItemRequest(requestID, approvedBy);
			
			
			return "redirect:/itemreq-viewall";
			
			
	    }
	
}
