package cabletie.cms.ops.infrasystem.controller;

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
import cabletie.cms.ops.infrasystem.service.DepotService;
import cabletie.cms.ops.infrasystem.service.MallService;
import cabletie.cms.ops.infrasystem.service.StationService;
import cabletie.cms.ops.infrasystem.service.WarehouseService;
import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;
import cabletie.cms.ops.operationDBModel.infra.Mall.Mall;
import cabletie.cms.ops.operationDBModel.infra.Station.Station;
import cabletie.cms.ops.operationDBModel.infra.Warehouse.Warehouse;


@Controller
@SessionAttributes("name")
public class InfrastructureController {

	@Autowired
	private DepotService depotSvc;
	@Autowired
	private MallService mallSvc;
	@Autowired
	private StationService stnSvc;
	@Autowired
	private WarehouseService warehouseSvc;
	
	
    @GetMapping("/infra-addnew")
    public String addNewInfraView(Model model) {
    	String type="";
    	List<Station> stationList = new ArrayList<>();

		//Eliminate depots from displaying
		for(Station s : stnSvc.getAllStation()) {
			type = s.getStationID();
			if(!type.substring(0,1).equalsIgnoreCase("D")) {
				stationList.add(s);
			}
		}

    	model.addAttribute("stationList", stationList);
        return "infra-addnew";
    }
    
    @PostMapping("/infra-addnew")
    public String addNewInfraView(@RequestParam("infraName") String infraName,
    		@RequestParam("infraID") String infraID,
    		@RequestParam("infraType") String infraType,
    		@RequestParam("infraLocale") String infraLocale,
    		@RequestParam(value = "stn_ID", required = false) String stationID,
    		@RequestParam(value = "prev_stn", required = false) String prevStn,
    		@RequestParam(value = "next_stn", required = false) String nextStn,
    		@ModelAttribute("name") SystemAccount user
    		) throws ParseException {
    	
    		boolean addSuccess = false;
    		
    		if(infraType.equals("Depot")) {
    			addSuccess = depotSvc.createDepot(infraID,infraName,infraLocale);
    			
    		}else if(infraType.equals("Mall")) {
    			addSuccess = mallSvc.createMall(infraID, infraName, stationID, infraLocale);
    			
    		}else if(infraType.equals("Station")) {
    			addSuccess = stnSvc.createStation(infraID, infraName, infraLocale, prevStn, nextStn);
    		}
    		else if(infraType.equals("Warehouse")) {
    			addSuccess = warehouseSvc.createWarehouse(infraID,infraName,infraLocale);
    		}
    	
    	//If add operation is successful redirect to view all item page, else throw error page
        if(addSuccess) {
        	return "redirect:/infra-viewall";
        
        }else {     	
        	return "redirect:/404";
        }
        
    }
    
    @GetMapping("/infra-viewall")
    public String viewAllInfra(@ModelAttribute("name") SystemAccount user, Model model) {
    	
    	//Get user location
    	//String userLocation = user.getStaff().getLocation();
    	//String locale = userLocation.substring(0,1);
    	
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
  	
        return "infra-viewall";
    }
    
    @GetMapping("/infra-viewdetails")
    public String viewInfraDetails(@RequestParam("sentinfraID") String infraID, @ModelAttribute("name") SystemAccount user, Model model) {
    	
    	//Get user location
    	//String userLocation = user.getStaff().getLocation();
    	//Get infra type.
    	String locale = "";
    	
    	if(infraID.length()==3) {
    		locale = infraID.substring(0,1);
    	} else if(infraID.length()==4) {
    		locale = infraID.substring(0,2);
    	}
    	model.addAttribute("locale", locale);
		if((locale.equals("D"))) {
			Depot depot = depotSvc.getDepot(infraID).get(0);
			Infrastructure infra = new Infrastructure(depot.getDepotID(),depot.getName(),depot.getLocation(),depot.getStatus());
	    	model.addAttribute("infra", infra);

		}else if((locale.equals("M"))) {
			Mall mall = mallSvc.getMall(infraID).get(0);
			Infrastructure infra = new Infrastructure(mall.getMallID(),mall.getName(),mall.getLocation(),mall.getStationID(),mall.getStatus());
	    	model.addAttribute("infra", infra);
			
		}else if((locale.equals("EW")) || (locale.equals("NS"))) {
			Station stn = stnSvc.getStation(infraID).get(0);
			Infrastructure infra = new Infrastructure(stn.getStationID(),stn.getName(),stn.getLocation(),stn.getPrevStn(),stn.getNextStn(),stn.getStatus());
			model.addAttribute("infra", infra);
		}
		else if(locale.equals("W")) {
			Warehouse ware = warehouseSvc.getWarehouse(infraID).get(0);
			Infrastructure infra = new Infrastructure(ware.getWarehouseID(),ware.getName(),ware.getLocation(),ware.getStatus());
	    	model.addAttribute("infra", infra);
		}  	
    	return "infra-viewdetails";
    }
    
    @GetMapping("/infra-editdetails")
    public String editDetailsView(@RequestParam("sentinfraID") String infraID, @ModelAttribute("name") SystemAccount user, Model model) {
    	
    	//Get user location
    	//String userLocation = user.getStaff().getLocation();
    	//Get infra type.
    	String locale = "";
    	
    	if(infraID.length()==3) {
    		locale = infraID.substring(0,1);
    	} else if(infraID.length()==4) {
    		locale = infraID.substring(0,2);
    		
    	}
    	model.addAttribute("locale", locale);
		if((locale.equals("D"))) {
			Depot depot = depotSvc.getDepot(infraID).get(0);
			Infrastructure infra = new Infrastructure(depot.getDepotID(),depot.getName(),depot.getLocation(),depot.getStatus());
	    	model.addAttribute("infra", infra);
		}else if((locale.equals("M"))) {
			Mall mall = mallSvc.getMall(infraID).get(0);
			Infrastructure infra = new Infrastructure(mall.getMallID(),mall.getName(),mall.getLocation(),mall.getStationID(),mall.getStatus());
	    	model.addAttribute("infra", infra);
		}else if((locale.equals("EW")) || (locale.equals("NS"))) {
			Station stn = stnSvc.getStation(infraID).get(0);
			Infrastructure infra = new Infrastructure(stn.getStationID(),stn.getName(),stn.getLocation(),stn.getPrevStn(),stn.getNextStn(),stn.getStatus());
	    	model.addAttribute("infra", infra);
		}
		else if(locale.equals("W")) {
			Warehouse ware = warehouseSvc.getWarehouse(infraID).get(0);
			Infrastructure infra = new Infrastructure(ware.getWarehouseID(),ware.getName(),ware.getLocation(),ware.getStatus());
	    	model.addAttribute("infra", infra);
		}

		//Eliminate depots from displaying
		String type="";
		List<Station> stationList = new ArrayList<>();
		for(Station s : stnSvc.getAllStation()) {
			type = s.getStationID();
			if(!type.substring(0,1).equalsIgnoreCase("D")) {
				stationList.add(s);
			}
		}
		model.addAttribute("stationList", stationList);
    	
    	return "infra-editdetails";
    	
    }
    
    @PostMapping("/infra-editdetails")
    public String editDetailsPost(@RequestParam("infra_id") String infraID,
    		@RequestParam("infra_name") String infraName,
    		@RequestParam("infra_location") String location,
    		@RequestParam(value="stn", required=false) String station,
    		@RequestParam(value="prev_stn", required=false) String prevStn,
    		@RequestParam(value="next_stn", required=false) String nextStn,
    		@RequestParam("infra_status") int status
    		) throws ParseException {
    	
    	
    	//Get user location
    	String locale = "";
    	
    	if(infraID.length()==3) {
    		locale = infraID.substring(0,1);
    	} else if(infraID.length()==4) {
    		locale = infraID.substring(0,2);
    	}
    	   	
		if((locale.equals("D"))) {
			Depot depot = depotSvc.getDepot(infraID).get(0);
			depot.setName(infraName);
			depot.setLocation(location);
	    	depot.setStatus(status);
	    	depotSvc.updateDepot(depot);

		}else if((locale.equals("M"))) {
			Mall mall = mallSvc.getMall(infraID).get(0);
			mall.setName(infraName);
			mall.setLocation(location);
			mall.setStatus(status);
			mall.setStationID(station);
			mallSvc.updateMall(mall);
		}else if((locale.equals("EW")) || (locale.equals("NS"))) {
			Station stn = stnSvc.getStation(infraID).get(0);
			stn.setName(infraName);
			stn.setLocation(location);
			stn.setStatus(status);
			stn.setPrevStn(prevStn);
			stn.setNextStn(nextStn);
			stnSvc.updateStation(stn);
		}
		else if(locale.equals("W")) {
			Warehouse ware = warehouseSvc.getWarehouse(infraID).get(0);
			ware.setName(infraName);
			ware.setLocation(location);
			ware.setStatus(status);
			warehouseSvc.updateWarehouse(ware);
		}
		
    	return "redirect:/infra-viewall";
    }
    
    @GetMapping("/infra-delete")
    public String editDetails(@RequestParam("sentinfraID") String infraID, @ModelAttribute("name") SystemAccount user) {

    	
    	//Get user location
    	String locale = "";
    	
    	if(infraID.length()==3) {
    		locale = infraID.substring(0,1);
    	} else if(infraID.length()==4) {
    		locale = infraID.substring(0,2);
    	}
    	   	
    	
		if((locale.equals("D"))) {
			Depot depot = depotSvc.getDepot(infraID).get(0);
	    	depot.setStatus(-1);
	    	Station stn = stnSvc.getStation(infraID).get(0);
	    	stn.setStatus(-1);
	    	depotSvc.updateDepot(depot);
	    	stnSvc.updateStation(stn);
	    	
		}else if((locale.equals("M"))) {
			Mall mall = mallSvc.getMall(infraID).get(0);
			mall.setStatus(-1);
			mallSvc.updateMall(mall);
			
		}else if((locale.equals("EW")) || (locale.equals("NS"))) {
			Station stn = stnSvc.getStation(infraID).get(0);
			stn.setStatus(-1);
			stnSvc.updateStation(stn);
		}
		else if(locale.equals("W")) {
			Warehouse ware = warehouseSvc.getWarehouse(infraID).get(0);
			ware.setStatus(-1);
			warehouseSvc.updateWarehouse(ware);
		}
		
    	return "redirect:/infra-viewall";
    }
    
	public java.sql.Date dateParser(String toConvert) throws ParseException {
    	//DateParser
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    	java.util.Date convertedDate = formatter.parse(toConvert); 
    	java.sql.Date sqlDate = new java.sql.Date(convertedDate.getTime()); 
    	
    	return sqlDate;
	}
}

