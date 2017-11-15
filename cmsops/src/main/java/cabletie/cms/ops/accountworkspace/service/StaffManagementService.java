package cabletie.cms.ops.accountworkspace.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaffManagementService {

    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private SystemAccountDAO acctDAO;
    //create new staff
    //Input: idNumber, name, gender, date of birth, contact number, email, address, nationality race, martial status, department, position, contract start, contract end, salary, years worked, number of penalty, eligibility for promotion
    public boolean createStaff(String location, String idNo, String name, String gender, String dateOfBirth, String contactNo, String email, String address, String nationality, String race, String maritalStatus, String department, String position, String contractStart, String contractEnd, Integer salary) {

        if(!staffDAO.findByIdNo(idNo).isEmpty()){
            return false;
        }

        Staff staff = new Staff(location, idNo, name, gender, dateOfBirth, contactNo, email, address, nationality, race, maritalStatus, department, position, contractStart, contractEnd, salary, "Active");

        staffDAO.save(staff);

        return true;
    }

    //edit the staff details
    //input: staff unique ID and respective fields to be updated/edited (example: name)
    public boolean editStaff(Staff staff) {

        //output: if cannot find the existing staff return false;
        if (staff.getStatus() == "deleted") {
            return false;
        }
        //Do the saving here
        else {
            staffDAO.save(staff);
            return true;
        }
    }

    //delete the staff and its associated account
    //input: staff unique ID and respective account tied to
    public boolean deleteStaff(String staffID) {

        //output: if cannot find the existing staff return false;
        List<Staff> staff = staffDAO.findByStaffId(staffID);
        if (staff.isEmpty()) {
            return false;
        } else if (staff.get(0).getStatus() == "deleted"){
            return false;
        }
        //Do the delete here
        //when deleting the staff, need to change the respective system account's status to deleted
        else {
            //change status of tied system to deleted if systemacct exist for the staff
            if(staff.get(0).getAccount() == null){
            }
            else {
                //delete the account associated
                SystemAccount system = staff.get(0).getAccount();
                system.setStatus("deleted");
                //delete the system - staff association
                system.setStaff(null);
                acctDAO.save(system);
            }

            //change status of staff to deleted

            staff.get(0).setStatus("deleted");
            //delete the staff - system association
            staff.get(0).setAccount(null);
            staffDAO.save(staff.get(0));
            return true;
        }
    }

    //retrive staff by the staff id
    //input: staff unique ID
    public List<Staff> getStaff (String staffID) {

        //output: empty list or a staff exists
        List<Staff> list = staffDAO.findByStaffId(staffID);
        return list;
    }

    //retrive all avaliable staff
    //input : none
    public List<Staff> getallStaff (){

        //output: if cannot find any staffs created
        if(staffDAO.findAll().isEmpty()){
            return new ArrayList<Staff>();
        }
        //check if all of the staff return have their status deleted
        else {
           List<Staff> retrievedList = staffDAO.findAll();
            List<Staff> listofStaff = new ArrayList<Staff>();

            if(!retrievedList.isEmpty()) {
                for (Staff e : retrievedList) {
            
                        listofStaff.add(e);
                   
                }
            }
           return listofStaff;
        }
    }

    //retrive all staff which is not tied to any account
    //input none
    public List<Staff> getAllNoAccStaff(){
        //output: if cannot find any staffs created
        if(staffDAO.findAll().isEmpty()){
            return new ArrayList<Staff>();
        }
        //check if all of the staff return have their status deleted
        else {
            List<Staff> retrievedList = staffDAO.findAll();
            List<Staff> listofStaff = new ArrayList<Staff>();
            if(!retrievedList.isEmpty()) {
                for (Staff e : retrievedList) {
                    //add into the list if staff does not have the status "deleted" and the staff is not tied with any exising account
                    if (!e.getStatus().equals("deleted")) {
                    	if(e.getAccount() == null || e.getAccount().getStatus().equals("deleted")) {
                        listofStaff.add(e);
                    	}
                    } else {
                    }
                }
            }
            return listofStaff;
        }
    }


    //For audit trail
    //retrive all staff which is not tied to any account
    //input none
    public List<Staff> getDeletedStaff(){
        //output: if cannot find any staffs created
        if(staffDAO.findAll().isEmpty()){
            return new ArrayList<Staff>();
        }
        //check if all of the staff return have their status deleted
        else {
            List<Staff> retrievedList = staffDAO.findAll();
            List<Staff> listofStaff = new ArrayList<Staff>();

            if(!retrievedList.isEmpty()) {
                for (Staff e : retrievedList) {
                    //add into the list if staff have the status "deleted"
                    if (e.getStatus().equals("deleted")) {
                        listofStaff.add(e);
                    } else {
                    }
                }
            }
           return listofStaff;
        }
    }

}