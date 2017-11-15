package cabletie.cms.ops.eHR.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.StaffTeamDAO;
import cabletie.cms.ops.corporateDBDao.eHR.AttendanceRecordDAO;
import cabletie.cms.ops.corporateDBDao.eHR.RosterDAO;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.corporateDBModel.eHR.AttendanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    StaffDAO sDao;
    @Autowired
    StaffTeamDAO stDao;
    @Autowired
    RosterDAO rDao;
    @Autowired
    AttendanceRecordDAO arDao;

    /**
     * *Get Method* - Retrieve All Attendance Records
     * @param loc - String
     * @param month - int (from 1-12)
     * @return <List<AttendanceRecord>>
     * Completed!
     */
    public List<AttendanceRecord> getAllAttendance(String loc, int month, int year){
        Calendar cal = Calendar.getInstance();
        List<AttendanceRecord> returnList = new ArrayList<>();

        for(AttendanceRecord ar:arDao.findAll()){
            cal.setTime(ar.getDate());

            if( (cal.get(Calendar.MONTH)+1)==month && cal.get(Calendar.YEAR)==year && ar.getStaff().getLocation().equalsIgnoreCase(loc) ){
                returnList.add(ar);
            }
        }

        return returnList;
    }

    /**
     * *Get Method* - Retrieve My Attendance Records
     * @param user - SystemAccount
     * @param month - int (from 1-12)
     * @return <List<AttendanceRecord>>
     * Completed!
     */
    public List<AttendanceRecord> getMyAttendance(SystemAccount user, int month, int year){
        Calendar cal = Calendar.getInstance();
        List<AttendanceRecord> returnList = new ArrayList<>();

        for(AttendanceRecord ar:arDao.findAll()){
            cal.setTime(ar.getDate());

            if( (cal.get(Calendar.MONTH)+1)==month && cal.get(Calendar.YEAR)==year && ar.getStaff().getStaffId().equalsIgnoreCase(user.getStaff().getStaffId()) ){
                returnList.add(ar);
            }
        }

        return returnList;
    }

    /**
     * *Create Method* - Create Attendance Record of a Staff (Check-In)
     * @param  staffID - String
     * @return none
     * Completed!
     */
    public void checkInAttendance(Date d, String shift, String staffID) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());

        //Create Attendance Record
        AttendanceRecord ar = new AttendanceRecord(d, shift, ts, sDao.findByStaffId(staffID).get(0));

        arDao.save(ar);
    }

    /**
     * *Create Method* - Check-Out Attendance of a Staff
     * @param  staffID - String
     * @return none
     * Completed!
     */
    public void checkOutAttendance(Date d, String staffID) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());
        AttendanceRecord ar = new AttendanceRecord();

        for(AttendanceRecord a:arDao.findByStaff(sDao.findByStaffId(staffID).get(0)) ){
            if(df.format(a.getDate()).equals(df.format(d)) ){
                ar = a;
            }
        }

        ar.setCheckOutTime(ts);
        arDao.save(ar);
    }

    /**
     * *Check Method* - Check whether current time is within the shift timings
     * @param  shift - String
     * @return valid - boolean
     * Completed!
     */
    public boolean isTimeBetweenTwoTime(String shift, String loc) throws ParseException {
        String locale = loc.substring(0, 1);
        boolean valid = false;

        //Time variables
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String argCurrentTime = df.format(new Date());
        String argStartTime="";
        String argEndTime="";

        //Set start & end timings for shift patterns (Stations & Depots)
        //30 minutes extra time for updating attendance of the shift
        switch(shift){
            case "A":
                //Station Shift A Timing
                if(locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N")) {
                    argStartTime = "04:00:00";
                    argEndTime = "12:00:00";
                }
                //Depot Shift A Timing
                else if(locale.equalsIgnoreCase("D")) {
                    argStartTime = "04:00:00";
                    argEndTime = "13:00:00";
                }
                break;
            case "B":
                //Station Shift B Timing
                if(locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N")) {
                    argStartTime = "11:00:00";
                    argEndTime = "19:00:00";
                }
                //Depot Shift B Timing
                else if(locale.equalsIgnoreCase("D")) {
                    argStartTime = "12:00:00";
                    argEndTime = "21:00:00";
                }
                break;
            case "C":
                //Station Shift C Timing
                if(locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N")) {
                    argStartTime = "18:00:00";
                    argEndTime = "02:00:00";
                }
                //Depot Shift C Timing
                else if(locale.equalsIgnoreCase("D")) {
                    argStartTime = "20:00:00";
                    argEndTime = "05:00:00";
                }
                break;
        }

        if (shift.equalsIgnoreCase("A") || shift.equalsIgnoreCase("B") || shift.equalsIgnoreCase("C")) {
            // Start Time
            Date startTime = new SimpleDateFormat("HH:mm:ss").parse(argStartTime);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);

            // Current Time
            Date currentTime = new SimpleDateFormat("HH:mm:ss").parse(argCurrentTime);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentTime);

            // End Time
            Date endTime = new SimpleDateFormat("HH:mm:ss").parse(argEndTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            //
            if (currentTime.compareTo(endTime) < 0) {
                currentCalendar.add(Calendar.DATE, 1);
                currentTime = currentCalendar.getTime();
            }

            if (startTime.compareTo(endTime) < 0) {
                startCalendar.add(Calendar.DATE, 1);
                startTime = startCalendar.getTime();
            }

            //
            if (currentTime.before(startTime)) {
                valid = false;
            } else {
                if (currentTime.after(endTime)) {
                    endCalendar.add(Calendar.DATE, 1);
                    endTime = endCalendar.getTime();
                }

                if (currentTime.before(endTime)) {
                    valid = true;
                } else {
                    valid = false;
                }
            }
        }

        return valid;
    }

    /**
     * *Get Method* - Get the list of months til current month
     * @return mList - List<String>
     * Completed!
     */
    public List<String> getMonthsList(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);

        String months[] = {"JANUARY", "FEBRUARY", "MARCH", "APRIL",
                "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
                "OCTOBER", "NOVEMBER", "DECEMBER"};

        List<String> mList = new ArrayList<>();

        //3 months of previous year to view the attendances
        for(int i=9; i<months.length; i++){
            mList.add(months[i]+" "+(year-1));
        }

        //Months of Current Year
        for(int i=0; i<=(now.get(Calendar.MONTH)); i++){
            mList.add(months[i]+" "+year);
        }

        return mList;
    }
}
