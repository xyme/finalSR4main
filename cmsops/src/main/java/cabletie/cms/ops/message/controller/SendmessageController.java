package cabletie.cms.ops.message.controller;

import cabletie.cms.ops.accountworkspace.service.AccountRoleService;
import cabletie.cms.ops.accountworkspace.service.AcctManagementService;
import cabletie.cms.ops.accountworkspace.service.StaffManagementService;
import cabletie.cms.ops.corporateDBDao.ReceiveMessageDAO;
import cabletie.cms.ops.corporateDBDao.SendMessageDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.*;
import cabletie.cms.ops.message.service.SendmessageService;
import cabletie.cms.ops.message.service.receivemessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes("name")
public class SendmessageController {

    @Autowired
    private SendmessageService sendmsg;
    @Autowired
    private SendMessageDAO sendmsgDAO;

    @Autowired
    private SystemAccountDAO sysDAO;


    private SystemAccount selectedacct;


    //create Message
    @GetMapping("/staff-createMessage")
    public String create(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }
        //finding all staff that has an account
        List<SystemAccount> allAcct = sendmsg.getAllAccount(acct.getUserID());
        model.addAttribute("staffList", allAcct);
        
        String acctID = acct.getUserID();
        List<SystemAccount> findacct = sysDAO.findByuserID(acctID);

        model.addAttribute("name", findacct.get(0));
        return "staff-createMessage";
    }

    @PostMapping("/staff-createMessage")
    public String createMessage(@RequestParam(value = "description") String description,
                                @RequestParam(value = "selectedStaff", required = false) List<String> users,
                                @RequestParam(value = "msgType") String msgType, Model model) {

        ///Handle announcement cases
        if(msgType.contains("Announcement")){

            //sends the announcement to HR - Manager for approval
            List<SystemAccount> Manager = sysDAO.findByuserGroup("manager");
            List<SystemAccount> HRManager = new ArrayList<>();
            //find the HR role in the manager
            for(int i = 0 ; i < Manager.size(); i++){
                for(int j = 0 ; j < Manager.get(i).getRoles().size(); j++) {
                	if(Manager.get(i).getRoles().get(j).equals("HR")) {
                		HRManager.add(Manager.get(i));	
                	}
                }
            }

            
            List<String> receivers = new ArrayList<>();
            for(int i = 0 ; i < Manager.size(); i++){
                receivers.add(Manager.get(i).getUserID());
            }

            //send to HR managers for approval
            sendmsg.createMessage(description, selectedacct.getStaff().getName() + " - Human Resource Dept", msgType, selectedacct.getUserID(), receivers);
            //send Notification to HR managers
            sendmsg.createMessage("Please be reminded that you have just received an announcement pending for your approval.",
            		"System Notification", "Notification", "Sys01", receivers);

        }
        //Handles creation of messages and notifications
        else {

            List<String> userIDS = new ArrayList<String>();
            //retrieve user id
            if (users.isEmpty()) {
                return "redirect:/staff-createMessage";
            }

            for (int i = 0; i < users.size(); i++) {
                //extract user from selectedStaff
                String[] parts = users.get(i).split("/");
                userIDS.add(parts[1]);
            }

            if (sendmsg.createMessage(description, selectedacct.getStaff().getName(), msgType, selectedacct.getUserID(), userIDS)) {
                return "redirect:/index";
            }
        }

        return "redirect:/index";
    }


//@GetMapping("/staff-approveAnnouncement")
//public String approveAnnouncement(@ModelAttribute("name") SystemAccount acct, Model model){
//
//        boolean containHR = false;
//        for(int i = 0 ; i < acct.getRoles().size() ; i++){
//            if(acct.getRoles().get(i).getRoleID().equals("HR") && acct.getUserGroup().equals("Manager")){
//                containHR = true;
//            }
//        }
//
//        //check if its a HR manager
//        if(!containHR){
//            return "redirect/index";
//        } else {
//            approve
//        }
//}




    //view all Message
    @GetMapping("/staff-viewMessageSent")
    public String view(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<SendMessage> allMsg = sendmsg.getAllSentMsg(acct.getUserID());

        Comparator<SendMessage> compare = new Comparator<SendMessage>() {
            public int compare(SendMessage o1, SendMessage o2) {
                if (o1.getTimeSent() == null || o2.getTimeSent() == null)
                    return 0;
                return o1.getTimeSent().compareTo(o2.getTimeSent());
            }
        };

        Collections.sort(allMsg, Collections.reverseOrder(compare));

        model.addAttribute("iList", allMsg);
        model.addAttribute("MessageType", "Message");
        return "staff-viewMessageSent";
    }



    
    //view message details
    @GetMapping("/staff-viewMsgSentDetail")
    public String sentDetail(@ModelAttribute("name") SystemAccount acct, Model model,
                                @RequestParam(value = "selected") String msgID){
        //inflate message view
        List<SendMessage> msg = sendmsgDAO.findBymsgID(msgID);
        String receivers = "";
        //Consolidate all the receivers' ID for view
        for (int i = 0 ; i < msg.get(0).getReceiveMessages().size(); i++){
            if(i == msg.get(0).getReceiveMessages().size() - 1) {
                receivers = receivers + msg.get(0).getReceiveMessages().get(i).getUserID();
            } else {
                receivers = receivers + msg.get(0).getReceiveMessages().get(i).getUserID() + ", ";
            }
        }

        model.addAttribute("receivables", receivers);
        model.addAttribute("iList", msg.get(0));

        return "/staff-viewMsgSentDetail";
    }


    @GetMapping("/staff-deleteSentMsg")
    public String deleteSentMsg( @RequestParam(value = "selected") String msgID){
        //look for the message type
//        List<SendMessage> msgtype = sendmsgDAO.findBymsgID(msgID);
        //String msgType = msgtype.get(0).getMsgtype();
        sendmsg.deleteMessage(msgID);

////        redirect base on message type
//        if(msgType.equals("Announcement"))
//            return "redirect:/staff-viewAnnouncement";
//        else if (msgType.equals("Notification"))
//            return "redirect:/staff-viewNotification";

        return "redirect:/staff-viewMessageSent";
    }

    @GetMapping("/announcement-delete")
    public String deleteAnnouncement(@RequestParam(value ="selected") String msgID){
        //look for the message type
        sendmsg.deleteMessage(msgID);

        return "redirect:/staff-viewSentAnnouncments";
    }

}
