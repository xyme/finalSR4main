package cabletie.cms.ops.accountworkspace.controller;

import java.sql.Timestamp;
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

import cabletie.cms.ops.corporateDBDao.AppraisalDAO;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.Appraisal;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;

@Controller
@SessionAttributes("name")
public class AppraisalController {

    @Autowired
    private SystemAccountDAO acctDAO;
    @Autowired
    private StaffDAO staffdao;
    @Autowired
    private AppraisalDAO appraisal;
    
    private String username;
	 //create Appraisal
    @GetMapping("/staff-createAppraisal")
    public String createAppraisal(@ModelAttribute("name") SystemAccount acct, Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        //only supervisor or manager can create appraisal
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
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int quarter = (month/3) + 1;
        
        String currentQuarter = Integer.toString(year) + ", Q" + Integer.toString(quarter);
        
        model.addAttribute("quarter", currentQuarter);
        model.addAttribute("staffList", underStaff);
        return "staff-createAppraisal";
    }

    @PostMapping("/staff-createAppraisal")
    public String appraisal(@RequestParam(value = "currentQuarter") String currentQuarter,
    		@RequestParam(value = "description") String description,
            @RequestParam(value = "selectedStaff", required = false) String users,
            @RequestParam(value = "consideration") String consideration, Model model) {
    	
    	//extract the staff userID out
        String[] parts = users.split("/");
    	//see if appraisal is already submitted in that specific quarter
    	List<Appraisal> sender = appraisal.findBySender(username);
    	Appraisal appraise = new Appraisal();
    	boolean appraised = false;
    	for (int i = 0 ; i < sender.size(); i++) {
    		if(sender.get(i).getQuarter().equalsIgnoreCase(currentQuarter) && sender.get(i).getReceiver().equalsIgnoreCase(parts[1])) {
    			appraised = true;
    			appraise = sender.get(i);
    		}
    	}
    	

    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
      
    	if(appraised) {
    		//edit the appraisal sent
    		appraise.setDescription(description);
    		appraise.setDate(timeStamp);
    		appraisal.save(appraise);
    		
    	} else {
    		//save a new apraisal
    	Appraisal appra = new Appraisal(username, parts[1], description, consideration, currentQuarter, timeStamp);
    	appraisal.save(appra);
    	}
    	return "redirect:/staff-viewAppraisal";
    }
    
    //view sent Appraisal
    @GetMapping("/staff-viewAppraisal")
    public String viewSentAppraisal(@ModelAttribute("name") SystemAccount acct, Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        
        List<Appraisal> sender = new ArrayList<Appraisal>();
        String individual = "";
        //only supervisor or manager can view sent appraisal
        if(!acct.getUserGroup().equalsIgnoreCase("manager")) {
        	sender = appraisal.findByReceiver(username);
        	individual = "receiver";
        } else {
        //finding all the appraisal sent
        sender = appraisal.findBySender(username);
        individual = "sender";
        }
        model.addAttribute("senderList", sender);
        model.addAttribute("individual", individual);
        return "staff-viewAppraisal";
    }
    
    //view All appraisal
    @GetMapping("/staff-viewAllAppraisal")
    public String viewallAppraisal(@ModelAttribute("name") SystemAccount acct, Model model) {

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
        List<Appraisal> all = appraisal.findAll();
        model.addAttribute("senderList", all);
        
        return "staff-viewAllAppraisal";
    }
    
    //view appraisal details
    @GetMapping("/staff-viewAppraisalDetail")
    public String viewAppraisalDetail(@ModelAttribute("name") SystemAccount acct, 
    		@RequestParam(value = "selected") String selected,
    		Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
   
        List<Appraisal> all = appraisal.findByappraisalID(selected);
        model.addAttribute("appraise", all.get(0));
        
        String user = all.get(0).getReceiver();
        List<SystemAccount> accty = acctDAO.findByuserID(user);
        
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
        return "staff-viewAppraisalDetail";
    }
    
  //view appraisal details
    @GetMapping("/staff-viewAllAppraisalDetail")
    public String viewAllAppraisalDetail(@ModelAttribute("name") SystemAccount acct, 
    		@RequestParam(value = "selected") String selected,
    		Model model) {

    	username = acct.getUserID();
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
   
        List<Appraisal> all = appraisal.findByappraisalID(selected);
        model.addAttribute("appraise", all.get(0));
        
        String user = all.get(0).getReceiver();
        List<SystemAccount> accty = acctDAO.findByuserID(user);
        
        model.addAttribute("position", accty.get(0).getStaff().getPosition());
        model.addAttribute("staffID", accty.get(0).getStaff().getStaffId());
        model.addAttribute("staffEligibility", accty.get(0).getStaff().getPromotionEligibility());
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
        return "staff-viewAllAppraisalDetail";
    }
}
