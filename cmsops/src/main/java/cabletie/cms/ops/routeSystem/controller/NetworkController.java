package cabletie.cms.ops.routeSystem.controller;

import java.text.ParseException;
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
import cabletie.cms.ops.operationDBDao.train.TrainDAO;
import cabletie.cms.ops.operationDBModel.routeTime.RouteTrain;
import cabletie.cms.ops.operationDBModel.train.Train;
import cabletie.cms.ops.routeSystem.service.AllocationService;
import cabletie.cms.ops.routeSystem.service.TimetableService;

@Controller
@SessionAttributes("name")
public class NetworkController {
	
	private int currBlockLength = 200; 					//used by block design | REPLACE with actual value from backend
	private int currStnStopTime = 30; 					//used by block design | REPLACE with actual value from backend
	private int calTime = (currBlockLength-1)/60 + 1;		//used by block design
	
	private ArrayList<String> assignment;			//used by trainroutedriver assignment | REPLACE with actual assignment from backend
	private ArrayList<ArrayList> allAssignmentsNP;		//used by trainroutedriver assignment | REPLACE with actual assignment from backend
	private ArrayList<ArrayList> allAssignmentsP;
	private ArrayList<String> allRouteGroupsDropdownNP; 	//used by trainroutedriver assignment | REPLACE with service numbers from backend
	private ArrayList<String> allRouteGroupsDropdownP;
	private ArrayList<String> allTrainsDropdownNP; 		//used by trainroutedriver assignment | REPLACE with actual available RS from backend
	private ArrayList<String> allTrainsDropdownP;
	//private ArrayList<String> allDriversDropdown; 	//used by trainroutedriver assignment | REPLACE with actual drivers from backend
	
	private ArrayList<String> stationsEW;		//used by timetable viewall 
	private ArrayList<ArrayList> timetableEW;
	private ArrayList<String> stationsWE;		//used by timetable viewall 
	private ArrayList<ArrayList> timetableWE;
	private ArrayList<String> stationsNS; 		//used by timetable viewall 
	private ArrayList<ArrayList> timetableNS; 	
	private ArrayList<String> stationsSN; 
	private ArrayList<ArrayList> timetableSN; 
	
	private ArrayList<String> stationsWkendEW;		//used by timetable viewall 
	private ArrayList<ArrayList> timetableWkendEW;
	private ArrayList<String> stationsWkendWE;		//used by timetable viewall 
	private ArrayList<ArrayList> timetableWkendWE;
	private ArrayList<String> stationsWkendNS; 		//used by timetable viewall 
	private ArrayList<ArrayList> timetableWkendNS; 	
	private ArrayList<String> stationsWkendSN; 
	private ArrayList<ArrayList> timetableWkendSN; 

	
	@Autowired
	TimetableService timeTableSvc;
	@Autowired
	AllocationService allosvc;
	
	@Autowired
	TrainDAO trainDAO;
	
	
	// $$$ VIEW ALL WEEKEND TIMETABLE VALUES $$$ //
	 @GetMapping("/route-timetable-wkend-viewall")
	    public String viewWkendTimetable(@ModelAttribute("name") SystemAccount acct, Model model) {
		    	
		    populateHeaderEW();
		    populateScheduleWkendEW();
		    model.addAttribute("ewStations", stationsEW);
		    model.addAttribute("allSchedulesEW", timetableWkendEW);
		    populateHeaderWE();
		    populateScheduleWkendWE();
		    model.addAttribute("weStations", stationsWE);
		    model.addAttribute("allSchedulesWE", timetableWkendWE);
		    populateHeaderNS();
		    populateScheduleWkendNS();
		    model.addAttribute("nsStations", stationsNS);
		    model.addAttribute("allSchedulesNS", timetableWkendNS);
		    populateHeaderSN();
		    populateScheduleWkendSN();
		    model.addAttribute("snStations", stationsSN);
		    model.addAttribute("allSchedulesSN", timetableWkendSN);

		    
		    	return "route-timetable-wkend-viewall";
	    }
	 
	
	
	// $$$ VIEW ALL WEEKDAY TIMETABLE VALUES $$$ //
	 @GetMapping("/route-timetable-wkday-viewall")
	    public String viewWkdayTimetable(@ModelAttribute("name") SystemAccount acct, Model model) {
		    	
		    populateHeaderEW();
		    populateScheduleEW();
		    model.addAttribute("ewStations", stationsEW);
		    model.addAttribute("allSchedulesEW", timetableEW);
		    populateHeaderWE();
		    populateScheduleWE();
		    model.addAttribute("weStations", stationsWE);
		    model.addAttribute("allSchedulesWE", timetableWE);
		    populateHeaderNS();
		    populateScheduleNS();
		    model.addAttribute("nsStations", stationsNS);
		    model.addAttribute("allSchedulesNS", timetableNS);
		    populateHeaderSN();
		    populateScheduleSN();
		    model.addAttribute("snStations", stationsSN);
		    model.addAttribute("allSchedulesSN", timetableSN);

		    
		    	return "route-timetable-wkday-viewall";
	    }
	 
	 
	 
	 
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateHeaderEW() {
			
			stationsEW = new ArrayList<String>(); // Header row
			
			// ==============================
			// HOW TO USE:
			// >>> populate arraylist "stationsEW" with your stations names (string data type)
			// ==============================

			// *** FOR DEMO ONLY *** //
			
			for (int i=1; i<=22; i++) { // 22 Stations
				stationsEW.add("EW" + String.valueOf(i));
			}
			
			stationsEW.add("Route Type");
			
		}
		
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateHeaderWE() {
			
			stationsWE = new ArrayList<String>(); // Header row
			
			// ==============================
			// HOW TO USE:
			// >>> populate arraylist "stationsEW" with your stations names (string data type)
			// ==============================

			// *** FOR DEMO ONLY *** //
			
			for (int i=22; i>=1; i--) { // 22 Stations
				stationsWE.add("EW" + String.valueOf(i));
			}
			stationsWE.add("Route Type");
			
		}
		
	 
		
	 	// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateHeaderNS() {
			
			stationsNS = new ArrayList<String>(); // Row Header

			
			for (int i=1; i<=15; i++) {  //15 Stations
				stationsNS.add("NS" + String.valueOf(i));
			}
			
			stationsNS.add("Route Type"); // Route Type
			
		}
		
		private void populateHeaderSN(){
			
			stationsSN = new ArrayList<String>(); // Row Header

			for (int i=15; i>=1; i--) {  //15 Stations
				stationsSN.add("NS" + String.valueOf(i));
			}
			
			stationsSN.add("Route Type"); // Route Type
		}
		

		
		
		
		
		
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateScheduleEW() {
			timetableEW = timeTableSvc.getEWDay()
			;
		}
		
		private void populateScheduleWE() {
			timetableWE = timeTableSvc.getWEDay();
			
		}
		
		
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateScheduleNS() {
			timetableNS = timeTableSvc.getNSDay();
			
			//CALL SVC to populate.
			
		}
		
		
		private void populateScheduleSN() {
			timetableSN = timeTableSvc.getSNDay();
			
		}
		
		
		
		
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateScheduleWkendEW() {
			timetableWkendEW = timeTableSvc.getEWend();
		}
		
		private void populateScheduleWkendWE() {
			timetableWkendWE = timeTableSvc.getWEend();
			
		}
		
		
		// $$$ VIEW ALL TIMETABLE VALUES $$$ //
		private void populateScheduleWkendNS() {
			timetableWkendNS = timeTableSvc.getNSend();

			
		}
		
		
		private void populateScheduleWkendSN() {
			timetableWkendSN = timeTableSvc.getSNend();
			
		}
		
		

	
		
		
		
		
	
	// $$$ BLOCK DESIGN $$$ //
	@GetMapping("/route-blockdesign-viewedit")
    public String displayBlockDetailsPage(@ModelAttribute("name") SystemAccount acct, Model model) {
		
		model.addAttribute("stopTime", currStnStopTime);
		model.addAttribute("inputLength", currBlockLength);
		model.addAttribute("calTheTime", calTime);
		return "route-blockdesign-viewedit";
	}
	
	// $$$ BLOCK DESIGN $$$ //
	@PostMapping("/route-blockdesign-viewedit")
	public String enterValues(
			@RequestParam("stnStopTime") int enteredStopTime,
			@RequestParam("lengthInput") int enteredLength,
			@ModelAttribute("name") SystemAccount user,
			Model model
			) throws ParseException {
		
		currStnStopTime = enteredStopTime; //update the stop time in backend
		currBlockLength = enteredLength; //update the block length in backend 
		calTime = (currBlockLength-1)/60 + 1; 
		
		//DEBUG
		System.out.println("[DEBUG] This is the stop time you entered: " + enteredStopTime);
		//DEBUG
		System.out.println("[DEBUG] This is the block length you entered: " + enteredLength);
		//DEBUG
		System.out.println("[DEBUG] This is the calculated time: " + calTime);
		
		return "redirect:/route-blockdesign-viewedit";
	}

	
	
	
	// $$$ INTERSTATION TRAVELING DETAILS $$$ //
	@GetMapping("/route-interstationdetails-viewall")
    public String viewInterstationDetails(@ModelAttribute("name") SystemAccount acct, Model model) {
		
		return "route-interstationdetails-viewall";
	}
	
	
	

	

		@GetMapping("/route-trainroute-viewedit")
	    public String viewtraintrouteass(@ModelAttribute("name") SystemAccount acct, Model model) {

			populateTrainAssignmentNP();
			populateTrainAssignmentP();
//			populateAllServicesDropdown();
//			populateAvailRSDropdown();
//			populateAvailDriversDropdown();
			

			model.addAttribute("nonpeakTrainRouteAssignment", allAssignmentsNP);
			model.addAttribute("nonpeakRouteGroupsDropdown", allAssignmentsNP);
			model.addAttribute("peakTrainRouteAssignment", allAssignmentsP);
			model.addAttribute("peakRouteGroupsDropdown", allAssignmentsP);
//			model.addAttribute("allTrains_P", allTrainsDropdownP);
			model.addAttribute("allTrains_NP", allTrainsDropdownNP);
			
			return "route-trainroute-viewedit";
		}
		

		@GetMapping("/route-train-routegroup-viewedit")
	    public String viewtrainroutegroup(@ModelAttribute("name") SystemAccount acct, Model model) {
			
			//Get List of Train-Route Mappings
			List<RouteTrain> trainRouteList = allosvc.getTrainAssignment();
			
			model.addAttribute("trainRouteList", trainRouteList);
			
			
			//Get List of Available Train
			
		    //All trains
			List<Train> trainList = trainDAO.findBystatus(1);
			
			//Filtered List of trains
			ArrayList<Train> unassignedList = new ArrayList<Train>();
			
			for(int i=0;i<trainList.size();i++) {
				
				//Check current train in full list to see if exist in trainroutelist
				boolean alreadyAssigned = false;
				
				for(int j=0; j<trainRouteList.size();j++) {
					
					if(trainList.get(i).getTrainId().equals(trainRouteList.get(j).getTrainID())) {
						alreadyAssigned = true;
					}
				}
				
				if(!alreadyAssigned) {
					
					unassignedList.add(trainList.get(i));
				}
				
			}
			
			model.addAttribute("unassignedList",unassignedList);
			
			return "route-train-routegroup-viewedit";
		}

		
		@GetMapping("/route-train-routegroup-autogen")
	    public String autogenTrainGrp(@ModelAttribute("name") SystemAccount acct, Model model) {
			
			allosvc.autoTrainRoute();
			
			return "redirect:/route-train-routegroup-viewedit";
		}
		
		@PostMapping("/route-train-routegroup-editass")
	    public String editass(@ModelAttribute("name") SystemAccount acct, 
	    		@RequestParam String routeGroup, @RequestParam String train, Model model) {
			
			allosvc.editTrainRoute(routeGroup, train);
			
			return "redirect:/route-train-routegroup-viewedit";
		}
		
		@PostMapping("/route-train-routegroup-delete")
	    public String deleteass(@ModelAttribute("name") SystemAccount acct, 
	    		@RequestParam String routeGroup, Model model) {
			
			allosvc.editTrainRoute(routeGroup, "");
			
			return "redirect:/route-train-routegroup-viewedit";
		}
		
		
	// $$$ TRAIN ROUTE DRIVER ASSIGNMENT $$$ //
	private void populateTrainAssignmentNP() {
		
		//allAssignments = allosvc.generateTable();
		
		allAssignmentsNP = new ArrayList<ArrayList>();
		
		ArrayList<ArrayList> trainassign = new ArrayList<ArrayList>();
		
		if(timeTableSvc.getTimetableSettings().get(0).getFrequency()==0) {
			trainassign = allosvc.generateTable5(); //service to call allocation5

			for (int i=0; i<10; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+10).toString());
				
				
				
				allAssignmentsNP.add(assignment);
			}

			
		}else if(timeTableSvc.getTimetableSettings().get(0).getFrequency()==1) {
			trainassign = allosvc.generateTable6(); //service to call allocation5

			for (int i=0; i<8; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+8).toString());
				
				
				allAssignmentsNP.add(assignment);
			}
	
			
		}else {
			trainassign = allosvc.generateTable8(); //service to call allocation5
	
			for (int i=0; i<6; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+6).toString());

				
				allAssignmentsNP.add(assignment);
			}

		}
		
		
	}
	
	
	
	
	private void populateTrainAssignmentP() {
		
		//allAssignments = allosvc.generateTable();
		allAssignmentsP = new ArrayList<ArrayList>();
		
		ArrayList<ArrayList> trainassign = new ArrayList<ArrayList>();
		
		if(timeTableSvc.getTimetableSettings().get(0).getFrequency()==0) {
			trainassign = allosvc.generateTable5P(); //service to call allocation5
			
	
			for (int i=0; i<15; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+15).toString());
				
				allAssignmentsP.add(assignment);
			}
	
			
		}else if(timeTableSvc.getTimetableSettings().get(0).getFrequency()==1) {
			trainassign = allosvc.generateTable6P(); //service to call allocation5
			

			for (int i=0; i<8; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+8).toString());

				allAssignmentsP.add(assignment);
			}
	
			
		}else {
			trainassign = allosvc.generateTable8P(); //service to call allocation5
			
	
			for (int i=0; i<6; i++) {
				assignment = new ArrayList<String>();
				
				assignment.add("Route Groups " + String.valueOf(i+1));
				assignment.add("Routes " + trainassign.get(i).toString());
				assignment.add("Trains " + trainassign.get(i+6).toString());

								
				allAssignmentsP.add(assignment);
			}

		}
		
		
	}
	

	

		
	@GetMapping("/route-networkdesign-viewall")
	public String viewNetworkDesign(@ModelAttribute("name") SystemAccount acct, Model model) {	
		return "route-networkdesign-viewall";
	}
	

	
	
	// $$$ EDIT TIMETABLE DETAILS $$$ //
	@GetMapping("/route-timetabledesign-editdetails")
	public String viewTimetableDesignEditPage(@ModelAttribute("name") SystemAccount acct, Model model) {	
	
		return "route-timetabledesign-editdetails";
	}
	
	// $$$ EDIT TIMETABLE DETAILS $$$ //
		@PostMapping("/route-timetabledesign-editdetails")
		public String editTimetableDesignPage(
				@RequestParam("scheduleType") int scheduleType, 
				@RequestParam("frequency") int freq,
				@RequestParam("start") String start, 
				@RequestParam("end") String end,
				@ModelAttribute("name") SystemAccount user
				) throws ParseException {
			
			// >>>>> KEEP THIS <<<<< ///
			String[] startValues = start.split(",");
			int startTime = 0;
			for (int i=0; i<startValues.length; i++) {
				System.out.println("[DEBUG] Start Value: " + startValues[i]);
				if (!startValues[i].equals("")) {
					startTime = (int) Integer.valueOf(startValues[i]);
					//startTime = (int) Integer.valueOf(startValues[i].substring(1));
				}
			}
			String[] endValues = end.split(",");
			int endTime = 0;
			for (int j=0; j<endValues.length; j++) { 
				System.out.println("[DEBUG] End value:" + endValues[j]);
				if (!endValues[j].isEmpty()) {
					endTime = (int) Integer.valueOf(endValues[j]);
					//endTime = (int) Integer.valueOf(endValues[j].substring(1));
				}
			}
			// >>>>> KEEP THIS <<<<< ///

			
			timeTableSvc.setTimetableSettings(scheduleType, freq, startTime, endTime);
			
//			//DEBUG
			System.out.println("[DEBUG] Schedule Type: " + scheduleType);
//			//DEBUG
			System.out.println("[DEBUG] Frequency: " + freq);
//			//DEBUG
			System.out.println("[DEBUG] Start:" + start);
			System.out.println("[DEBUG] Start Time: " + startTime);
			System.out.println("[DEBUG] End:" + end);
			System.out.println("[DEBUG] End Time: " + endTime);
			
			return "redirect:/route-timetabledesign-editdetails";
		}
	
		
}
