package cabletie.cms.ops.teamManageSystem.controller;

import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.teamManageSystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Elvin
 *team-noaccess GET
 *team-viewall GET
 *team-viewdetails GET
 *team-unassignstaff GET
 *team-assignstaff GET | POST
 *team-addnew GET
 *team-delete GET
 */
@Controller
@SessionAttributes("name")
public class TeamController {
    @Autowired
    TeamService teamSvc;

    @GetMapping("/team-noaccess")
    public String noAccessPage(Model model){
        String err = "You have no access to this function";
        model.addAttribute("error", err);

        return "team-noaccess";
    }

    @GetMapping("/team-viewall")
    public String viewAllTeams(@ModelAttribute("name") SystemAccount user, Model model){
        //Get First Char of Staff location [D|Depot E|EW N|NS]
        String locale = user.getStaff().getLocation().substring(0, 1);

        if(locale.equalsIgnoreCase("D") || locale.equalsIgnoreCase("E") || locale.equalsIgnoreCase("N")){
            model.addAttribute("teamList", teamSvc.getSTListByLocation(user.getStaff().getLocation()));

            return "team-viewall";
        } else {
            return "redirect:/team-noaccess";
        }
    }

    @GetMapping("/team-viewdetails")
    public String viewTeamDetails(@RequestParam("teamID") String id, Model model){
        model.addAttribute("t", teamSvc.getST(id).get(0));

        return "team-viewdetails";
    }

    @GetMapping("/team-assign")
    public String assignStaffToTeams(@RequestParam("teamID") String id, @ModelAttribute("name") SystemAccount user, Model model){
        model.addAttribute("t", teamSvc.getST(id).get(0));
        model.addAttribute("staffs", teamSvc.getUnassignedStaffListByLocation(user.getStaff().getLocation()));

        return "team-assign";
    }

    @PostMapping("/team-assign")
    public String assignStaffToTeams(@RequestParam("teamID") String id,
                                     @RequestParam(value="team_staff", required=false) List<String> staffIDList){
        teamSvc.assignStaff(id, staffIDList);

        return "redirect:/team-viewdetails?teamID="+id;
    }

    @GetMapping("/team-unassign")
    public String unassignStaffFromTeams(@RequestParam("teamID") String id, @RequestParam("staffID") String staffID){
        teamSvc.unassignStaff(id, staffID);

        return "redirect:/team-viewdetails?teamID="+id;
    }

    @GetMapping("/team-addnew")
    public String addNewTeam(@ModelAttribute("name") SystemAccount user){
        teamSvc.createTeam(user);

        return "redirect:/team-viewall";
    }


    @GetMapping("/team-delete")
    public String deleteTeam(@RequestParam("teamID") String id){
        teamSvc.removeTeam(id);

        return "redirect:/team-viewall";
    }
}
