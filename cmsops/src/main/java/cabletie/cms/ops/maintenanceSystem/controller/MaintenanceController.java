package cabletie.cms.ops.maintenanceSystem.controller;

import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.inventoryAssetSystem.service.AssetService;
import cabletie.cms.ops.maintenanceSystem.service.MaintenanceService;
import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceReport;
import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceRequest;
import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceTask;
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
 *maint-error GET
 *defect-viewall GET
 *defect-viewdetails GET
 *defect-updatestatus GET | POST
 *maintreq-viewall GET
 *maintreq-viewdetails GET
 *maintreq-addnew GET | POST
 *maintreq-delete GET
 *mainttask-viewall GET
 *mainttask-viewdetails GET
 *mainttask-addnew GET | POST
 *mainttask-updatedetails GET | POST
 *mainttask-delete GET
 *maint-report-viewall GET
 *maint-report-viewdetails GET
 *maint-report-edit GET | POST
 */
@Controller
@SessionAttributes("name")
public class MaintenanceController {
    @Autowired
    private MaintenanceService mainSvc;

    @GetMapping("/maint-error")
    public String noAccessPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "maint-error";
    }

    @GetMapping("/defect-viewall")
    public String viewDefectReports(@ModelAttribute("name") SystemAccount user, Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("MT")) {
                access = ar.getRoleID();
            }
        }

        if(access.equalsIgnoreCase("MT") && user.getUserGroup().equalsIgnoreCase("Manager")) {
            model.addAttribute("defList", mainSvc.getDefectList());

            return "defect-viewall";
        } else {
            return "redirect:/maint-error?err=You have no access to this function";
        }
    }

    @GetMapping("/defect-viewdetails")
    public String viewDefectReportDetails(@RequestParam("defID") String defectID, Model model){
        model.addAttribute("d", mainSvc.getDefect(defectID).get(0));

        return "defect-viewdetails";
    }

    @GetMapping("/defect-updatestatus")
    public String updateDefectStatus(@RequestParam("defID") String defectID, Model model){
        model.addAttribute("d", mainSvc.getDefect(defectID).get(0));

        List<String> statusList = new ArrayList<>();
        statusList.add("Pending");
        statusList.add("In-Progress");
        statusList.add("Rectified");
        model.addAttribute("statusList", statusList);

        return "defect-updatestatus";
    }

    @PostMapping("/defect-updatestatus")
    public String updateDefectStatus(@RequestParam("defID") String defectID,
                                     @RequestParam("status") int status,
                                     Model model){
        mainSvc.updateDefect(defectID, status);

        return "redirect:/defect-viewdetails?defID="+defectID;
    }

    @GetMapping("/defect-delete")
    public String removeDefect(@RequestParam("defID") String defectID){
        mainSvc.removeDefect(defectID);

        return "redirect:/defect-viewall";
    }

    //Maintenance Request
    @GetMapping("/maintreq-viewall")
    public String viewMaintReqList(@ModelAttribute("name") SystemAccount user, Model model) {
        //Check if Account is Manager
        if(user.getUserGroup().equalsIgnoreCase("Manager")) {
            //Get All Requests List
            List<MaintenanceRequest> mrList = mainSvc.getMRListByLocation(user.getStaff().getLocation());
            model.addAttribute("mrList", mrList);
        } else {
            //Get Requests List By The User
            List<MaintenanceRequest> mrList = mainSvc.getMRListByLocationTeam(user.getStaff().getLocation(), user);
            model.addAttribute("mrList", mrList);
        }

        return "maintreq-viewall";
    }

    @GetMapping("/maintreq-viewdetails")
    public String viewMaintReqDetails(@RequestParam("reqID") String reqID, @ModelAttribute("name") SystemAccount user, Model model) {
        MaintenanceRequest mr = mainSvc.getMR(reqID).get(0);
        model.addAttribute("mr", mr);
        model.addAttribute("s", mainSvc.getStaff(mr.getReqStaff()).get(0));

        //Get Asset
        if(mr.getAssetCat().equalsIgnoreCase("Normal")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("asset", mainSvc.getAsset(mr.getAssetID()).get(0));
        }
        //Get RollingStock
        else if(mr.getAssetCat().equalsIgnoreCase("RS")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("rs", mainSvc.getRS(mr.getAssetID()).get(0));
        }

        return "maintreq-viewdetails";
    }

    @GetMapping("/maintreq-addnew")
    public String fileNewMaintReq(@ModelAttribute("name") SystemAccount user, Model model) {
        String locale = user.getStaff().getLocation().substring(0, 1);

        //Populate assets dropdown list by staff location
        model.addAttribute("assets", mainSvc.getAssetListByLocation(user.getStaff().getLocation()));
        model.addAttribute("rs", mainSvc.getRSListBylocation(user.getStaff().getLocation()));

        return "maintreq-addnew";
    }

    @PostMapping("/maintreq-addnew")
    public String fileNewMaintReq(@RequestParam("mainreq_type") String type,
                                  @RequestParam("mainreq_desc") String desc,
                                  @RequestParam("mainreq_assetcat") String assetCat,
                                  @RequestParam(value="mainreq_asset", required=false) String assetID,
                                  @RequestParam(value="mainreq_rs", required=false) String rsID,
                                  @ModelAttribute("name") SystemAccount user) {
        if(assetCat.equalsIgnoreCase("Normal")) {
            mainSvc.createMR(type, desc, assetCat, assetID, user);
        } else if(assetCat.equalsIgnoreCase("RS")) {
            mainSvc.createMR(type, desc, assetCat, rsID, user);
        }

        if(assetCat.equals(""))
            return "redirect:/maint-error?err=Please select an asset";

        return "redirect:/maintreq-viewall";
    }

    @GetMapping("/maintreq-delete")
    public String removeMaintRequest(@RequestParam("reqID") String reqID){
        mainSvc.removeRequest(reqID);

        return "redirect:/maintreq-viewall";
    }

    //Maintenance Task
    @GetMapping("/mainttask-viewall")
    public String viewMaintTaskList(@ModelAttribute("name") SystemAccount user, Model model) {
        mainSvc.checkAllTasks();
        //Check if Account is Manager
        if(user.getUserGroup().equalsIgnoreCase("Manager")) {
            //Get All Tasks List
            List<MaintenanceTask> mtList = mainSvc.getMTListByLocation(user.getStaff().getLocation());
            model.addAttribute("mtList", mtList);
        } else {
            //Get Team Tasks List For User
            List<MaintenanceTask> mtList = new ArrayList<>();
            if(user.getStaff().getTeam() != null) {
                mtList = mainSvc.getMTListByLocationTeam(user.getStaff().getLocation(),
                        user.getStaff().getTeam().getTeamId());
            }
            model.addAttribute("mtList", mtList);
        }
        return "mainttask-viewall";
    }

    @GetMapping("/mainttask-viewdetails")
    public String viewMaintTaskDetails(@RequestParam("taskID") String taskID, Model model) {
        MaintenanceTask mt = mainSvc.getMT(taskID).get(0);
        model.addAttribute("mt", mt);

        return "mainttask-viewdetails";
    }

    @GetMapping("/mainttask-addnew")
    public String addNewMaintTask(@ModelAttribute("name") SystemAccount user, Model model) {
        //Check if Account is Manager
        if(user.getUserGroup().equalsIgnoreCase("Manager")) {
            List<MaintenanceRequest> mrList = mainSvc.getPendingMRListByLocation(user.getStaff().getLocation());
            model.addAttribute("mrList", mrList);
            model.addAttribute("st", mainSvc.getSTListByLocation(user.getStaff().getLocation()));

            return "mainttask-addnew";
        } else {
            return "redirect:/maint-error?err=You have no access to this function";
        }
    }

    @PostMapping("/mainttask-addnew")
    public String addNewMaintTask(@RequestParam("maintask_reqID") String reqID,
                                  @RequestParam("maintask_desc") String desc,
                                  @RequestParam("taskCost") double cost,
                                  @RequestParam(value="maintask_staff", required=false) String teamID,
                                  @RequestParam(value="maintask_scheduleDate", required=false) String dateScheduled,
                                  @ModelAttribute("name") SystemAccount user) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        if(!dateScheduled.equals("")) {
            Date scheduled = df.parse(dateScheduled);
            if (today.equals(dateScheduled) || scheduled.after(Calendar.getInstance().getTime())) {
                mainSvc.createMT(reqID, desc, cost, teamID, scheduled, user);
                return "redirect:/mainttask-viewall";
            } else {
                return "redirect:/maint-error?err=Scheduled date is before current date";
            }
        }else {
            return "redirect:/maint-error?err=Please choose a scheduled date";
        }
    }

    @GetMapping("/mainttask-updatedetails")
    public String updateMaintTaskDetails(@RequestParam("taskID") String taskID, @ModelAttribute("name") SystemAccount user,
                                         Model model) {
        MaintenanceTask mt = mainSvc.getMT(taskID).get(0);
        model.addAttribute("mt", mt);
        model.addAttribute("st", mainSvc.getSTListByLocation(user.getStaff().getLocation()));

        return "mainttask-updatedetails";
    }

    @PostMapping("/mainttask-updatedetails")
    public String updateMaintTaskDetails(@RequestParam("taskID") String taskID,
                                         @RequestParam(value="mainttask_scheduleDate", required=false) String dateScheduled,
                                         @RequestParam("mainttask_desc") String desc,
                                         @RequestParam(value="mainttask_staff", required=false) String teamID,
                                         @RequestParam("mainttask_status") int status,
                                         @RequestParam("mainttask_remarks") String remarks,
                                         @ModelAttribute("name") SystemAccount user) throws ParseException{

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        if(!dateScheduled.equals("")) {
            Date scheduled = df.parse(dateScheduled);
            if (today.equals(dateScheduled) || scheduled.after(Calendar.getInstance().getTime())) {
                mainSvc.updateTask(taskID, scheduled, desc, teamID, status, remarks, user);
                return "redirect:/mainttask-viewdetails?taskID=" + taskID;
            } else if(scheduled.before(Calendar.getInstance().getTime()) && (status == 2 || status == 3)){
                mainSvc.updateTask(taskID, scheduled, desc, teamID, status, remarks, user);
                return "redirect:/mainttask-viewdetails?taskID=" + taskID;
            } else {
                return "redirect:/maint-error?err=Scheduled date is before current date";
            }
        }else {
            return "redirect:/maint-error?err=Please choose a scheduled date";
        }
    }

    @GetMapping("/mainttask-delete")
    public String removeMaintTask(@RequestParam("taskID") String taskID){
        mainSvc.removeTask(taskID);

        return "redirect:/mainttask-viewall";
    }

    //Maintenance Report
    @GetMapping("/maint-report-viewall")
    public String viewAllReports(@ModelAttribute("name") SystemAccount user, Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("MT")) {
                access = ar.getRoleID();
            }
        }

        List<Staff> sList = new ArrayList<>();
        if(user.getStaff().getLocation().equalsIgnoreCase("HQ")){
            model.addAttribute("rList", mainSvc.getAllMReport());
            for (MaintenanceReport r : mainSvc.getAllMReport()) {
                sList.add(mainSvc.getStaff(r.getCreatedBy()).get(0));
            }
        }
        else if(access.equalsIgnoreCase("MT")) {
            List<MaintenanceReport> rList = mainSvc.getMReportList(user);
            model.addAttribute("rList", rList);
            for (MaintenanceReport r : rList) {
                sList.add(mainSvc.getStaff(r.getCreatedBy()).get(0));
            }
        } else {
            return "redirect:/maint-error?err=You have no access to this function";
        }
        model.addAttribute("sList", sList);

        return "maint-report-viewall";
    }

    @GetMapping("/maint-report-viewdetails")
    public String viewReport(@RequestParam("reportID") String id, Model model){
        MaintenanceReport r = mainSvc.getMReport(id).get(0);
        MaintenanceRequest mr = r.getMainReq();
        model.addAttribute("r", r);

        model.addAttribute("cStaff", mainSvc.getStaff(r.getCreatedBy()).get(0) );

        if(!r.getApprovedBy().equals("")){
            model.addAttribute("aStaff", mainSvc.getStaff(r.getApprovedBy()).get(0) );
        }

        //Get Asset
        if(mr.getAssetCat().equalsIgnoreCase("Normal")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("asset", mainSvc.getAsset(mr.getAssetID()).get(0));
        }
        //Get RollingStock
        else if(mr.getAssetCat().equalsIgnoreCase("RS")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("rs", mainSvc.getRS(mr.getAssetID()).get(0));
        }

        return "maint-report-viewdetails";
    }

    @GetMapping("/maint-report-edit")
    public String editReport(@RequestParam("reportID") String id, Model model){
        MaintenanceReport r = mainSvc.getMReport(id).get(0);
        MaintenanceRequest mr = r.getMainReq();
        model.addAttribute("r", r);

        model.addAttribute("cStaff", mainSvc.getStaff(r.getCreatedBy()).get(0) );

        if(!r.getApprovedBy().equals("")){
            model.addAttribute("aStaff", mainSvc.getStaff(r.getApprovedBy()).get(0) );
        }

        //Get Asset
        if(mr.getAssetCat().equalsIgnoreCase("Normal")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("asset", mainSvc.getAsset(mr.getAssetID()).get(0));
        }
        //Get RollingStock
        else if(mr.getAssetCat().equalsIgnoreCase("RS")){
            if(!mr.getAssetID().equals(""))
                model.addAttribute("rs", mainSvc.getRS(mr.getAssetID()).get(0));
        }

        return "maint-report-edit";
    }

    @PostMapping("/maint-report-edit")
    public String editReport(@RequestParam("reportID") String id,
                             @RequestParam("details") String details){
        mainSvc.updateReport(id, details);

        return "redirect:/maint-report-viewdetails?reportID="+id;
    }

    @GetMapping("/maint-report-approve")
    public String approveReport(@RequestParam("reportID") String id, @ModelAttribute("name") SystemAccount user){
        mainSvc.approveReport(id, user);

        return "redirect:/maint-report-viewdetails?reportID="+id;
    }
}
