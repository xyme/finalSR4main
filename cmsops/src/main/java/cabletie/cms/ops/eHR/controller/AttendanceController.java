package cabletie.cms.ops.eHR.controller;

import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.corporateDBModel.eHR.AttendanceRecord;
import cabletie.cms.ops.corporateDBModel.eHR.Roster;
import cabletie.cms.ops.eHR.service.AttendanceService;
import cabletie.cms.ops.eHR.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Elvin
 *attend-error GET
 *attend-update-home GET
 *attend-update GET
 *attend-update-checkin GET
 *attend-update-checkout GET
 *attend-view GET
 *attend-view-table GET
 *attend-myview GET
 *attend-myview-table GET
 */
@Controller
@SessionAttributes("name")
public class AttendanceController {
    @Autowired
    RosterService rSvc;
    @Autowired
    AttendanceService aSvc;

    @GetMapping("/attend-error")
    public String errPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "attend-error";
    }

    @GetMapping("/attend-update-home")
    public String viewAttendanceHome(@ModelAttribute("name") SystemAccount user, Model model) throws ParseException{
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("HR")) {
                access = ar.getRoleID();
            }
        }

        //Check whether account have access to HR functions (EHR) & if account is manager & if user is not from HQ
        if(access.equalsIgnoreCase("HR") && user.getUserGroup().equalsIgnoreCase("Manager") &&
           !user.getStaff().getLocation().equalsIgnoreCase("HQ")) {

            String locale = user.getStaff().getLocation().substring(0, 1);
            Calendar cal = Calendar.getInstance();
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            model.addAttribute("month", Month.of(month).name());
            model.addAttribute("year", year);

            //A list of days to return
            List<Integer> dayList = new ArrayList<>();
            dayList.add(currentDay);

            //Check which shift does the time falls into
            ArrayList<Boolean> betweens = new ArrayList<>();

            //List of rosters to display
            List<Roster> rList = new ArrayList<>();

            //Once the hour of currentDay reaches 0, currentDay - 1
            //Eg Time of Day 5 reaches 00:00:00, currentDay must still be 5 and not 6.
            switch(locale) {
                case "D":
                    if (cal.get(Calendar.HOUR_OF_DAY) >= 0 && cal.get(Calendar.HOUR_OF_DAY) < 5) {
                        currentDay--;
                        dayList.add(0, currentDay);
                    }
                    break;
                case "E": case "N":
                    if (cal.get(Calendar.HOUR_OF_DAY) >= 0 && cal.get(Calendar.HOUR_OF_DAY) < 4) {
                        currentDay--;
                        dayList.remove(0);
                        dayList.add(currentDay);
                    }
                    break;
            }

            for(int d: dayList) {
                //Current day is 1st and require shift from the day before (last day of previous month)
                if(d==0){
                    //If it is January, require last day of December of previous year
                    if(month==1){
                        for (Roster r : rSvc.getRosterByLocation(user.getStaff().getLocation(), 12, year-1)) {
                            boolean between = aSvc.isTimeBetweenTwoTime(r.getDay(Month.of(12).maxLength()), user.getStaff().getLocation());
                            betweens.add(between);
                            rList.add(r);
                        }
                    } else {
                        for (Roster r : rSvc.getRosterByLocation(user.getStaff().getLocation(), month - 1, year)) {
                            boolean between = aSvc.isTimeBetweenTwoTime(r.getDay(Month.of(month - 1).maxLength()), user.getStaff().getLocation());
                            betweens.add(between);
                            rList.add(r);
                        }
                    }
                } else {
                    for (Roster r : rSvc.getRosterByLocation(user.getStaff().getLocation(), month, year)) {
                        boolean between = aSvc.isTimeBetweenTwoTime(r.getDay(d), user.getStaff().getLocation());
                        betweens.add(between);
                        rList.add(r);
                    }
                }
            }

            /*for (Roster r : rSvc.getRosterByLocation(user.getStaff().getLocation(), month, year)) {
                rList.add(r);
            }*/

            //model.addAttribute("rosters", rSvc.getRosterByLocation(user.getStaff().getLocation(), month, year));
            model.addAttribute("rosters", rList);
            model.addAttribute("dayList", dayList);

            if(month==1){
                model.addAttribute("lastDayPrevMonthInt", 31);
                model.addAttribute("lastDayPrevMonth","31 December "+(year-1));
            } else {
                model.addAttribute("lastDayPrevMonthInt", Month.of(month - 1).maxLength());
                model.addAttribute("lastDayPrevMonth", Month.of(month - 1).maxLength()+" "+Month.of(month - 1).name()+" "+year);
            }

            model.addAttribute("timeBetweens", betweens);
            model.addAttribute("locChar", user.getStaff().getLocation().substring(0,1) );

            return "attend-update-home";
        } else {
            return "redirect:/attend-error?err=You have no access to this function";
        }
    }

    @GetMapping("/attend-update")
    public String updateAttendance(@RequestParam("rosterID") long rosterID,
                                   @RequestParam("day") int day,
                                   Model model){
        String shift="";

        Roster r = rSvc.getRoster(rosterID).get(0);

        //Create Date
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(r.getYear(), r.getMonth()-1, day);
        Date d = cal.getTime();

        switch(r.getDay(day)){
            case "A":
                if(r.getLocation().substring(0, 1).equalsIgnoreCase("E") || r.getLocation().substring(0, 1).equalsIgnoreCase("N"))
                    shift = "A [0400H - 1130H]";
                else if(r.getLocation().substring(0, 1).equalsIgnoreCase("D"))
                    shift = "A [0400H - 1230H]";
                break;
            case "B":
                if(r.getLocation().substring(0, 1).equalsIgnoreCase("E") || r.getLocation().substring(0, 1).equalsIgnoreCase("N"))
                    shift = "B [1100H - 1830H]";
                else if(r.getLocation().substring(0, 1).equalsIgnoreCase("D"))
                    shift = "B [1200H - 2030H]";
                break;
            case "C":
                if(r.getLocation().substring(0, 1).equalsIgnoreCase("E") || r.getLocation().substring(0, 1).equalsIgnoreCase("N"))
                    shift = "C [1800H - 0130H]";
                else if(r.getLocation().substring(0, 1).equalsIgnoreCase("D"))
                    shift = "C [2000H - 0430H]";
                break;
        }

        model.addAttribute("r", r);
        model.addAttribute("day", day);
        model.addAttribute("shift", shift);
        model.addAttribute("date", df.format(d));

        return "attend-update";
    }

    @GetMapping("/attend-update-checkin")
    public String checkInAttendance(@RequestParam("staffID") String staffID,
                                    @RequestParam("rosterID") long rosterID,
                                    @RequestParam("day") int day,
                                    Model model) {
        Roster r = rSvc.getRoster(rosterID).get(0);

        //Create Date
        Calendar cal = Calendar.getInstance();
        cal.set(r.getYear(), r.getMonth()-1, day);
        Date d = cal.getTime();

        aSvc.checkInAttendance(d, r.getDay(day), staffID);

        return "redirect:/attend-update?rosterID="+rosterID+"&day="+day;
    }

    @GetMapping("/attend-update-checkout")
    public String checkOutAttendance(@RequestParam("staffID") String staffID,
                                     @RequestParam("rosterID") long rosterID,
                                     @RequestParam("day") int day,
                                     Model model) {
        Roster r = rSvc.getRoster(rosterID).get(0);

        //Create Date
        Calendar cal = Calendar.getInstance();
        cal.set(r.getYear(), r.getMonth()-1, day);
        Date d = cal.getTime();

        aSvc.checkOutAttendance(d, staffID);

        return "redirect:/attend-update?rosterID="+rosterID+"&day="+day;
    }

    @GetMapping("/attend-view")
    public String viewAllAttendance(@ModelAttribute("name") SystemAccount user, Model model){
        Calendar cal = Calendar.getInstance();

        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("HR")) {
                access = ar.getRoleID();
            }
        }

        //Check whether account have access to HR (eHR) & if account is manager
        if(access.equalsIgnoreCase("HR") && user.getUserGroup().equalsIgnoreCase("Manager")) {
            List<String> locList = new ArrayList<>();

            //If account location is HQ
            if(user.getStaff().getLocation().equalsIgnoreCase("HQ")) {
                //Get all locations from StaffTeam Records
                for(String s:rSvc.getAllSTLocations()){
                    locList.add(s);
                }
            } else {
                //Only get account's location
                locList.add(user.getStaff().getLocation());
            }

            //For location dropdown
            model.addAttribute("locs", locList);
            //For months dropdown
            model.addAttribute("months", aSvc.getMonthsList());
            model.addAttribute("currentMonth", Month.of(cal.get(Calendar.MONTH)+1).name());
            model.addAttribute("currentYear", cal.get(Calendar.YEAR));

            return "attend-view";
        } else {
            return "redirect:/attend-error?err=You have no access to this function";
        }
    }

    @GetMapping("/attend-view-table")
    public String viewAllRoster(@RequestParam("attend_loc") String loc,
                                @RequestParam("attend_month") String monthYear,
                                Model model){
        //Split monthYear with " "
        String[] parts = monthYear.split(" ");
        //Month from monthYear
        String m = parts[0];
        //Year from monthYear
        String y = parts[1];

        Calendar cal = Calendar.getInstance();
        int month = Month.valueOf(m).getValue();
        int year = Integer.parseInt(y);

        model.addAttribute("month", m);
        model.addAttribute("year", year);
        model.addAttribute("loc", loc);
        model.addAttribute("locChar", loc.substring(0, 1));

        model.addAttribute("attends", aSvc.getAllAttendance(loc, month, year));

        return "attend-view-table";
    }

    @GetMapping("/attend-myview")
    public String viewMyAttendance(@ModelAttribute("name") SystemAccount user, Model model){
        Calendar cal = Calendar.getInstance();

        //For months dropdown
        model.addAttribute("months", aSvc.getMonthsList());
        model.addAttribute("currentMonth", Month.of(cal.get(Calendar.MONTH)+1).name());
        model.addAttribute("currentYear", cal.get(Calendar.YEAR));

        String locale = user.getStaff().getLocation().substring(0, 1);
        if (locale.equalsIgnoreCase("D") || locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N"))
            return "attend-myview";
        else
            return "redirect:/attend-error?err=You have no access to this function";
    }

    @GetMapping("/attend-myview-table")
    public String viewMyRoster(@RequestParam("attend_month") String monthYear,
                               @ModelAttribute("name") SystemAccount user,
                               Model model){
        //Split monthYear with " "
        String[] parts = monthYear.split(" ");
        //Month from monthYear
        String m = parts[0];
        //Year from monthYear
        String y = parts[1];

        Calendar cal = Calendar.getInstance();
        int month = Month.valueOf(m).getValue();
        int year = Integer.parseInt(y);

        model.addAttribute("month", m);
        model.addAttribute("year", year);
        model.addAttribute("locChar", user.getStaff().getLocation().substring(0, 1));

        model.addAttribute("attends", aSvc.getMyAttendance(user, month, year));

        return "attend-myview-table";
    }
}
