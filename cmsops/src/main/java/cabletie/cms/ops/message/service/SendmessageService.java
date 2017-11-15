package cabletie.cms.ops.message.service;

import cabletie.cms.ops.corporateDBDao.ReceiveMessageDAO;
import cabletie.cms.ops.corporateDBDao.SendMessageDAO;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.SendMessage;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SendmessageService {

    //sender point of view
    @Autowired
    private SendMessageDAO msgDAO;
    @Autowired
    private ReceiveMessageDAO receiveDAO;
//    @Autowired
//    private StaffDAO staffDAO;
    @Autowired
    private SystemAccountDAO acctDAO;

    public boolean createMessage(String description, String sender, String msgtype, String creatorID, List<String> receiver) {
        //establish timesent

        //In this example, the date created is generated using java api
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        //create send message baseline
        SendMessage msg = new SendMessage(description, sender, currentTimestamp, msgtype, creatorID);
        msgDAO.save(msg);

        //create multiple receivers
        ReceiveMessage msgReceiver;
        for(int i = 0 ; i < receiver.size();i++){
            msgReceiver = new ReceiveMessage(receiver.get(i));
            //save the receiver then save the sent msg
            msgReceiver.setSendmsg(msg);
            receiveDAO.save(msgReceiver);
//            msg.getReceiveMessages().add(msgReceiver);
        }

        return true;
    }

    //for message service
    //get all staffs tied with an account except yourself
//    public List<Staff> getAllStaffWithAcc(String id) {
//        //output: if cannot find any staffs created
//        if (staffDAO.findAll().isEmpty()) {
//            return new ArrayList<Staff>();
//        }
//        //check if all of the staff return have their status deleted
//        else {
//            List<Staff> retrievedList = staffDAO.findAll();
//            List<Staff> listofStaff = new ArrayList<Staff>();
//            if (!retrievedList.isEmpty()) {
//                for (Staff e : retrievedList) {
//                    //add into the list if staff does not have the status "deleted" and the staff is not tied with any exising account
//                    if (!e.getStatus().equals("deleted")) {
//                        if (e.getAccount() != null) {
//                            if (!e.getAccount().getStatus().equals("deleted") && !e.getStaffId().equals(id)){
//                                listofStaff.add(e);
//                            }
//                        }
//                    } else {
//                    }
//                }
//            }
//            return listofStaff;
//        }
//    }


    //get all existing accounts
    public List<SystemAccount> getAllAccount(String userID){

            List<SystemAccount> retrievedList = acctDAO.findAll();
            List<SystemAccount> addedList = new ArrayList<SystemAccount>();
            //put in all none deleted accounts
            if(!retrievedList.isEmpty()) {
                for (SystemAccount e : retrievedList) {
//                if(!e.getUserGroup().equals("Admin")) {
                    if (!e.getStatus().equalsIgnoreCase("deleted") && !e.getUserID().equalsIgnoreCase("admin") && !e.getUserID().equals(userID)) {
                    addedList.add(e);
                }
                    //   }
//            else {
//                    }
                }
            }

            return addedList;
    }



    //get all messages only (exclude announcements)
    public List<SendMessage> getAllSentMsg(String userID){

        List<SendMessage> retrievedList = msgDAO.findByuserID(userID);

        List<SendMessage> addedList = new ArrayList<SendMessage>();
        //put in all none deleted messages received
        if(!retrievedList.isEmpty()) {
            for (SendMessage e : retrievedList) {


                //receive all message that are not deleted
                if (!e.getStatus().equalsIgnoreCase("deleted") && e.getMsgtype().contains("Message")) {
                    addedList.add(e);
                }

            }
        }

        return addedList;
    }
    
    
  //get all Announcements only 
    public List<SendMessage> getAllSentAnnouncement(String userID){

        List<SendMessage> retrievedList = msgDAO.findByuserID(userID);

        List<SendMessage> addedList = new ArrayList<SendMessage>();
        //put in all none deleted messages received
        if(!retrievedList.isEmpty()) {
            for (SendMessage e : retrievedList) {


                //receive all message that are not deleted
                if (e.getMsgtype().contains("Announcement")) {
                    addedList.add(e);
                }

            }
        }

        return addedList;
    }



    public void deleteMessage(String msgID){

        List<SendMessage> delete = msgDAO.findBymsgID(msgID);
        delete.get(0).setStatus("deleted");
        msgDAO.save(delete);

    }


}
