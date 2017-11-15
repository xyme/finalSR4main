package cabletie.cms.ops.accountworkspace.service;

import cabletie.cms.ops.corporateDBDao.AccountRoleDAO;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

//Account created must be tagged to a staff
//There must not be any floating accounts
@Service
public class AcctManagementService {
    //find by id : returns an object
    //remember to handle null back in controller
    @Autowired
    private SystemAccountDAO accDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private AccountRoleDAO roleDAO;

    //create new account
    //Input: id of the staff to be tied with and the respective details of the account to be created
    public boolean createAccount(String staffID, String userID, String password, String userGroup, List<AccountRole> role) {

        //In this example, the date created is generated using java api
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        //create a new system account
        String status = "active";
        SystemAccount systemAccount = new SystemAccount(userID, password, status, userGroup, currentTimestamp);


        //tie systemaccount user to role
        //search for the respective staff with the staff id
        List<Staff> staff = staffDAO.findByStaffId(staffID);
        if(staff.isEmpty()){
            return false;
        }

        //search for existing users (primary key)
        List<SystemAccount> systemAcct = accDAO.findByuserID(userID);
        if(!systemAcct.isEmpty()){
            return false;
        }

        //set the association
        //set staff to account
        accDAO.save(systemAccount);
        systemAccount.setStaff(staff.get(0));

        if(role == null) {
        } else {
            //load role to systemaccount user
            systemAccount.setRoles(role);


            //adding the roles
            for (int i = 0; i < role.size(); i++) {
                role.get(i).getSys().add(systemAccount);
            }
            roleDAO.save(role);
            //
        }


            //set account to staff
        staff.get(0).setAccount(systemAccount);
        staffDAO.save(staff.get(0));
        accDAO.save(systemAccount);

        return true;
    }

    //edit the account details
    //input: staff unique ID and respective fields to be updated/edited (example: name)
    public boolean editAccount(SystemAccount acct, List<AccountRole> role) {

        //output: if the account is deleted return false;
        if (acct.getStatus() == "deleted") {
            return false;
        }
        //Do the saving here
        else if (role == null) {
            acct.setRoles(null);
        } else {

            //delete the roles from the acct
            acct.setRoles(null);
            //set the new set of roles to the acct
            acct.setRoles(role);

            //adding the roles
            for (int i = 0; i < role.size(); i++) {
                role.get(i).getSys().add(acct);
            }
            roleDAO.save(role);
            //
        }
        accDAO.save(acct);
        return true;
            }

    //Delete an account based on the id provided
    //input: the userid to be deleted
    public boolean deleteAccount(String userID) {
        //output: if the account is already deleted;
        List<SystemAccount> systemAccounts = getSingleAccount(userID);

//        if (systemAccounts.isEmpty()){
//            return false;
//        }

        if (systemAccounts.get(0).getStatus() == "deleted") {
            return false;
        } else {
            //delete associate of staff - acct
            Staff staff = systemAccounts.get(0).getStaff();
            staff.setAccount(null);
//            staffDAO.save(staff);
//            
//            staffsvc.createStaff(location, idNo, name, gender, dateOfBirth, contactNo, email, address, nationality, race, maritalStatus, department, position, contractStart, contractEnd, salary)
//            staff.setName(staff.getName() + " - removed already");
//            staffDAO.save(staff);
            //delete association of acct - staff
//            systemAccounts.get(0).setStaff(staff);
            //delete the account
            systemAccounts.get(0).setStatus("deleted");
            accDAO.save(systemAccounts.get(0));

            return true;
            }

    }


    //change the staff's account password
    //input: staff ID, old password (for validation) and new password
    public boolean resetPassword(SystemAccount acct, String oldPass, String newPass, String newPass2) {

        //output : If old password is invalid or account is deleted

        if (!acct.getPassword().equals(oldPass)) {
            return false;
        } else if (!newPass.equals(newPass2)) {
            return false;
        }
        //credentials are correct

            acct.setPassword(newPass);
            accDAO.save(acct);
            return true;
        }


    //retrieving all system acccounts
    //input: none
    public List<SystemAccount> getAllSystemAccount() {

        //output a list of system account
        List<SystemAccount> retrievedList = accDAO.findAll();
        List<SystemAccount> addedList = new ArrayList<SystemAccount>();
        //put in all none deleted accounts
        if(!retrievedList.isEmpty()) {
            for (SystemAccount e : retrievedList) {
//                if(!e.getUserGroup().equals("Admin")) {
//                    if (!e.getStatus().equals("deleted")) {
                        addedList.add(e);
//                }
                 //   }
//            else {
//                    }
                }
            }

        //return populated list
        //returns null if it is empty
        return addedList;
    }

    //retrieving all deleted system accounts
    //input: none
    public List<SystemAccount> getAllDeletedSystemAccount() {

        //output a list of system account
        List<SystemAccount> retrievedList = accDAO.findAll();
        List<SystemAccount> addedList = new ArrayList<SystemAccount>();
        //put in all none deleted accounts
        if(!retrievedList.isEmpty()) {
            for (SystemAccount e : retrievedList) {
                if (e.getStatus().equals("deleted")) {
                    addedList.add(e);
                } else {
                }
            }
        }
        //return populated list
        return addedList;
    }


    //view all of user's current roles
    //input: userID
    public List<AccountRole> getAllRoles(String userID){
        List<AccountRole> tobeReturned = accDAO.findByuserID(userID).get(0).getRoles();
        return tobeReturned;
    }


    //retrieve a single account
    //input: account userID
    public List<SystemAccount> getSingleAccount(String userID){
        //output a list of system account
        return accDAO.findByuserID(userID);
    }
}
