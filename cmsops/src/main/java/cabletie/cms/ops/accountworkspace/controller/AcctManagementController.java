package cabletie.cms.ops.accountworkspace.controller;

import cabletie.cms.ops.accountworkspace.service.AccountRoleService;
import cabletie.cms.ops.accountworkspace.service.StaffManagementService;
import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.accountworkspace.service.AcctManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cabletie.cms.ops.accountworkspace.service.EmailServiceImpl;
@Controller
@SessionAttributes("name")
public class AcctManagementController {
    @Autowired
    private AcctManagementService accService;
    @Autowired
    private StaffManagementService staffService;
    @Autowired
    private AccountRoleService roleService;

    
    @Autowired
    private EmailServiceImpl emailService;
    
    
    
    
    private SystemAccount selectedacct;
    //Edit Account
    @RequestMapping("/deleteAccount")
    public String deleteAccount() {
        //retrieving account userID to be deleted from the view side
        //sample acct
        String userID = "sample";
        //after retrieving call the create account method
        if (accService.deleteAccount(userID)) {
            return "Account deleted successfully";
        } else {
            return "Account might have already been deleted";
        }
    }

    //Reset Account password
    @GetMapping("/staff-editpassword")
    public String resetPassword(@ModelAttribute("name") SystemAccount acct) {

        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        //retrieving account userID, old and new password from the view side
        //sample acct
        //after retrieving call the create account method
      return "staff-editPassword";
    }

    //Reset Account password
    @PostMapping("/staff-editpassword")
    public String resetPasswordPost(@RequestParam(value = "oldPassword") String oldpass,
                                    @RequestParam(value = "newPassword") String newpass,
                                    @RequestParam(value = "newPassword2") String newpass2,
                                    @ModelAttribute("name") SystemAccount acct,
                                    Model map) {
        //retrieving account userID, old and new password from the view side
        //sample acct

        //after retrieving call the create account method
        if (accService.resetPassword(acct, oldpass, newpass, newpass2)) {
            return "redirect:/index";
        } else {
            return "staff-editPassword";
        }
    }


    //View all accounts
    //in this case, getUserID is the webpage retrieved
    @GetMapping("/viewAllAccounts")
    public String viewAllAccounts(@ModelAttribute("name") SystemAccount acct, Model model) {

        if(acct.getUserID() == null) {
            return "redirect:/";
        }

        List<SystemAccount> accountList = accService.getAllSystemAccount();
        model.addAttribute("iList", accountList);
        return "acct-int-viewall";
    }

//    @PostMapping("/viewAllAccounts")
//    public String grabOneAccount(@RequestParam(value = "selected") String selected) {
//        //manipuate via account's username
//
//        List<SystemAccount> accountList = accService.getAllSystemAccount();
//        return "redirect:/retrieveAccount";
//    }



    //Retrieve a single account
//    //in this case, getUserID is the webpage retrieved
//    @GetMapping("/retrieveAccount")
//    public String retrieveAccount(@ModelAttribute("name") SystemAccount acct) {
//
//        if(account.getUserID() == null) {
//            return "redirect:/";
//        }
//
//        //retrive userID from the view layer
//        //find by the userID retrieved
////        String userID = "123123";
////        List<SystemAccount> accountList = accService.getSingleAccount(userID);
////        if(accountList.isEmpty()){
////            return new SystemAccount();
////        } else {
////            return accountList.get(0);
////        }
//        return "acct-int-viewdetails";
//    }

//    @PostMapping("/retrieveAccount")
//    public String retrieveAccountDetails(){
//
//    }


    //create Account (this ties the selected staff id to the created account)
    @GetMapping("/acct-int-addnew")
    public String create(@ModelAttribute("name") SystemAccount acct, Model model){

        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        //finding all avaliable staff that is not tied to any account
        List<Staff> avaliableStaff = staffService.getAllNoAccStaff();
        List<AccountRole> acctrole = roleService.retriveRole();
        model.addAttribute("roleList", acctrole);
        model.addAttribute("staffList", avaliableStaff);
        return "acct-int-addnew";
    }

    @PostMapping("/acct-int-addnew")
    public String createAccount(@RequestParam(value = "roles", required = false) List<AccountRole> role,
            @RequestParam(value = "username") String name,
               
    @RequestParam(value = "usergroup") String group, @RequestParam(value= "selectedStaff") String staffselected, Model model){

    	//first time creates a random password.
    	String pass = String.valueOf(System.nanoTime());
        String staffid = "1";
        if(!staffselected.isEmpty()) {
            //extract ID from selectedStaff
            String[] parts = staffselected.split("/");
            staffid = parts[1];
        }
        //after retrieving call the create account method
        if (accService.createAccount(staffid, name, pass, group, role)) {
        	
        	
        	//send an email to the account
        	try {
				emailService.sendEmail("cabletie3014@gmail.com", "Account creation", "Please kindly note that your account has been created with the password: " + pass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
            return "redirect:/viewAllAccounts";
        } else {
            return "redirect:/acct-int-addnew";
        }

    }



    //Edit Account
    @GetMapping("/acct-int-editdetails")
    public String editAccount(Model model, @RequestParam(value="selected") String username, @ModelAttribute("name") SystemAccount acct) {
        //retrieving details from the view side
        //sample acct
        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        //for sample case
        //search for the selected userID
        List<SystemAccount> account = accService.getSingleAccount(username);
        List<AccountRole> acctrole = roleService.retriveRole();
        List<AccountRole> role = accService.getAllRoles(username);
        //loop to display the roles
        String s = "";
        if(!role.isEmpty()) {

            for (int i = 0; i < role.size(); i++) {
                if (i == role.size() - 1) {
                    s += role.get(i).getRoleID();
                } else {
                    s += role.get(i).getRoleID() + ", ";
                }
            }
        }

        model.addAttribute("displayRole", s);
        model.addAttribute("roleList", acctrole);
        selectedacct = account.get(0);
        model.addAttribute("acct",account.get(0));
        //after retrieving call the create account method

        return "acct-int-editdetails";
    }

//
    @PostMapping("/acct-int-editdetails")
    public String saveEditAccount(
                                 @RequestParam(value = "roles", required = false) List<AccountRole> role,
                                  @RequestParam(value = "usergroup") String usergroup,
                                  @RequestParam(value = "status") String status,
                                  Model map){

//        selectedacct.setPassword(password);
        selectedacct.setUserGroup(usergroup);
        //remove users from roles
        List<AccountRole> currentRoles = accService.getAllRoles(selectedacct.getUserID());

        if(!currentRoles.isEmpty()) {
            roleService.deleteRoleUser(selectedacct, currentRoles);
        }
        //saved the editing (save new roles to users and users to new roles)
        accService.editAccount(selectedacct, role);


        if(!status.isEmpty()){
            accService.deleteAccount(selectedacct.getUserID());
        }

//        // selectedAcc.setRoles(accountRole);

       // acctService.tagRole(selectedAcc.getUserID(), accountRole);
      return "redirect:/viewAllAccounts";
    }












    //View all deleted accounts for auditing
    //in this case, getUserID is the webpage retrieved
    @RequestMapping("/viewDeletedAccountList")
    public List<SystemAccount> viewAllDeletedAccounts() {
        List<SystemAccount> accountList = accService.getAllDeletedSystemAccount();
        return accountList;

    }


    //Find all user's roles
    @RequestMapping("/findAllUserRoles")
    public ResponseEntity<List<AccountRole>> viewAllUsersRoles() {
        //sample user
        String userID = "sample2";

        List<AccountRole> roleList = accService.getAllRoles(userID);

        return new ResponseEntity<List<AccountRole>>(roleList, HttpStatus.OK);

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
