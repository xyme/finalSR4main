//package cabletie.cms.ops.accountworkspace.controller;
//
//import cabletie.cms.ops.accountworkspace.service.AccountRoleService;
//import cabletie.cms.ops.corporateDBModel.AccountRole;
//import cabletie.cms.ops.corporateDBModel.SystemAccount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@SessionAttributes("name")
//@RequestMapping("/role")
//public class AccountRoleController {
//
//    @Autowired
//    private AccountRoleService accountRoleService;
//
//    //create Account (this ties the selected staff id to the created account)
//    @RequestMapping("/createRole")
//    public ResponseEntity<String> createRole() {
//
//        //grab from view
//        //sample creation
//        String roleID = "IT";
//        String roleName = "hello";
//        String s = "";
//        if(accountRoleService.createRole(roleID, roleName)){
//            s = "Created role";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        } else {
//            s = "error creating";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        }
//    }
//
//    //Edit Account
//    @RequestMapping("/editRole")
//    public ResponseEntity<String> editRole() {
//        //retrieving details from the view side
//        //sample acct
//
//        AccountRole role = new AccountRole();
//        //after retrieving call the edit role method
//        String s;
//        if (accountRoleService.editRole(role)) {
//            s = "Edited successfully";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        } else {
//            s = "Error";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        }
//    }
//
//    //Edit Account
//    @RequestMapping("/deleteRole")
//    public String deleteAccount() {
//        //retrieving role roleID to be deleted from the view side
//        //sample acct
//        String roleID = "ID";
//        //after retrieving call the create account method
//        if (accountRoleService.deleteRole(roleID)) {
//            return "Role deleted successfully";
//        } else {
//            return "Role might already been deleted";
//        }
//    }
//
//
//
//
//    //View all roles created
//    @RequestMapping("/viewRoleList")
//    public List<AccountRole> viewAllCreatedRoles() {
//        List<AccountRole> roleList = accountRoleService.retriveRole();
//        return roleList;
//
//    }
//
////    //tag roles to user
////    @RequestMapping("/taguserRole")
////    public ResponseEntity<String> tagUserRoles() {
////        //sample userid and roleid
////        String userID = "sample2";
////        String roleID = "IT";
////        String s = "";
//////        if (accountRoleService.tagRole(userID, roleID)){
//////            s = "tagged succesfully";
//////            return new ResponseEntity<String>(s, HttpStatus.OK);
//////
//////        } else {
//////            s = "Error tagging.";
//////            return new ResponseEntity<String>(s, HttpStatus.OK);
//////        }
//////    }
//
//    //delete roles from user
//    @RequestMapping("/DeleteuserRole")
//    public ResponseEntity<String> deleteuserRole() {
//        //sample userid and roleid
//        String userID = "sample2";
//        String roleID = "IT";
//        String s = "";
//        if (accountRoleService.deleteUserRole(userID, roleID)){
//            s = "Deleted role from the user successfully";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        } else {
//            s= "Error tagging. User might not have the role specified.";
//            return new ResponseEntity<String>(s, HttpStatus.OK);
//        }
//    }
//
//
//    //retrieved all existing users that have that particular role
//    @RequestMapping("/retrievedallUserwithRole")
//    public  ResponseEntity<List<SystemAccount>> retrievedallUserwithRole() {
//        //sample roleid
//        String roleID = "ID";
//        if(accountRoleService.findAllUserWithRole(roleID).isEmpty()){
//            return new ResponseEntity<List<SystemAccount>>(new ArrayList<SystemAccount>(), HttpStatus.OK);
//        } else {
//        return new ResponseEntity<List<SystemAccount>>(accountRoleService.findAllUserWithRole(roleID), HttpStatus.OK);
//    }
//    }
//
//
//
//
//
//
//    //View all deleted accounts for auditing
//    //in this case, getUserID is the webpage retrieved
//    @RequestMapping("/viewDeletedRoleList")
//    public List<AccountRole> viewAllDeletedAccounts() {
//        List<AccountRole> role = accountRoleService.retriveDeletedRole();
//        return role;
//
//    }
//
//
//
//
//    //this method validates the number of characters in the string provided by the user.
//    public boolean methodtoCheckCharacters(String word) {
//        int length = word.length();
//        if (length > 45) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//}
