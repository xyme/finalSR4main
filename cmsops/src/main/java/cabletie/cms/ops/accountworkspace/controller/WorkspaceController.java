package cabletie.cms.ops.accountworkspace.controller;

import cabletie.cms.ops.accountworkspace.service.AcctManagementService;
import cabletie.cms.ops.accountworkspace.service.WorkspaceManagementService;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.message.service.SendmessageService;
import cabletie.cms.ops.message.service.receivemessageService;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes("name")
public class WorkspaceController {

    @Autowired
    WorkspaceManagementService work;

    @Autowired
    AcctManagementService acc;
    
    @Autowired
    private receivemessageService received;

    @Autowired
    private SendmessageService send;
    
    //assuming
    //Display login website
    @GetMapping(value="/")
    public String login(Model modelMap) {
        modelMap.addAttribute("name", new SystemAccount());
       return "login";
    }


//    //Retrive login website
    @PostMapping(value = "/")
    public String handlelogin(@RequestParam(value = "userID") String useryID, @RequestParam(value = "password")
            String password, Model modelMap){


        if(work.loginCheck(useryID)){
        if(!work.validateLogin(useryID, password)) {
            return "login";
        }} else {
            return "login";
        }


 //<<------------store user object as the session ---->>>>>>>>
        modelMap.addAttribute("name", work.retrieveAcct(useryID).get(0));

        //Handle Admin view and staff view
        if(acc.getSingleAccount(useryID).get(0).getUserGroup().equals("Admin")){
            return "redirect:/viewAllAccounts";
        }

        //for Staff view
        return "redirect:/index";
    }

    @GetMapping(value = "/index")
    public String welcome(@ModelAttribute("name") SystemAccount acct, Model model){
        //if session is cleared
        if(acct.getUserID() == null) {
            return "redirect:/";
        }
        
        //inflate all 3 types of messages
    
        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());
        
        
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Message", allMsg);
        model.addAttribute("M", filterMsg);
  
        
        //Retrieve all notification
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg2 = filterMessage("Notification", allMsg);
        //sort flagged msg and all msg
        model.addAttribute("N", filterMsg2);
        
        
        List<ReceiveMessage> filterMsg3 = filterMessage("Internal Announcement", allMsg);
        model.addAttribute("A", filterMsg3);
        
        return "index";
    }

    public List<ReceiveMessage> filterMessage(String type, List<ReceiveMessage> allMsg){
        List<ReceiveMessage> filterMsg = new ArrayList<>();

        //sort 
        for(int i = 0 ; i < allMsg.size()  ; i++){
            if(allMsg.get(i).getSendmsg().getMsgtype().contains(type)){
                filterMsg.add(allMsg.get(i));
            }
        }
        
        Comparator<ReceiveMessage> compare = new Comparator<ReceiveMessage>() {
            public int compare(ReceiveMessage o1, ReceiveMessage o2) {
                if (o1.getSendmsg().getTimeSent() == null || o2.getSendmsg().getTimeSent() == null)
                    return 0;
                return o1.getSendmsg().getTimeSent().compareTo(o2.getSendmsg().getTimeSent());
            }
        };

        Collections.sort(filterMsg, Collections.reverseOrder(compare));

        //return only 3 msg
        List<ReceiveMessage> returnMsg = new ArrayList<ReceiveMessage>();
      
        if ( filterMsg.size() < 3) {
        	return filterMsg;
        } else {
        	  for ( int i = 0 ; i < 3; i++) {
              	returnMsg.add(filterMsg.get(i));
              }
              return returnMsg;
        }
    }
    
//    @PostMapping(value = "/index")
//    public String welcome(@RequestParam(value = "hi") String hi, @ModelAttribute("name") SystemAccount acct){
//
//        //if session is cleared
//        if(acct.getUserID() == null) {
//            return "redirect:/";
//        }
//        //populate the element
//
//
//        return "redirect:/test";
//    }

    //Retrive Logout
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String Logout(Model model, SessionStatus status){
        //<<-------------clears the session ------------>
        model.addAttribute("name", new SystemAccount());
        return "redirect:/";
    }
}
