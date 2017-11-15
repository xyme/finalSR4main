package cabletie.cms.ops.eHR.controller;

import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.eHR.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Elvin
 *roster-error GET
 *roster-generate GET | POST
 *roster-viewall GET
 *roster-viewall-table GET
 *roster-myview GET
 */
@Controller
@SessionAttributes("name")
public class RosterController {
    @Autowired
    RosterService rSvc;

    //Controller Calendar variables
    private Calendar cal;
    private int month;
    private int year;

    @GetMapping("/roster-error")
    public String errPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "roster-error";
    }

    @GetMapping("/roster-generate")
    public String generateRoster(@RequestParam(value="msg", required=false) String msg,
                                 @ModelAttribute("name") SystemAccount user,
                                 Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("RO")) {
                access = ar.getRoleID();
            }
        }

        //Check whether account have access to RO (Rostering) & if account is manager
        if(access.equalsIgnoreCase("RO") && user.getUserGroup().equalsIgnoreCase("Manager")
            && !user.getStaff().getLocation().equalsIgnoreCase("HQ")) {

            List<String> locList = new ArrayList<>();
            locList.add(user.getStaff().getLocation());

            //For location dropdown
            model.addAttribute("locs", locList);
            //For months dropdown
            model.addAttribute("months", rSvc.getMonthsList(user.getStaff().getLocation()));
            model.addAttribute("message", msg);

            return "roster-generate";
        } else {
            return "redirect:/roster-error?err=You have no access to this function";
        }
    }

    @PostMapping("/roster-generate")
    public String generateRoster(@RequestParam("roster_loc") String loc,
                                 @RequestParam("roster_month") String month) throws ParseException{
        if(month.equals(""))
            return "redirect:/roster-error?err=Please select a month";


        rSvc.generateRoster(loc, month);

        return "redirect:/roster-generate?msg=Roster Generated Successfully";
    }

    @GetMapping("/roster-viewall")
    public String viewAllRoster(@ModelAttribute("name") SystemAccount user, Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("RO")) {
                access = ar.getRoleID();
            }
        }

        //Check whether account have access to RO (Rostering) & if account is manager
        if(access.equalsIgnoreCase("RO") && user.getUserGroup().equalsIgnoreCase("Manager")) {
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

            return "roster-viewall";
        } else {
            return "redirect:/roster-error?err=You have no access to this function";
        }
    }

    @GetMapping("/roster-viewall-table")
    public String viewAllRoster(@RequestParam("roster_loc") String loc,
                                @RequestParam(value="prev_next_btn", required=false) String btn,
                                Model model){
        if(btn == null){
            cal = Calendar.getInstance();
            month = cal.get(Calendar.MONTH)+1;
            year = cal.get(Calendar.YEAR);
        } else {
            if(btn.equalsIgnoreCase("prev")){
                month--;
            } else if(btn.equalsIgnoreCase("next")) {
                month++;
            }
        }

        // Get the number of days in month
        int daysInMonth = rSvc.getNumOfDaysInMonth(year, month);

        model.addAttribute("month", Month.of(month).name());
        model.addAttribute("year", year);
        model.addAttribute("loc", loc);
        model.addAttribute("locChar", loc.substring(0, 1));

        //Populate table columns for days
        ArrayList<Integer> days = new ArrayList<>();
        for(int i=1; i<=daysInMonth; i++){
            days.add(i);
        }
        model.addAttribute("days", days);
        model.addAttribute("rosters", rSvc.getRosterByLocation(loc, month, year) );

        return "roster-viewall-table";
    }

    @GetMapping("/roster-myview")
    public String viewMyRoster(@ModelAttribute("name") SystemAccount user,
                               @RequestParam(value="prev_next_btn", required=false) String btn,
                               Model model) {
        String locale = user.getStaff().getLocation().substring(0, 1);

        if (locale.equalsIgnoreCase("D") || locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N")) {
            if (btn == null) {
                cal = Calendar.getInstance();
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
            } else {
                if (btn.equalsIgnoreCase("prev")) {
                    month--;
                } else if (btn.equalsIgnoreCase("next")) {
                    month++;
                }
            }

            // Get the number of days in month
            int daysInMonth = rSvc.getNumOfDaysInMonth(year, month);

            model.addAttribute("month", Month.of(month).name());
            model.addAttribute("year", year);
            model.addAttribute("loc", user.getStaff().getLocation());
            model.addAttribute("locChar", user.getStaff().getLocation().substring(0, 1));

            //Populate table columns for days
            ArrayList<Integer> days = new ArrayList<>();
            for (int i = 1; i <= daysInMonth; i++) {
                days.add(i);
            }
            model.addAttribute("days", days);
            model.addAttribute("roster", rSvc.getStaffRoster(user, month, year));

            return "roster-myview";
        }
        else
            return "redirect:/roster-error?err=You have no access to this function";
    }
}
