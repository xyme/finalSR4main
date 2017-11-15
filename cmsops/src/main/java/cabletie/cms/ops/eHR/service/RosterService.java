package cabletie.cms.ops.eHR.service;

import cabletie.cms.ops.corporateDBDao.StaffTeamDAO;
import cabletie.cms.ops.corporateDBDao.eHR.RosterDAO;
import cabletie.cms.ops.corporateDBModel.StaffTeam;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.corporateDBModel.eHR.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RosterService {
    @Autowired
    RosterDAO rDao;
    @Autowired
    StaffTeamDAO stDao;

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
     * *Get Method* - Retrieve All Locations from StaffTeam Records
     * @param
     * @return <List<String>>
     * Completed!
     */
    public List<String> getAllSTLocations(){
        return stDao.getDistinctLocations();
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
     * *Get Method* - Retrieve Roster Record
     * @param id - long
     * @return <List<Roster>>
     * Completed!
     */
    public List<Roster> getRoster(long id){
        return rDao.findByRosterId(id);
    }

    /**
     * *Get Method* - Retrieve Team's Roster Record
     * @param month - Integer
     * @param year - Integer
     * @param team - String
     * @return <List<Roster>>
     * Completed!
     */
    public List<Roster> getTeamRoster(int month, int year, String team){
        return rDao.findStaffRoster(month, year, team);
    }

    /**
     * *Get Method* - Retrieve Roster Record By Location
     * @param loc - String
     * @param month - Integer
     * @param year - Integer
     * @return <List<Roster>>
     * Completed!
     */
    public List<Roster> getRosterByLocation(String loc){
        return rDao.findByLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Roster Record By Location
     * @param loc - String
     * @param month - Integer
     * @param year - Integer
     * @return <List<Roster>>
     * Completed!
     */
    public List<Roster> getRosterByLocation(String loc, int month, int year){
        List<Roster> rosters = rDao.findByMonthAndYear(month, year);
        List<StaffTeam> teams = stDao.findByLocation(loc);
        List<Roster> returnList = new ArrayList<>();

        for(Roster r:rosters) {
            for (StaffTeam st : teams) {
                if(r.getTeam().equals(st))
                    returnList.add(r);
            }
        }

        return returnList;
    }

    /**
     * *Get Method* - Retrieve Account's Roster Record
     * @param user - SystemAccount
     * @param month - Integer
     * @param year - Integer
     * @return <List<Roster>>
     * Completed!
     */
    public List<Roster> getStaffRoster(SystemAccount user, int month, int year){
        return rDao.findStaffRoster(month, year, user.getStaff().getTeam().getTeamId() );
    }

    /**
     * *Create Method* - Generate Rosters
     * @param none
     * Completed!
     */
    public void generateRoster(String loc, String month) throws ParseException{
        boolean success=false;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Month.valueOf(month.toUpperCase()).getValue()-1);
        int argMonth = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        // Get the number of days in month
        int daysInMonth = getNumOfDaysInMonth(cal.get(Calendar.YEAR), argMonth);
        int daysInPrev = 0;

        //Shift patterns
        String shifts[] = {"A", "B", "C", "O", "S"};

        List<StaffTeam> stList = stDao.findByLocation(loc);

        int teamcount=0;
        int shiftcount=0;
        Roster prevMonth = null;

        for(StaffTeam st:stList) {
            shiftcount = teamcount;
            if (teamcount < stList.size()) {
                //Check whether team is active or inactive
                if (st.getStatus() != -1) {
                    Roster r = new Roster(st.getLocation(), argMonth, cal.get(Calendar.YEAR), st);

                    if(getRosterByLocation(loc).size() >= 5) {
                        //If month passed in is January, check December of previous year for last shift pattern
                        if (argMonth == 1) {
                            daysInPrev = getNumOfDaysInMonth(year - 1, 12);
                            prevMonth = getTeamRoster(12, year - 1, st.getTeamId()).get(0);
                        } else {
                            daysInPrev = getNumOfDaysInMonth(year, argMonth - 1);
                            prevMonth = getTeamRoster(argMonth - 1, year, st.getTeamId()).get(0);
                        }

                        //Get Index of last shift code for the team and +1 to get the starting shift code
                        shiftcount = getIndexOfLastShift(prevMonth.getDay(daysInPrev)) + 1;

                        if(shiftcount >= 5)
                            shiftcount = 0;
                    }

                    //Set shift patterns to each roster record
                    for (int i = 1; i <= daysInMonth; i++) {
                        r.setDay(i, shifts[shiftcount]);
                        shiftcount++;

                        //Reset shift counter
                        if (shiftcount == shifts.length)
                            shiftcount = 0;
                    }
                    rDao.save(r);
                    teamcount++;
                }
            }
        }
    }

    /**
     * *Get Method* - Get the list of months that have no generated roster
     * @return mList - List<String>
     * Completed!
     */
    public List<String> getMonthsList(String loc){
        Calendar now = Calendar.getInstance();

        String months[] = {"JANUARY", "FEBRUARY", "MARCH", "APRIL",
                "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
                "OCTOBER", "NOVEMBER", "DECEMBER"};

        List<String> mList = new ArrayList<>();
        List<String> mExistList = new ArrayList<>();

        for(int i:rDao.getMonthsGenerated(now.get(Calendar.YEAR), loc)){
            mExistList.add(Month.of(i).name());
        }

        for(int i=(now.get(Calendar.MONTH)); i<months.length; i++){
            mList.add(months[i]);
        }

        //Eliminate months that are already generated
        for(String m:mExistList){
            if(mList.contains(m))
                mList.remove(m);
        }

        return mList;
    }

    /**
     * *Get Method* - Get the number of days in the month
     * @param year - Integer
     * @param month - Integer
     * @return daysInMonth - Integer
     * Completed!
     */
    public int getNumOfDaysInMonth(int year, int month){
        YearMonth ym = YearMonth.of(year, month);
        int daysInMonth = ym.lengthOfMonth();

        return daysInMonth;
    }

    /**
     * *Get Method* - Get index of last shift code
     * @param shift - String
     * @return index - Integer
     * Completed!
     */
    public int getIndexOfLastShift(String shift){
        int index=0;

        switch(shift){
            case "A":
                index=0;
                break;
            case "B":
                index=1;
                break;
            case "C":
                index=2;
                break;
            case "O":
                index=3;
                break;
            case "S":
                index=4;
                break;
        }

        return index;
    }
}
