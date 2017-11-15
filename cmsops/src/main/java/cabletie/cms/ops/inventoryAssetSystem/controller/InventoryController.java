package cabletie.cms.ops.inventoryAssetSystem.controller;

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
import cabletie.cms.ops.operationDBModel.inventory.Item;



/**
 * @author xY
 *
 */
@Controller
@SessionAttributes("name")
public class InventoryController {
	

	@Autowired
	private InventoryService invSvc;
	
	
    @GetMapping("/inventory-addnew")
    public String addNewInventoryView() {
        return "inventory-addnew";
    }
	
	
    @PostMapping("/inventory-addnew")
    //To change mapping param
    public String addNewInventory(@RequestParam("inventoryname") String invName,
    		@RequestParam("category") String category,
    		@RequestParam("quantity") int quantity,
    		@RequestParam("description") String description,
    	    @ModelAttribute("name") SystemAccount user
    		){
    	

    	//Get user location
    	String locale = user.getStaff().getLocation();
    	String userID = user.getUserID();
    	
        //long infraID, String name, String category, String description, int quantity
    	
    	//Create a Store item if user is a warehouse Kia
    	if(locale.substring(0,1).equals("W")) {
    		invSvc.createStoreItem(invName, category, description, quantity,userID);
    		
    	}
        
        return "redirect:/inventory-viewall";
        
    }
    
    
    
    /**
     * @param user
     * @param model
     * @return
     * 
     * VIEW ALL! - GET MAPPING
     */
    @GetMapping("/inventory-viewall")
    public String viewAllInventory(@ModelAttribute("name") SystemAccount user, Model model) {

    	
    	//Get user location
    	String locale = user.getStaff().getLocation();

    	//Get List of all items
    	if(locale.substring(0,1).equals("W")) {
    		List<Item> itemList = invSvc.getAllItem();
    		model.addAttribute("inventory", itemList);
    	}else {//For location staff
        List<Item> itemList = invSvc.getAllItemLocalised(locale);
        model.addAttribute("inventory", itemList);
    	}
        //Show add button for warehouse only
        boolean showBtn = locale.substring(0,1).equals("W");
        model.addAttribute("useruser",showBtn);
            
        return "inventory-viewall";
    }
    
    
    
    /**
     * @param invID
     * @param user
     * @param model
     * @return
     * 
     * VIEW DETAILS - GET MAPPING
     */
    @GetMapping("/inventory-viewdetails")
    public String viewDetails(@RequestParam("sentAssID") String invID, 
    		@ModelAttribute("name") SystemAccount user, 
    		@RequestParam("sentInfraID") String infraID,
    		Model model) {
    	
    	//Get user location
    	String locale = user.getStaff().getLocation();
    	int itemID = Integer.valueOf(invID);
        
    	
    	//Show button for warehouse only
        boolean showBtn = locale.substring(0,1).equals("W");
        model.addAttribute("useruser",showBtn);
        
    	//Get Specific Item
    	Item item = invSvc.getItem(itemID, infraID).get(0);
    	model.addAttribute("inventory", item);

    	return "inventory-viewdetails";
    	
    }
    
   
    
    /**
     * @param invID
     * @param user
     * @param model
     * @return
     * 
     * EDIT DETAILS - GET Mapping
     */
    @GetMapping("/inventory-editdetails")
    public String editDetails(@RequestParam("sentAssID") String invID, 
    		@ModelAttribute("name") SystemAccount user,
    		@RequestParam("sentInfraID") String infraID,
    		Model model) {
    	
    	//Get user location
    	String locale = user.getStaff().getLocation();
    	int itemID = Integer.valueOf(invID);
        
    	
    	//Show add button for warehouse only
        boolean showBtn = locale.substring(0,1).equals("W");
        model.addAttribute("useruser",showBtn);
        
    	//Get Specific Item
    	Item item = invSvc.getItem(itemID, infraID).get(0);
    	model.addAttribute("inventory", item);
    	
    	return "inventory-editdetails";
    }


    /**
     * @param invID
     * @param invName
     * @param category
     * @param quantity
     * @param description
     * @param user
     * @return
     * 
     * EDIT DETAILS - POST MAPPING
     * 
     */
    @PostMapping("/inventory-editdetails")
    public String editDetailsPost(@RequestParam("itemID") String invID,
    		@RequestParam("inventoryname") String invName,
    		@RequestParam("category") String category,
    		@RequestParam("quantity") int quantity,
    		@RequestParam("description") String description,
    		@RequestParam("lowStock") int lowStock,
    		@RequestParam("infraID") String infraID,
    	    @ModelAttribute("name") SystemAccount user){
    	
    	//Get user location
//    	String locale = user.getStaff().getLocation();
        int itemID = Integer.valueOf(invID);
        String userID = user.getUserID();
    	
    	//Only can edit name category, long itemID, long infraID, String name, String category, String description, int aftQty, String modBy
   	
        invSvc.updateItem(itemID, infraID, invName, category, description, quantity, lowStock, userID);
		
    	return "redirect:/inventory-viewall";
    	
    }
    
    @GetMapping("/inventory-log")
    public String assetLog(Model model, @RequestParam("sentitemID") int itemID) {
    	
    	
    	model.addAttribute("log", invSvc.getAllItemLog(itemID));
    	
    	return "inventory-log";
    	
    }
      
	

}





