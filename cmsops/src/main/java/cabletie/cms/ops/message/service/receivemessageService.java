package cabletie.cms.ops.message.service;

import cabletie.cms.ops.corporateDBDao.ReceiveMessageDAO;
import cabletie.cms.ops.corporateDBDao.SendMessageDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.SendMessage;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

@Service
public class receivemessageService {

    @Autowired
    private ReceiveMessageDAO receiveDAO;
    @Autowired
    private SendMessageDAO sendDAO;
    @Autowired
    private SystemAccountDAO sysDAO;


    //get all new messages (notification/announcement/messages)
    public List<ReceiveMessage> receiveAllMessages(String userID){

        List<ReceiveMessage> retrievedList = receiveDAO.findByuserID(userID);

        List<ReceiveMessage> addedList = new ArrayList<ReceiveMessage>();
        //put in all none deleted messages received
        if(!retrievedList.isEmpty()) {
            for (ReceiveMessage e : retrievedList) {


                //receive all message that are not deleted
                if ( e.getStatus().equalsIgnoreCase("active") 
                	|| e.getStatus().equalsIgnoreCase("read") || e.getStatus().equalsIgnoreCase("Updated")) {
                    addedList.add(e);
                }


            }
        }

        return addedList;
    }

    //get all new messages (notification/announcement/messages)
    public List<ReceiveMessage> receiveAllAnnouncements(String userID){

        List<ReceiveMessage> retrievedList = receiveDAO.findByuserID(userID);

        List<ReceiveMessage> addedList = new ArrayList<ReceiveMessage>();
        //put in all none deleted messages received
        if(!retrievedList.isEmpty()) {
            for (ReceiveMessage e : retrievedList) {


                //receive all message that are not deleted
                if (!e.getStatus().equalsIgnoreCase("deleted")) {
                    addedList.add(e);
                }

            }
        }

        return addedList;
    }


    //release announcement to external party
    public boolean sendAnnouncementExternal(SendMessage m) {
    	ReceiveMessage msgReceiver = new ReceiveMessage("allPassenger");
    	msgReceiver.setSendmsg(m);

    	receiveDAO.save(msgReceiver);
    	return true;
    }


    //release the announcements company to other HR staffs
    public boolean sendAnnouncementHR(SendMessage m){
        List<SystemAccount> retrievedList = sysDAO.findAll();
        List<SystemAccount> addedList = new ArrayList<SystemAccount>();
        //put in all none deleted accounts
        if(!retrievedList.isEmpty()) {
            for (SystemAccount e : retrievedList) {
            	
            	boolean exist = false;
            	for ( int i = 0 ; i < e.getRoles().size(); i ++) {
            		if(e.getRoles().get(i).getRoleID().equalsIgnoreCase("HR")){
            			exist = true;
            		}
            	}
            	
            	if(exist && !e.getUserGroup().equalsIgnoreCase("manager")) {
            		addedList.add(e);
            	}
            	}
                //   }
//            else {
//                    }
            }

        //Processs sending
        //create multiple receivers
        ReceiveMessage msgReceiver;
        for(int i = 0 ; i < addedList.size();i++){
            msgReceiver = new ReceiveMessage(addedList.get(i).getUserID());
            //save the receiver then save the sent msg
            msgReceiver.setSendmsg(m);
            receiveDAO.save(msgReceiver);
//            msg.getReceiveMessages().add(msgReceiver);
        }

        return true;
    }


    //get all accounts except HR accounts
    public boolean sendAnnouncementWide(SendMessage m){

        List<SystemAccount> retrievedList = sysDAO.findAll();
        List<SystemAccount> addedList = new ArrayList<SystemAccount>();
        //put in all none deleted accounts
        if(!retrievedList.isEmpty()) {
            for (SystemAccount e : retrievedList) {
            
        		boolean exist = false;
            	for ( int i = 0 ; i < e.getRoles().size(); i ++) {            
            		if(e.getRoles().get(i).getRoleID().equalsIgnoreCase("HR")){
            			exist = true;
            		}
            	}
            	
            	if(!exist) {
            		addedList.add(e);
            	}
                //   }
//            else {
//                    }
            }
        }

        //Processs sending
        //create multiple receivers
        ReceiveMessage msgReceiver;
        for(int i = 0 ; i < addedList.size();i++){
            msgReceiver = new ReceiveMessage(addedList.get(i).getUserID());
            //save the receiver then save the sent msg
            msgReceiver.setSendmsg(m);
            receiveDAO.save(msgReceiver);
//            msg.getReceiveMessages().add(msgReceiver);
        }

        return true;
    }

    //delete message
    public boolean deleteMsg(String msgID){

        List<ReceiveMessage> retrievedMsg = receiveDAO.findByreceivemsgID(msgID);
        retrievedMsg.get(0).setStatus("deleted");
        receiveDAO.save(retrievedMsg);
        return true;
    }


    //flag message
    public boolean flagMsg(String msgID){
        List<ReceiveMessage> retrievedMsg = receiveDAO.findByreceivemsgID(msgID);
        retrievedMsg.get(0).setFlaggedstatus("yes");
        receiveDAO.save(retrievedMsg);
        return true;
    }

    //unflag message
    public boolean unflagMsg(String msgID){
        List<ReceiveMessage> retrievedMsg = receiveDAO.findByreceivemsgID(msgID);
        retrievedMsg.get(0).setFlaggedstatus("no");
        receiveDAO.save(retrievedMsg);
        return true;
    }

    //read message
    public void readMsg(String msgID){
        List<ReceiveMessage> retrievedMsg = receiveDAO.findByreceivemsgID(msgID);
        retrievedMsg.get(0).setStatus("read");
        receiveDAO.save(retrievedMsg);
    }


    //get all read messages (notification/announcement/messages)
//    public List<ReceiveMessage> receiveReadMessages(String userID){
//
//
//        List<ReceiveMessage> retrievedList = receiveDAO.findByuserID(userID);
//
//        List<ReceiveMessage> addedList = new ArrayList<ReceiveMessage>();
//        //put in all none deleted accounts
//        if(!retrievedList.isEmpty()) {
//            for (ReceiveMessage e : retrievedList) {
////                if(!e.getUserGroup().equals("Admin")) {
//                if (e.getStatus().equals("read")) {
//                    addedList.add(e);
//                }
//                //   }
////            else {
////                    }
//            }
//        }
//        return addedList;
//    }


    //get all deleted messages
    //public List<ReceiveMessage> deletedMessages(String userID){}

}
