package cabletie.cms.ops.maintenanceSystem.controller;

import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.maintenanceSystem.service.InspectService;
import cabletie.cms.ops.operationDBModel.maintenance.Inspection;
import cabletie.cms.ops.operationDBModel.maintenance.InspectionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Elvin
 *inspect-error GET
 *inspect-viewall GET
 *inspect-viewdetails GET
 *inspect-editdetails GET | POST
 *inspect-addnew GET | POST
 *inspect-delete GET
 *inspect-report-viewall GET
 *inspect-report-viewdetails GET
 *inspect-report-edit GET | POST
 *inspect-report-approve GET | POST
 */
@Controller
@SessionAttributes("name")
public class InspectController {
    @Autowired
    private InspectService inspectSvc;

    @GetMapping("/inspect-error")
    public String noAccessPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "inspect-error";
    }

    @GetMapping("inspect-viewall")
    public String viewAllInspection(@ModelAttribute("name") SystemAccount user, Model model){
        inspectSvc.checkAllInspections();
        if(user.getUserGroup().equalsIgnoreCase("Manager")){
            model.addAttribute("inspectList", inspectSvc.getInspectByLocation(user.getStaff().getLocation()));

        }else {
            //Get Assigned Inspection List For User
            List<Inspection> iList = new ArrayList<>();
            if(user.getStaff().getTeam() != null) {
                iList = inspectSvc.getInspectListByLocationTeam(user.getStaff().getLocation(),
                        user.getStaff().getTeam().getTeamId());
            }
            model.addAttribute("inspectList", iList);
        }
        return "inspect-viewall";
    }

    @GetMapping("inspect-viewdetails")
    public String viewInspectionDetails(@RequestParam("inspectID") String inspectId, Model model){
        Inspection i = inspectSvc.getInspect(inspectId).get(0);
        model.addAttribute("i", i);

        //Get Asset
        if(i.getAssetCat().equalsIgnoreCase("Normal")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("asset", inspectSvc.getAsset(i.getAssetId()).get(0));
        }
        //Get RollingStock
        else if(i.getAssetCat().equalsIgnoreCase("RS")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("rs", inspectSvc.getRS(i.getAssetId()).get(0));
        }

        return "inspect-viewdetails";
    }

    @GetMapping("inspect-editdetails")
    public String editInspectionDetails(@RequestParam("inspectID") String inspectId, @ModelAttribute("name") SystemAccount user, Model model){
        model.addAttribute("i", inspectSvc.getInspect(inspectId).get(0));
        model.addAttribute("st", inspectSvc.getSTListByLocation(user.getStaff().getLocation()));

        List<String> statusList = new ArrayList<>();
        statusList.add("In-Progress");
        statusList.add("Completed");
        model.addAttribute("statusList", statusList);

        return "inspect-editdetails";
    }

    @PostMapping("/inspect-editdetails")
    public String editInspectionDetails(@RequestParam("inspectID") String id,
                                        @RequestParam("inspect_desc") String desc,
                                        @RequestParam("inspect_dateScheduled") String dateScheduled,
                                        @RequestParam("inspect_remarks") String remarks,
                                        @RequestParam(value="inspect_staff", required=false) String teamID,
                                        @RequestParam("inspect_status") int status,
                                        @ModelAttribute("name") SystemAccount user) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date scheduled = df.parse(dateScheduled);
        String today = df.format(Calendar.getInstance().getTime());

        if(today.equals(dateScheduled) || scheduled.after(Calendar.getInstance().getTime())) {
            inspectSvc.updateInspection(id, desc, dateScheduled, remarks, teamID, status, user);
            return "redirect:/inspect-viewdetails?inspectID=" + id;
        } else if(scheduled.before(Calendar.getInstance().getTime()) && (status == 1 || status == 2)) {
            inspectSvc.updateInspection(id, desc, dateScheduled, remarks, teamID, status, user);
            return "redirect:/inspect-viewdetails?inspectID=" + id;
        } else {
            return "redirect:/inspect-error?err=Scheduled date is before current date";
        }
    }

    @GetMapping("inspect-addnew")
    public String addNewInspection(@ModelAttribute("name") SystemAccount user, Model model){
        if(user.getUserGroup().equalsIgnoreCase("Manager")){
            model.addAttribute("st", inspectSvc.getSTListByLocation(user.getStaff().getLocation()));
            //Populate assets dropdown list by staff location
            model.addAttribute("assets", inspectSvc.getAssetListBylocation(user.getStaff().getLocation()));
            model.addAttribute("rs", inspectSvc.getRSListBylocation(user.getStaff().getLocation()));
            return "inspect-addnew";
        }else {
            return "redirect:/inspect-error?err=You have no access to this function";
        }
    }

    @PostMapping("/inspect-addnew")
    public String addNewInspection(@RequestParam("inspect_type") String type,
                                   @RequestParam("inspect_desc") String desc,
                                   @RequestParam("inspect_dateScheduled") String dateScheduled,
                                   @RequestParam("inspect_assetcat") String assetCat,
                                   @RequestParam(value="inspect_asset", required=false) String assetID,
                                   @RequestParam(value="inspect_rs", required=false) String rsID,
                                   @RequestParam(value="inspect_staff", required=false) String teamID,
                                   @ModelAttribute("name") SystemAccount user) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        if (!assetCat.equals("")) {
            if(!dateScheduled.equals("")) {
                Date scheduled = df.parse(dateScheduled);
                if (today.equals(dateScheduled) || scheduled.after(Calendar.getInstance().getTime())) {
                    if (assetCat.equalsIgnoreCase("Normal")) {
                        inspectSvc.createInspection(type, scheduled, desc, assetCat, assetID, teamID, user);
                    } else if (assetCat.equalsIgnoreCase("RS")) {
                        inspectSvc.createInspection(type, scheduled, desc, assetCat, rsID, teamID, user);
                    }
                } else {
                    return "redirect:/inspect-error?err=Scheduled date is before current date";
                }
            } else {
                return "redirect:/inspect-error?err=Please choose a scheduled date";
            }
        } else {
            return "redirect:/inspect-error?err=Please select an asset";

        }

        return "redirect:/inspect-viewall";
    }

    @GetMapping("inspect-delete")
    public String deleteInspection(@RequestParam("inspectID") String inspectId){
        inspectSvc.removeInspection(inspectId);

        return "redirect:/inspect-viewall";
    }

    //Inspection Report
    @GetMapping("/inspect-report-viewall")
    public String viewAllReports(@ModelAttribute("name") SystemAccount user, Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("MT")) {
                access = ar.getRoleID();
            }
        }

        List<Staff> sList = new ArrayList<>();
        if(user.getStaff().getLocation().equalsIgnoreCase("HQ")){
            model.addAttribute("rList", inspectSvc.getAllInReport());

            for (InspectionReport r : inspectSvc.getAllInReport()) {
                sList.add(inspectSvc.getStaff(r.getCreatedBy()).get(0));
            }
        }
        else if(access.equalsIgnoreCase("MT")) {
            List<InspectionReport> rList = inspectSvc.getInReportList(user);
            model.addAttribute("rList", rList);

            for (InspectionReport r : rList) {
                sList.add(inspectSvc.getStaff(r.getCreatedBy()).get(0));
            }
        } else {
            return "redirect:/inspect-error?err=You have no access to this function";
        }
        model.addAttribute("sList", sList);
        return "inspect-report-viewall";
    }

    @GetMapping("/inspect-report-viewdetails")
    public String viewReport(@RequestParam("reportID") String id, Model model){
        InspectionReport r = inspectSvc.getInReport(id).get(0);
        Inspection i = r.getInspect();
        model.addAttribute("r", r);

        model.addAttribute("cStaff", inspectSvc.getStaff(r.getCreatedBy()).get(0) );

        if(!r.getApprovedBy().equals("")){
            model.addAttribute("aStaff", inspectSvc.getStaff(r.getApprovedBy()).get(0) );
        }

        //Get Asset
        if(i.getAssetCat().equalsIgnoreCase("Normal")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("asset", inspectSvc.getAsset(i.getAssetId()).get(0));
        }
        //Get RollingStock
        else if(i.getAssetCat().equalsIgnoreCase("RS")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("rs", inspectSvc.getRS(i.getAssetId()).get(0));
        }

        return "inspect-report-viewdetails";
    }

    @GetMapping("/inspect-report-edit")
    public String editReport(@RequestParam("reportID") String id, Model model){
        InspectionReport r = inspectSvc.getInReport(id).get(0);
        Inspection i = r.getInspect();
        model.addAttribute("r", r);

        model.addAttribute("cStaff", inspectSvc.getStaff(r.getCreatedBy()).get(0) );

        if(!r.getApprovedBy().equals("")){
            model.addAttribute("aStaff", inspectSvc.getStaff(r.getApprovedBy()).get(0) );
        }

        //Get Asset
        if(i.getAssetCat().equalsIgnoreCase("Normal")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("asset", inspectSvc.getAsset(i.getAssetId()).get(0));
        }
        //Get RollingStock
        else if(i.getAssetCat().equalsIgnoreCase("RS")){
            if(!i.getAssetId().equals(""))
                model.addAttribute("rs", inspectSvc.getRS(i.getAssetId()).get(0));
        }

        return "inspect-report-edit";
    }

    @PostMapping("/inspect-report-edit")
    public String editReport(@RequestParam("reportID") String id,
                             @RequestParam("details") String details){
        inspectSvc.updateReport(id, details);

        return "redirect:/inspect-report-viewdetails?reportID="+id;
    }

    @GetMapping("/inspect-report-approve")
    public String approveReport(@RequestParam("reportID") String id, @ModelAttribute("name") SystemAccount user){
        inspectSvc.approveReport(id, user);

        return "redirect:/inspect-report-viewdetails?reportID="+id;
    }
}
