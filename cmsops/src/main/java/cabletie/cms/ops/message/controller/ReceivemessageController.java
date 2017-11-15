package cabletie.cms.ops.message.controller;

import cabletie.cms.ops.corporateDBDao.FeedbackDAO;
import cabletie.cms.ops.corporateDBDao.ReceiveMessageDAO;
import cabletie.cms.ops.corporateDBDao.SendMessageDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Feedback;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.SendMessage;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.message.service.SendmessageService;
import cabletie.cms.ops.message.service.receivemessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ReceivemessageController {

    @Autowired
    private receivemessageService received;

    @Autowired
    private SendmessageService send;
    @Autowired
    private ReceiveMessageDAO msgDAO;

    @Autowired
    private SystemAccountDAO sysDAO;
    
    @Autowired
    private FeedbackDAO feedback;
    @Autowired
            private SendMessageDAO sendDAO;

    SystemAccount selectedacct;

    //view all message (MsgType)
    @GetMapping("/staff-viewMessages")
    public String view(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Message", allMsg);

//        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
//        //load all flagged messages
//        for(int i = 0 ; i< filterMsg.size() ; i++){
//            if(filterMsg.get(i).getFlaggedstatus().equalsIgnoreCase("yes")){
//                flaggedMsg.add(filterMsg.get(i));
//            }
//        }
        //sort flagged msg and all msg
        model.addAttribute("iList", filterMsg);
//        model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Message");
        return "staff-viewMessages";
    }
    
    
  
    //view all message (MsgType)
    @GetMapping("/staff-viewFlaggedMessages")
    public String viewFlagged(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Message", allMsg);

        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
        //load all flagged messages
        for(int i = 0 ; i< filterMsg.size() ; i++){
            if(filterMsg.get(i).getFlaggedstatus().equalsIgnoreCase("yes")){
                flaggedMsg.add(filterMsg.get(i));
            }
        }
        //sort flagged msg and all msg
//        model.addAttribute("iList", filterMsg);
        model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Message");
        return "staff-viewFlaggedMessages";
    }
    
  //view all message (MsgType)
    @GetMapping("/staff-viewFlaggedAnnouncement")
    public String viewFlaggedAnnouncements(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Internal Announcement", allMsg);

        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
        //load all flagged messages
        for(int i = 0 ; i< filterMsg.size() ; i++){
            if(filterMsg.get(i).getFlaggedstatus().equalsIgnoreCase("yes")){
                flaggedMsg.add(filterMsg.get(i));
            }
        }
        //sort flagged msg and all msg
//        model.addAttribute("iList", filterMsg);
        model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Announcement");
        return "staff-viewFlaggedMessages";
    }
    
  //view all message (MsgType)
    @GetMapping("/staff-viewFlaggedNotification")
    public String viewFlaggedNotification(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());
        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Notification", allMsg);

        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
        //load all flagged messages
        for(int i = 0 ; i< filterMsg.size() ; i++){
            if(filterMsg.get(i).getFlaggedstatus().equalsIgnoreCase("yes")){
                flaggedMsg.add(filterMsg.get(i));
            }
        }
        //sort flagged msg and all msg
//        model.addAttribute("iList", filterMsg);
        model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Notification");
        return "staff-viewFlaggedMessages";
    }
    

    //view all Received/Sent Announcement (MsgType)
    @GetMapping("/staff-viewAnnouncement")
    public String viewAnnouncement(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //if user is a HR (Manager)
        List<SystemAccount> found = sysDAO.findByuserID(acct.getUserID());
        
        if ( found.get(0).getUserGroup().equalsIgnoreCase("Manager")){
        for(int i = 0 ; i < found.get(0).getRoles().size(); i ++) {
        	if(found.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
        		return "redirect:/staff-viewPendingAnnouncements";
        	}
        }
    }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());

        boolean isHRStaff = false;
        
        if ( found.get(0).getUserGroup().equalsIgnoreCase("staff")){
            for(int i = 0 ; i < found.get(0).getRoles().size(); i ++) {
            	if(found.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")) {
            		isHRStaff = true;
            	}
            }
        }
        
        List<ReceiveMessage> filterMsg = new ArrayList<ReceiveMessage>();
        //Filter message to grab if it is a HR staff
        if(isHRStaff) {
        	 filterMsg = filterMessage("Announcement", allMsg);
        } else {
        
        //Filter message to grab by type and sort them by descending order if it is not a HR staff
             filterMsg = filterMessage("Internal Announcement", allMsg);
        }
        
        
//        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
//        //load all flagged messages
//        for(int i = 0 ; i< allMsg.size() ; i++){
//            if(allMsg.get(i).getFlaggedstatus().equals("yes")){
//                flaggedMsg.add(allMsg.get(i));
//            }
//        }

        //sort flagged msg and all msg
        model.addAttribute("iList", filterMsg);
        //model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Internal Announcement");
        return "staff-viewMessages";
    }


    //view all Received/Sent Notification (MsgType)
    @GetMapping("/staff-viewNotification")
    public String viewNotification(@ModelAttribute("name") SystemAccount acct, Model model) {

        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        //Retrieve all existing messages of all type
        List<ReceiveMessage> allMsg = received.receiveAllMessages(acct.getUserID());

        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Notification", allMsg);

//        List<ReceiveMessage> flaggedMsg = new ArrayList<ReceiveMessage>();
//        //load all flagged messages
//        for(int i = 0 ; i< allMsg.size() ; i++){
//            if(allMsg.get(i).getFlaggedstatus().equals("yes")){
//                flaggedMsg.add(allMsg.get(i));
//            }
//        }
        //sort flagged msg and all msg
        model.addAttribute("iList", filterMsg);
       // model.addAttribute("iFlagged", flaggedMsg);
        model.addAttribute("MessageType", "Notification");
        return "staff-viewMessages";
    }


    public List<ReceiveMessage> filterMessage(String type, List<ReceiveMessage> allMsg){
        List<ReceiveMessage> filterMsg = new ArrayList<>();

        //sort by descending order
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

        return filterMsg;
    }

    //flag message
    @GetMapping("/staff-flagMessage")
    public String flagMessage(@RequestParam(value = "selectedFlag") String msgID) {

        received.flagMsg(msgID);

        //look for the message type
        List<ReceiveMessage> msgtype = msgDAO.findByreceivemsgID(msgID);
        String msgType = msgtype.get(0).getSendmsg().getMsgtype();

        return returnstate(msgType, "Flag");
    }

    //flag message
    @GetMapping("/staff-unflagMessage")
    public String unflagMessage(@RequestParam(value = "selectedFlag") String msgID) {

        received.unflagMsg(msgID);

        //look for the message type
        List<ReceiveMessage> msgtype = msgDAO.findByreceivemsgID(msgID);
        String msgType = msgtype.get(0).getSendmsg().getMsgtype();

        return returnstate(msgType, "Unflag");
    }

    //flag message
    @GetMapping("/staff-flagDetailedMessage")
    public String flagDetailedMessage(@RequestParam(value = "selectedFlag") String msgID) {

        received.flagMsg(msgID);

        //look for the message type
        List<ReceiveMessage> msgtype = msgDAO.findByreceivemsgID(msgID);
        String msgType = msgtype.get(0).getSendmsg().getMsgtype();

        return returnstate(msgType, "Unflag");
    }

    //unflag message
    @GetMapping("/staff-unflagDetailedMessage")
    public String unflagDetailedMessage(@RequestParam(value = "selectedFlag") String msgID) {

        received.unflagMsg(msgID);

        //look for the message type
        List<ReceiveMessage> msgtype = msgDAO.findByreceivemsgID(msgID);
        String msgType = msgtype.get(0).getSendmsg().getMsgtype();

        return returnstate(msgType, "Flag");
    }


    //view message details
    @GetMapping("/staff-viewMsgDetail")
    public String messageDetail(@ModelAttribute("name") SystemAccount acct, Model model,
                                @RequestParam(value = "selected") String receivemsgID){

        //mark it as read
        received.readMsg(receivemsgID);
        //inflate message view
        List<ReceiveMessage> msg = msgDAO.findByreceivemsgID(receivemsgID);
        
        model.addAttribute("iList", msg.get(0));
        
        return "/staff-viewMsgDetail";
    }

    @GetMapping("/staff-deleteMsg")
    public String deleteMsg( @RequestParam(value = "selected") String receivemsgID){
        //look for the message type
        List<ReceiveMessage> msgtype = msgDAO.findByreceivemsgID(receivemsgID);
        String msgType = msgtype.get(0).getSendmsg().getMsgtype();
        received.deleteMsg(receivemsgID);
        
        //get flag status
        String flagstatus = msgtype.get(0).getFlaggedstatus();
        String flag = "";
        if(flagstatus.equals("yes")) {
        	flag = "Unflag";
        } else {
        	flag = "Flag";
        }
        return returnstate(msgType, flag);
    }

    public String returnstate(String msgType, String status){
        //redirect base on message type
    	if(status == "Flag") {
        if(msgType.equals("Internal Announcement"))
            return "redirect:/staff-viewAnnouncement";
        else if (msgType.equals("Notification"))
            return "redirect:/staff-viewNotification";
        else
        	return "redirect:/staff-viewMessages";
    	} else {
    		 if(msgType.equals("Internal Announcement"))
    	            return "redirect:/staff-viewFlaggedAnnouncement";
    	        else if (msgType.equals("Notification"))
    	            return "redirect:/staff-viewFlaggedNotification";
    	        else 
    	        	return "redirect:/staff-viewFlaggedMessages";
    	}
        
    }























    // HR ANNOUMCEMENT MANAGEMENT
    //
    //
    //for HR manager's and staff's management
    @GetMapping("/staff-viewPendingAnnouncements")
    public String viewPendingAnnouncement(@ModelAttribute("name") SystemAccount acct, Model model){

        //load all internal and external announcements for view
        List<ReceiveMessage> allMsg = received.receiveAllAnnouncements(acct.getUserID());

        //Filter message to grab by type and sort them by descending order
        List<ReceiveMessage> filterMsg = filterMessage("Announcement", allMsg);



        model.addAttribute("iList", filterMsg);
        return "staff-viewPendingAnnouncements";
    }



    //
    @GetMapping("/announcement-approve")
    public String approveAnnouncement(@RequestParam(value = "selected") String msgID){

        List<SendMessage> sent = sendDAO.findBymsgID(msgID);
        sent.get(0).setStatus("Approved");
        sendDAO.save(sent.get(0));

        if(sent.get(0).getMsgtype().contains("Internal")) {
        //send the announcement to rest of the HR internally
        received.sendAnnouncementHR(sent.get(0));

        //send the announcement to all less the HR member
        received.sendAnnouncementWide(sent.get(0));
        } else {
        	received.sendAnnouncementExternal(sent.get(0));
        }

        return "redirect:/staff-viewAnnouncement";
    }

    @GetMapping("/announcement-reject")
    public String rejectAnnouncement(@RequestParam(value = "selected") String msgID){

        List<SendMessage> sent = sendDAO.findBymsgID(msgID);
        sent.get(0).setStatus("Reject");
        sendDAO.save(sent);
        return "redirect:/staff-viewAnnouncement";
    }

    //opens the view page
    @GetMapping("/announcement-management")
    public String manageAnnouncement(@RequestParam(value = "selected") String msgID,
    Model model){

    	List<SendMessage> msg = sendDAO.findBymsgID(msgID);
        model.addAttribute("iList", msg.get(0));
        return "staff-viewAnnouncementDetail";
    }

//    //opens the view page
//    @GetMapping("/announcement-management2")
//    public String manageAnnouncement2(@RequestParam(value = "selected") String msgID,
//                                     Model model){
//
//    	List<SendMessage> msg = sendDAO.findBymsgID(msgID);
//        model.addAttribute("iList", msg.get(0));
//        return "staff-viewAnnouncementDetail";
//    }

    //opens the view page
    @GetMapping("/staff-editAnnouncement")
    public String editAnnouncement(@RequestParam(value = "selected") String msgID,
                                      Model model){


    	List<SendMessage> msg = sendDAO.findBymsgID(msgID);
        model.addAttribute("iList", msg.get(0));
        return "staff-editAnnouncement";
    }

    //Post editing of announcement

    @PostMapping("/staff-editAnnouncement")
    public String saveEditAnnouncement(@RequestParam(value = "announcementID") String msgID,
                                       @RequestParam(value = "msgType") String msgType,
                                       @RequestParam(value = "status", required = false) String status,
                                                   @RequestParam(value = "description") String description,
                                       @ModelAttribute("name") SystemAccount acct
                                  ){


        List<SendMessage> msg = sendDAO.findBymsgID(msgID);
        
        //test conditions before updating the entry
		if(msgType.trim().isEmpty()){
		    msgType = msg.get(0).getMsgtype();
		} if (status != null) {
			if (status.trim().isEmpty()){
		    status = msg.get(0).getStatus();
		}
		}if(status == null) {
			status = msg.get(0).getStatus();
		}

        msg.get(0).setMsgtype(msgType);
        msg.get(0).setDescription(description);
        //save the message
        String previousStatus = msg.get(0).getStatus();
        msg.get(0).setStatus(status);
        
        if(acct.getUserGroup().equalsIgnoreCase("staff") && previousStatus.equalsIgnoreCase("Reject")){
            msg.get(0).setStatus("active");
           //In this example, the date created is generated using java api
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            msg.get(0).setTimeSent(currentTimestamp);


            //change receiver's message status to active
//            for(int i = 0 ; i < msg.get(0).getReceiveMessages().size(); i ++){
//                msg.get(0).getReceiveMessages().get(i).setStatus("active");
//                msgDAO.save(msg.get(0).getReceiveMessages().get(i));
//            }
        }
        else if(acct.getUserGroup().equalsIgnoreCase("manager") && previousStatus.equalsIgnoreCase("Approved")){

            //In this example, the date created is generated using java api
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            
            msg.get(0).setSender(acct.getUserID());
            msg.get(0).setTimeSent(currentTimestamp);
            msg.get(0).setStatus("Updated");


            //change receiver's message status to updated
            for(int i = 0 ; i < msg.get(0).getReceiveMessages().size(); i ++){
                msg.get(0).getReceiveMessages().get(i).setStatus("Updated");
                msgDAO.save(msg.get(0).getReceiveMessages().get(i));
            }
        }
        
        if(msg.get(0).getStatus().equalsIgnoreCase("Approved")) {
        	
        	sendDAO.save(msg.get(0));
        	 //send the announcement to rest of the HR internally
            received.sendAnnouncementHR(msg.get(0));

            //send the announcement to all less the HR member
            received.sendAnnouncementWide(msg.get(0));
        	
        }
        sendDAO.save(msg.get(0));

        return "redirect:/staff-announcementDirect";
    }
    
    @GetMapping("/staff-viewSentAnnouncements")
    public String viewSentAnnouncement(@ModelAttribute("name") SystemAccount acct, Model model) {
        //retrieve all announcements that were sent (HR - Staff)
    	
        selectedacct = acct;
        if (acct.getUserID() == null) {
            return "redirect:/";
        }

        
        List<SystemAccount> sys = sysDAO.findByuserID(acct.getUserID());
        
        boolean isHR = false;
        
        for ( int i = 0 ; i < sys.get(0).getRoles().size() ; i++) {
        	if(sys.get(0).getRoles().get(i).getRoleID().equalsIgnoreCase("HR")){
        		isHR = true;
        	}
        }
        
        if(!isHR) {
        return "redirect:/staff-viewAnnouncement";
        }
        
        List<SendMessage> allMsg = send.getAllSentAnnouncement(acct.getUserID());

        Comparator<SendMessage> compare = new Comparator<SendMessage>() {
            public int compare(SendMessage o1, SendMessage o2) {
                if (o1.getTimeSent() == null || o2.getTimeSent() == null)
                    return 0;
                return o1.getTimeSent().compareTo(o2.getTimeSent());
            }
        };

        Collections.sort(allMsg, Collections.reverseOrder(compare));

        model.addAttribute("i", allMsg);
        model.addAttribute("MessageType", "Message");
        return "staff-viewSentAnnouncements";
    }
    
    
    @GetMapping("/staff-announcementDirect")
    public String decideLink(@ModelAttribute("name") SystemAccount acct) {
    	if(acct.getUserGroup().equalsIgnoreCase("staff")) {
    		return "redirect:/staff-viewSentAnnouncements";
    	} 
    	return "redirect:/staff-viewPendingAnnouncements";
    }

    @GetMapping("/staff-feedback")
    public String getFeedback(@ModelAttribute("name") SystemAccount acct, Model model) {
    	List<Feedback> fb = feedback.findAll();
    	model.addAttribute("fb", fb);
    	return "staff-feedback";
    	}
    
    @GetMapping("/staff-customerFeedbackDetails")
    public String getFeedbackDetails(@ModelAttribute("name") SystemAccount acct, 
    		@RequestParam(value = "selected") String selected, Model model) {
    	
    	List<Feedback> fb = feedback.findBynum(selected);
    	model.addAttribute("feedback", fb.get(0));
    	return "staff-customerFeedbackDetails";
    	}
}
