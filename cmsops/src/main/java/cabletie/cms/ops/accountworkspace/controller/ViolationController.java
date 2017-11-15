package cabletie.cms.ops.accountworkspace.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cabletie.cms.ops.corporateDBDao.ViolationDAO;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBDao.ViolationDAO;
import cabletie.cms.ops.corporateDBModel.Violation;
import cabletie.cms.ops.corporateDBModel.Appraisal;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.corporateDBModel.Violation;

@Controller
@SessionAttributes("name")
public class ViolationController {

    @Autowired
    private SystemAccountDAO acctDAO;
    @Autowired
    private StaffDAO staffdao;
    @Autowired
    private ViolationDAO Violation;
    
    private String username;
	 //create Violation
    @GetMapping("/staff-createViolation")
    public String createViolation(@ModelAttribute("name") SystemAccount acct, Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        //only supervisor or manager can create Violation
        if(!acct.getUserGroup().equalsIgnoreCase("manager")) {
        	return "redirect:/index";
        }
        
        //finding all staff under that manager (using staff's department and systemaccount's usergroup)
        List<Staff> underStaff = new ArrayList<Staff>();
        List<Staff> findStaff = staffdao.findByDepartment(acct.getStaff().getDepartment());
        
        if(findStaff.size() > 0) {
        for(int i = 0; i < findStaff.size();i++) {
        	if(findStaff.get(i).getAccount() != null) {
        	if(findStaff.get(i).getAccount().getUserGroup().equalsIgnoreCase("staff")) {
        		underStaff.add(findStaff.get(i));
        	}
        	}
        }
        }
        //finding the current quarter
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        int month = Calendar.getInstance().get(Calendar.MONTH);
//        int quarter = (month/3) + 1;
//        
//        String currentQuarter = Integer.toString(year) + ", Q" + Integer.toString(quarter);
        
//        model.addAttribute("quarter", currentQuarter);
        model.addAttribute("staffList", underStaff);
        return "staff-createViolation";
    }

    @PostMapping("/staff-createViolation")
    public String Violation(
    		@RequestParam(value = "description") String description,
            @RequestParam(value = "selectedStaff", required = false) String users,
             Model model) {
    	
    	//increment number of penalties
        String[] parts = users.split("/");
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
    	List<Staff> staff = staffdao.findByAccount(parts[1]);
     int numofoffence = staff.get(0).getNumOfPenalty();
     numofoffence += 1;
     staff.get(0).setNumOfPenalty(numofoffence);
    		//save a new violation
    	Violation vio = new Violation(username, parts[1], description, timeStamp);
    	Violation.save(vio);

    	return "redirect:/staff-viewViolation";
    }
    
    //view sent Violation
    @GetMapping("/staff-viewViolation")
    public String viewSentViolation(@ModelAttribute("name") SystemAccount acct, Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        List<Violation> sender = new ArrayList<Violation>();
        String individual = "";
        //only supervisor or manager can view sent Violation
        if(!acct.getUserGroup().equalsIgnoreCase("manager")) {
        	sender = Violation.findByReceiver(username);
        	individual = "receiver";
        } else {
        //finding all the Violation sent
        sender = Violation.findBySender(username);
        individual = "sender";
        }
        model.addAttribute("senderList", sender);
        model.addAttribute("individual", individual);
        return "staff-viewViolation";
    }
    
    //view All Violation
    @GetMapping("/staff-viewAllViolations")
    public String viewallViolation(@ModelAttribute("name") SystemAccount acct, Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        List<SystemAccount> accty = acctDAO.findByuserID(username);
        //find the HR role in the manager
        boolean isHR = false;
        for(int i = 0 ; i < accty.get(0).getRoles().size(); i++) {
        	if (accty.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
        		isHR = true;
        	}
        }
       if(!isHR) {
    	   return "redirect:/index";
       } 
       
        List<Violation> all = Violation.findAll();
        model.addAttribute("senderList", all);
 
        return "staff-viewAllViolations";
    }
    
  //view appraisal details
    @GetMapping("/staff-viewViolationDetail")
    public String viewAppraisalDetail(@ModelAttribute("name") SystemAccount acct, 
    		@RequestParam(value = "selected") String selected,
    		Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        List<Violation> all = Violation.findByviolationID(selected);
        model.addAttribute("appraise", all.get(0));
        
        String user = all.get(0).getReceiver();
        List<SystemAccount> accty = acctDAO.findByuserID(user);
        
        model.addAttribute("num", Integer.toString(accty.get(0).getStaff().getNumOfPenalty()));
        model.addAttribute("position", accty.get(0).getStaff().getPosition());
        model.addAttribute("staffID", accty.get(0).getStaff().getStaffId());
        //find the HR role in the manager
        boolean isHR = false;
        for(int i = 0 ; i < accty.get(0).getRoles().size(); i++) {
        	if (accty.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
        		isHR = true;
        	}
        }
       if(isHR) {
    	   model.addAttribute("HR", "yes");
       } else {
    	   model.addAttribute("HR", "no");
       }
        return "staff-viewViolationDetail";
    }
    
  //view appraisal details
    @GetMapping("/staff-viewAllViolationDetail")
    public String viewAllAppraisalDetail(@ModelAttribute("name") SystemAccount acct, 
    		@RequestParam(value = "selected") String selected,
    		Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        List<Violation> all = Violation.findByviolationID(selected);
        model.addAttribute("appraise", all.get(0));
        
        String user = all.get(0).getReceiver();
        List<SystemAccount> accty = acctDAO.findByuserID(user);
        
        model.addAttribute("num", Integer.toString(accty.get(0).getStaff().getNumOfPenalty()));
        model.addAttribute("position", accty.get(0).getStaff().getPosition());
        model.addAttribute("staffID", accty.get(0).getStaff().getStaffId());
        //find the HR role in the manager
        boolean isHR = false;
        for(int i = 0 ; i < accty.get(0).getRoles().size(); i++) {
        	if (accty.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
        		isHR = true;
        	}
        }
       if(isHR) {
    	   model.addAttribute("HR", "yes");
       } else {
    	   model.addAttribute("HR", "no");
       }
        return "staff-viewAllViolationDetail";
    }
}

