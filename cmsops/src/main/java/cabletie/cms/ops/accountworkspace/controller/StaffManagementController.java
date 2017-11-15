package cabletie.cms.ops.accountworkspace.controller;

import cabletie.cms.ops.accountworkspace.service.AcctManagementService;
import cabletie.cms.ops.accountworkspace.service.StaffManagementService;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.infrasystem.controller.Infrastructure;
import cabletie.cms.ops.infrasystem.service.DepotService;
import cabletie.cms.ops.infrasystem.service.StationService;
import cabletie.cms.ops.infrasystem.service.WarehouseService;
import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;
import cabletie.cms.ops.operationDBModel.infra.Station.Station;
import cabletie.cms.ops.operationDBModel.infra.Warehouse.Warehouse;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class StaffManagementController {

    @Autowired
    private StaffManagementService staffService;
    @Autowired
    private StaffDAO staffDAO;
    //for storing session
    @Autowired
    private SystemAccountDAO acctDAO;

    private Staff staff;
    
    private Staff selectedStaff;
    
    @Autowired
    private DepotService depotSvc;
    @Autowired
    private StationService stnSvc;
    @Autowired
    private WarehouseService warehouseSvc;
    //View all staff
    //in this case, getUserID is the webpage retrieved
    @GetMapping("/staff-int-viewall")
    public String viewAllAccounts(@ModelAttribute("name") SystemAccount acct, Model model) {

        if(acct.getUserID() == null) {
            return "redirect:/";
        }

        List<SystemAccount> thisacct = acctDAO.findByuserID(acct.getUserID());
        //find the HR role in the manager
        boolean isHR = false;
        for(int i = 0 ; i < thisacct.get(0).getRoles().size(); i++) {
        	if (thisacct.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
        		isHR = true;
        	}
        }
        if(!isHR) {
        	return "redirect:/index";
        }
 
            List<Staff> staffList = staffService.getallStaff();
            model.addAttribute("iList", staffList);
            return "staff-int-viewall";
    }


   
    //create Staff
    @GetMapping("/staff-int-addnew")
    public String create(@ModelAttribute("name") SystemAccount acct, Model model){

        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        
     
    //Get separate List of infrastructures
            List<Depot> depots = depotSvc.getAllDepot();
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
           Infrastructure infra = new Infrastructure("HQ", "HQ", "HQ", 1);
            infraList.add(infra);
            model.addAttribute("infraList", infraList);

//        //finding all avaliable staff that is not tied to any account
//        List<Staff> avaliableStaff = staffService.getAllNoAccStaff();
//        List<AccountRole> acctrole = roleService.retriveRole();
//        model.addAttribute("roleList", acctrole);
//        model.addAttribute("staffList", avaliableStaff);
        return "staff-int-addnew";
    }
    
    //create Staff
    @GetMapping("/staff-int-addnewerror")
    public String createStaff(@ModelAttribute("name") SystemAccount acct, Model model){

        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        
     
    //Get separate List of infrastructures
            List<Depot> depots = depotSvc.getAllDepot();
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
           Infrastructure infra = new Infrastructure("HQ", "HQ", "HQ", 1);
            infraList.add(infra);
            model.addAttribute("infraList", infraList);

//        //finding all avaliable staff that is not tied to any account
//        List<Staff> avaliableStaff = staffService.getAllNoAccStaff();
//        List<AccountRole> acctrole = roleService.retriveRole();
//        model.addAttribute("roleList", acctrole);
//        model.addAttribute("staffList", avaliableStaff);
        return "staff-int-addnewerror";
    }


    @PostMapping("/staff-int-addnew")
    public String createAccount(
            @RequestParam(value = "idNo") String idNo,
            @RequestParam(value = "staffLocation") String location,
            @RequestParam(value = "staffName") String name,
            @RequestParam(value = "staffGender") String gender,
            @RequestParam(value = "staffBirthday") String birthday,
            @RequestParam(value= "Contact") String contact,
            @RequestParam(value = "staffEmail") String email,
            @RequestParam(value = "staffAddress") String address ,
            @RequestParam(value = "staffNationality") String national,
            @RequestParam(value = "staffRace") String race,
            @RequestParam(value = "staffMarital") String marital,

            @RequestParam(value = "staffDepartment") String dept,
            @RequestParam(value = "staffPosition") String position,
            @RequestParam(value = "ContractStart") String start,
            @RequestParam(value = "ContractEnd") String end,

            @RequestParam(value = "Salary") int salary,
            Model model){

        //after retrieving call the create account method
        if ( staffService.createStaff(location, idNo, name, gender, birthday, contact, email, address, national, race,
                marital, dept, position, start, end, salary)) {
            return "redirect:/staff-int-viewall";
        } else {
            return "redirect:/staff-int-addnewerror";
        }

    }


    //Edit Staff
    @GetMapping("/staff-int-editdetails")
    public String editStaff(@RequestParam(value="selected") String username, @ModelAttribute(value = "name") SystemAccount sys, Model model) {
        //retrieving details from the view side
        //if session is cleared
        if(sys.getUserID() == null) {
            return "redirect:/";
        }
        //sample staff ID
        List<Staff> staffList = staffService.getStaff(username);
        model.addAttribute("staff", staffList.get(0));
        //Get separate List of infrastructures
        List<Depot> depots = depotSvc.getAllDepot();
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
       Infrastructure infra = new Infrastructure("HQ", "HQ", "HQ", 1);
        infraList.add(infra);
        model.addAttribute("infraList", infraList);
        selectedStaff = staffList.get(0);
        //after retrieving call the create account method

        return "staff-int-editdetails";
    }

    //Edit Account (Edit)
    @PostMapping("/staff-int-editdetails")
    public String saveStaff(

            @RequestParam(value = "idNo") String idNo,
            @RequestParam(value = "staffLocation") String location,
            @RequestParam(value = "staffName") String name,
            @RequestParam(value = "staffGender") String gender,
            @RequestParam(value = "staffBirthday") String birthday,
            @RequestParam(value= "Contact") String contact,
            @RequestParam(value = "staffEmail") String email,
                            @RequestParam(value = "staffAddress") String address ,
                            @RequestParam(value = "staffNationality") String national,
            @RequestParam(value = "staffRace") String race,
                            @RequestParam(value = "staffMarital") String marital,

            @RequestParam(value = "staffDepartment") String dept,
            @RequestParam(value = "staffPosition") String position,
            @RequestParam(value = "ContractStart") String start,
            @RequestParam(value = "ContractEnd") String end,

            @RequestParam(value = "Salary") int salary,
            @RequestParam(value = "staffPenalty") int penalty,
            @RequestParam(value = "staffPromotion") String promotion,
                              Model model){

        List<Staff> staffs = staffDAO.findByIdNo(idNo);
        Staff staff = staffs.get(0);

        //save details into staff object
        staff.setLocation(location);
        staff.setName(name);
        staff.setGender(gender);
        staff.setDateOfBirth(birthday);
        staff.setContactNo(contact);
        staff.setEmail(email);
        staff.setAddress(address);
        staff.setNationality(national);
        staff.setRace(race);
        staff.setMaritalStatus(marital);
        staff.setDepartment(dept);
        staff.setPosition(position);
        staff.setContractStart(start);
        staff.setContractEnd(end);
        staff.setSalary(salary);
        staff.setNumOfPenalty(penalty);
        staff.setPromotionEligibility(promotion);

        staffDAO.save(staff);
      //  staffService.editStaff(staff);
        return "redirect:/staff-int-viewall";
    }



    //Edit Staff (self profile)
    @GetMapping("/staff-editdetails")
    public String editAccount(@ModelAttribute(value = "name") SystemAccount sys, Model model) {
        //retrieving details from the view side
        //if session is cleared
        if(sys.getStaff() == null) {
            return "redirect:/";
        }
        //sample staff ID
        String staffID = sys.getStaff().getStaffId();
        List<Staff> accountList = staffService.getStaff(staffID);
        staff = accountList.get(0);
        model.addAttribute("staff", accountList.get(0));
        	
        return "staff-editdetails";
    }

    //Edit Account (Edit)
    @PostMapping("/staff-editdetails")
    public String saveAccount(@RequestParam(value = "StaffName") String name,
                              @RequestParam(value = "StaffAddress") String address ,
                              @RequestParam(value = "StaffEmail") String email,
                              @RequestParam(value = "StaffNationality") String national,
                              @RequestParam(value = "StaffMarital") String marital,
                              Model model){

        //save details into staff object
        staff.setName(name);
        staff.setAddress(address);
        staff.setEmail(email);
        staff.setNationality(national);
        staff.setMaritalStatus(marital);

        staffService.editStaff(staff);
        return "redirect:/staff-viewdetails";
    }

    @GetMapping("/deleteStaff")
    public String deleteAccount(@RequestParam(value = "selected") String username) {

        List<Staff> foundStaff = staffDAO.findByStaffId(username);
        foundStaff.get(0).setStatus("deleted");
        foundStaff.get(0).getAccount().setStatus("deleted");
        staffDAO.save(foundStaff);
      
        
        return "redirect:/staff-int-viewall";
    }



    //Retrieve a staff
    @GetMapping("/staff-int-viewdetails")
    public String staffDetails(@RequestParam (value = "selected") String staffID,
                               @ModelAttribute(value = "name") SystemAccount sys, Model model) {
        //retrieve staff to be viewed from the front end
        //if session is cleared
//        if(sys.getStaff() == null) {
//            return "redirect:/";
//        }
//
//        String staffID = sys.getStaff().getStaffId();
        List<Staff> accountList = staffService.getStaff(staffID);
        model.addAttribute("staff", accountList.get(0));
        model.addAttribute("staffID", accountList.get(0).getStaffId());
        return "staff-int-viewdetails";
    }


    //Retrieve a staff
    @RequestMapping("/staff-viewdetails")
    public String viewStaffDetails(@ModelAttribute(value = "name") SystemAccount sys, Model model) {
        //retrieve staff to be viewed from the front end
        //if session is cleared
        if(sys.getStaff() == null) {
            return "redirect:/";
        }

        String staffID = sys.getStaff().getStaffId();
        List<Staff> accountList = staffService.getStaff(staffID);
        model.addAttribute("staff", accountList.get(0));
        return "staff-viewdetails";
    }


//    //Retrieve all staffs
//    @RequestMapping("/retrieveAllStaff")
//    public  ResponseEntity<List<Staff>> retrieveAllStaff() {
//
//        List<Staff> accountList = staffService.getallStaff();
//
//        return new ResponseEntity<List<Staff>>(accountList, HttpStatus.OK);
//    }
//



    //View all deleted accounts for auditing
    //in this case, getUserID is the webpage retrieved
    @RequestMapping("/viewDeletedStaffList")
    public ResponseEntity<List<Staff>>  viewAllDeletedAccounts() {
        List<Staff> accountList = staffService.getDeletedStaff();
        return new ResponseEntity<List<Staff>>(accountList, HttpStatus.OK);

    }




    //this method validates the number of characters in the string provided by the user.
    public boolean methodtoCheckCharacters(String word) {
        int length = word.length();
        if (length > 45) {
            return false;
        } else {
            return true;
        }

    }

}
