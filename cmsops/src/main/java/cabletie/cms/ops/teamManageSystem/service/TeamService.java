package cabletie.cms.ops.teamManageSystem.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.StaffTeamDAO;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.StaffTeam;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    StaffTeamDAO stDao;
    @Autowired
    StaffDAO staffDao;

    /**
     * *Get Method* - Retrieve Staff Team
     * @param String teamID
     * @return <List<StaffTeam>>
     * Completed!
     */
    public List<StaffTeam> getST(String teamID){
        return stDao.findByTeamId(teamID);
    }

    /**
     * *Get Method* - Retrieve Staff Team List
     * @param String loc
     * @return <List<StaffTeam>>
     * Completed!
     */
    public List<StaffTeam> getSTListByLocation(String loc){
        return stDao.findByLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Unassigned Staff List
     * @param String loc
     * @return <List<Staff>>
     * Completed!
     */
    public List<Staff> getUnassignedStaffListByLocation(String loc){
        return staffDao.findUnassignedStaffByLocation(loc);
    }

    /**
     * *Create Method* - Create new Staff Team
     * @param SystemAccount user
     * @return none
     * Completed!
     */
    public void createTeam(SystemAccount user) {
        List<StaffTeam> currentList = stDao.findAll();

        StaffTeam st;
        //Create Staff Team
        if (currentList.size() < 9) {
            st = new StaffTeam("ST0" + (currentList.size() + 1), user.getStaff().getLocation());
        } else {
            st = new StaffTeam("ST" + (currentList.size() + 1), user.getStaff().getLocation());
        }

        stDao.save(st);
     }

    /**
     * *Update Method* - Assign Staffs to Team
     * @param String teamID, String staffID
     * @return none
     * Completed!
     */
    public void assignStaff(String teamID, List<String> staffIDList) {
        StaffTeam st = stDao.findByTeamId(teamID).get(0);
        for(String staffID:staffIDList) {
            Staff s = staffDao.findByStaffId(staffID).get(0);

            //Tag teamID to Staff
            s.setTeam(st);
            staffDao.save(s);

            //Add Staff to staffList in Team
            st.getStaffs().add(s);
            stDao.save(st);
        }
    }

    /**
     * *Update Method* - Unassign Staffs from Team
     * @param String teamID, String staffID
     * @return none
     * Completed!
     */
    public void unassignStaff(String teamID, String staffID) {
        StaffTeam st = stDao.findByTeamId(teamID).get(0);
        Staff s = staffDao.findByStaffId(staffID).get(0);

        //Tag teamID to Staff
        s.setTeam(null);
        staffDao.save(s);

        //Remove Staff from staffList in Team
        st.getStaffs().remove(s);
        stDao.save(st);
    }


    /**
     * *Delete Method* - Update status of Staff Team to -1.
     * @param teamID - Assume teamID is a valid record
     * Completed!
     */
    public void removeTeam(String teamID) {
        StaffTeam st = getST(teamID).get(0);

        //If there are staffs in the team
        if(!st.getStaffs().isEmpty()){
            //Unassign team from staff
            for(Staff s:st.getStaffs()){
                s.setTeam(null);
                staffDao.save(s);
            }
            st.setStaffs(new ArrayList<Staff>());
        }

        st.setStatus(-1);
        stDao.save(st);
    }
}

