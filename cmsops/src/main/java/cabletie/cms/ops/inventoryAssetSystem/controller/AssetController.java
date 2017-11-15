package cabletie.cms.ops.inventoryAssetSystem.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import cabletie.cms.ops.infrasystem.controller.Infrastructure;
import cabletie.cms.ops.infrasystem.service.DepotService;
import cabletie.cms.ops.infrasystem.service.MallService;
import cabletie.cms.ops.infrasystem.service.StationService;
import cabletie.cms.ops.infrasystem.service.WarehouseService;
import cabletie.cms.ops.inventoryAssetSystem.service.AssetService;
import cabletie.cms.ops.operationDBModel.assets.Asset;
import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;
import cabletie.cms.ops.operationDBModel.infra.Mall.Mall;
import cabletie.cms.ops.operationDBModel.infra.Station.Station;
import cabletie.cms.ops.operationDBModel.infra.Warehouse.Warehouse;




/**
 * @author xY
 *
 *
 */
@Controller
@SessionAttributes("name")
public class AssetController {
	

	@Autowired
	AssetService assSvc;
	@Autowired
	private DepotService depotSvc;
	@Autowired
	private MallService mallSvc;
	@Autowired
	private StationService stnSvc;
	@Autowired
	private WarehouseService warehouseSvc;
	
    @GetMapping("/asset-addnew")
    public String addNewAssetView(Model model) {
    	
    	//Get separate List of infrastructures
    	List<Depot> depots = depotSvc.getAllDepot();
    	List<Mall> malls  = mallSvc.getAllMall();
    	List<Station> stations = stnSvc.getAllStation();
    	List<Warehouse> warehouse = warehouseSvc.getAllWarehouse();
    	
    	//Concatenate 
    	List<Infrastructure> infraList = new ArrayList<Infrastructure>();
    	
    	//Total list length
    	//int listLen = depots.size() + malls.size() + stations.size() + warehouse.size();
    	
    	
    	for(int i =0; i < depots.size(); i++) {
    		
    		Depot depot = depots.get(i);
    		Infrastructure infra = new Infrastructure(depot.getDepotID(),depot.getName(),depot.getLocation(),depot.getStatus());
    		infraList.add(infra);
    		
    	}
    	
    	for(int i =0; i < malls.size(); i++) {

    		Mall mall = malls.get(i);
    		Infrastructure infra = new Infrastructure(mall.getMallID(),mall.getName(),mall.getLocation(),mall.getStatus());
    		infraList.add(infra);

    	}
    	for(int i =0; i < stations.size(); i++) {
    		
    		Station station = stations.get(i);
    		String type = station.getStationID();
    		//Eliminate depots from displaying
    		if(!type.substring(0,1).equalsIgnoreCase("D")) {
    			Infrastructure infra = new Infrastructure(station.getStationID(),station.getName(),station.getLocation(),station.getStatus());
    			infraList.add(infra);
    		}

    	}for(int i =0; i < warehouse.size(); i++) {

    		Warehouse ware = warehouse.get(i);
    		Infrastructure infra = new Infrastructure(ware.getWarehouseID(),ware.getName(),ware.getLocation(),ware.getStatus());
    		infraList.add(infra);

    	}
			
    	model.addAttribute("infraList", infraList);

    	
        return "asset-addnew";
    }
	
	
    @PostMapping("/asset-addnew")
    public String addNewAsset(@RequestParam("assname") String assetName,
    		@RequestParam("assetID") String serialID,
    		@RequestParam("infraID") String infraID,
    		@RequestParam("category") String category,
    		@RequestParam("purchaseDate") String purchaseDate,
    		@RequestParam("description") String description,
    		@RequestParam("warrantyDate") String warrantyDate, 
    		@RequestParam("depPeriod") String depPeriod, 
    		@ModelAttribute("name") SystemAccount user,
    		Model model
    		) throws ParseException {
    	
    	//Parse the dates to sql format
    	Timestamp purDate = dateParser(purchaseDate);
    	Timestamp warDate = dateParser(warrantyDate);
    	    	
    	//check if asset exist first
  
    	if(assSvc.getAsset(serialID).isEmpty()) {

        	assSvc.createAsset(serialID, infraID, assetName, description, 
            		category, purDate, warDate, depPeriod, user.getUserID());
        	
        	return "redirect:/asset-viewall";
    		
    	}else {
    		
    		model.addAttribute("err", "Failed to add asset! Check for asssetID Duplicate!");
    		
    		return "cruderr";
    		
    	}

    }
    
    @GetMapping("/crudeerr")
    public String crudeError(@ModelAttribute("err") String error){
    	
    	return "cruderr";
    }
    
    @GetMapping("/asset-viewall")
    public String viewAllAsset(@ModelAttribute("name") SystemAccount user, Model model) {

    	
    	List<Asset> assList = assSvc.getAllAsset();
    	model.addAttribute("assets", assList);
        
        return "asset-viewall";
    }
    
    @GetMapping("/asset-viewdetails")
    public String viewDetails(@RequestParam("sentAssID") String assetID, @ModelAttribute("name") SystemAccount user, Model model) {
    	
    	
    	Asset ass = assSvc.getAsset(assetID).get(0);
    	
    	model.addAttribute("asset", ass);
    	    	
    	return "asset-viewdetails";
    	
    }
    
    @GetMapping("/asset-editdetails")
    public String editDetails(@RequestParam("sentAssID") String assetID, @ModelAttribute("name") SystemAccount user, Model model) {
   
    	Asset ass = assSvc.getAsset(assetID).get(0);
    	
    	model.addAttribute("asset", ass);
    	
    	//Get separate List of infrastructures
    	List<Depot> depots = depotSvc.getAllDepot();
    	List<Mall> malls  = mallSvc.getAllMall();
    	List<Station> stations = stnSvc.getAllStation();
    	List<Warehouse> warehouse = warehouseSvc.getAllWarehouse();
    	
    	//Concatenate 
    	List<Infrastructure> infraList = new ArrayList<Infrastructure>();
    	
    	//Total list length
    	//int listLen = depots.size() + malls.size() + stations.size() + warehouse.size();
    	
    	
    	for(int i =0; i < depots.size(); i++) {
    		
    		Depot depot = depots.get(i);
    		Infrastructure infra = new Infrastructure(depot.getDepotID(),depot.getName(),depot.getLocation(),depot.getStatus());
    		infraList.add(infra);
    		
    	}
    	
    	for(int i =0; i < malls.size(); i++) {

    		Mall mall = malls.get(i);
    		Infrastructure infra = new Infrastructure(mall.getMallID(),mall.getName(),mall.getLocation(),mall.getStatus());
    		infraList.add(infra);

    	}
    	for(int i =0; i < stations.size(); i++) {
    		
    		Station station = stations.get(i);
    		String type = station.getStationID();
    		//Eliminate depots from displaying
    		if(!type.substring(0,1).equalsIgnoreCase("D")) {
    			Infrastructure infra = new Infrastructure(station.getStationID(),station.getName(),station.getLocation(),station.getStatus());
    			infraList.add(infra);
    		}

    	}for(int i =0; i < warehouse.size(); i++) {

    		Warehouse ware = warehouse.get(i);
    		Infrastructure infra = new Infrastructure(ware.getWarehouseID(),ware.getName(),ware.getLocation(),ware.getStatus());
    		infraList.add(infra);

    	}
			
    	model.addAttribute("infraList", infraList);
    			
    	return "asset-editdetails";
    }
    
    
    @PostMapping("/asset-editdetails")
    public String editDetailsPost(@RequestParam("assetID") String assetID,
    		@RequestParam("infraID") String infraID,
    		@RequestParam("warrantyDate") String warrantyDate,
    		@RequestParam("depPeriod") String depPeriod,
    		@ModelAttribute("name") SystemAccount user) throws ParseException {
    	
   	     
    	//Convert Date
    	//Parse the dates to sql format
    	
    	Timestamp warDate = dateParser(warrantyDate);
    	
    	
    	assSvc.updateAsset(assetID, infraID, warDate, depPeriod, user.getUserID());
				
		
    	return "redirect:/asset-viewall";
    }
    
    @GetMapping("/asset-delete")
    public String editDetails(@RequestParam("sentAssID") String assetID, @ModelAttribute("name") SystemAccount user) {
    	
    	
    	assSvc.decomAsset(assetID, user.getUserID());
    	
		
    	return "redirect:/asset-viewall";
    }
    
    @GetMapping("/asset-log")
    public String assetLog(Model model) {
    	
    	model.addAttribute("log", assSvc.getAllAssetLog());
    	
    	return "asset-log";
    	
    }
    
	
	public java.sql.Timestamp dateParser(String toConvert) throws ParseException {
    	//DateParser
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
    	java.util.Date convertedDate = formatter.parse(toConvert); 
    	java.sql.Timestamp sqlDate = new java.sql.Timestamp(convertedDate.getTime()); 
    	

    	return sqlDate;
		
	}
}





