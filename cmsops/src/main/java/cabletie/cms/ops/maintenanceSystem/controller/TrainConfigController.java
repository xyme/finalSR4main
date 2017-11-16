package cabletie.cms.ops.maintenanceSystem.controller;

import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.maintenanceSystem.service.TrainConfigService;
import cabletie.cms.ops.operationDBModel.train.RollingStock;
import cabletie.cms.ops.operationDBModel.train.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Elvin
 *trainconfig-error GET
 *trainconfig-viewall GET
 *trainconfig-viewdetails GET
 *trainconfig-editdetails GET | POST
 *trainconfig-delete GET
 */
@Controller
@SessionAttributes("name")
public class TrainConfigController {
    @Autowired
    TrainConfigService trainSvc;

    @GetMapping("/trainconfig-error")
    public String noAccessPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "trainconfig-error";
    }

    @GetMapping("/trainconfig-viewall")
    public String viewAllTrainConfig(@ModelAttribute("name") SystemAccount user, Model model){
        String access = "";
        for(AccountRole ar: user.getRoles()) {
            if(ar.getRoleID().equalsIgnoreCase("TM")) {
                access = ar.getRoleID();
            }
        }

        if(access.equalsIgnoreCase("TM")) {
            model.addAttribute("trainList", trainSvc.getDepotTrainList(user.getStaff().getLocation()));
            return "trainconfig-viewall";
        } else {
            return "redirect:/trainconfig-error?err=You have no access to this function";
        }
    }

    @GetMapping("/trainconfig-viewdetails")
    public String viewTrainConfigDetails(@RequestParam("trainID") String trainID, Model model){
        Train t = trainSvc.getTrain(trainID).get(0);
        List<RollingStock> rs = t.getRolling();
        Collections.sort(rs);

        model.addAttribute("t", t);
        model.addAttribute("rs", rs);

        List<String> rsStatus = new ArrayList<>();
        if(!rs.isEmpty()) {
            for (RollingStock r : rs) {
                switch (r.getStatus()) {
                    case 2:
                        rsStatus.add("Assigned");
                        break;
                    case 3:
                        rsStatus.add("Maintenance");
                        break;
                }
            }
        }
        model.addAttribute("rsStatus", rsStatus);

        return "trainconfig-viewdetails";
    }

    @GetMapping("/trainconfig-addnew")
    public String addNewTrainConfig(@ModelAttribute("name") SystemAccount user, Model model){
        model.addAttribute("rs", trainSvc.getRSByDepot(user.getStaff().getLocation()));

        return "trainconfig-addnew";
    }

    @PostMapping("/trainconfig-addnew")
    public String addNewTrainConfig(@RequestParam("train_type") String type,
                                    @RequestParam(value="rs_thin1", required = false) String thin1,
                                    @RequestParam(value="rs_thin2", required = false) String thin2,
                                    @RequestParam(value="rs_thin3", required = false) String thin3,
                                    @RequestParam(value="rs_fat1", required = false) String fat1,
                                    @RequestParam(value="rs_fat2", required = false) String fat2,
                                    @RequestParam(value="rs_fat3", required = false) String fat3,
                                    @RequestParam(value="rs_fat4", required = false) String fat4,
                                    @RequestParam(value="rs_fat5", required = false) String fat5,
                                    @ModelAttribute("name") SystemAccount user){
        ArrayList<String> rsIDList = new ArrayList<String>();

        if(type.equalsIgnoreCase("THIN")){
            if(!thin1.equals("") && !thin2.equals("") && !thin3.equals("")) {
                if(thin1.equals(thin3)) {
                    return "redirect:/trainconfig-error?err=Please select different rolling stocks from each selection list";
                } else {
                    rsIDList.add(thin1);
                    rsIDList.add(thin2);
                    rsIDList.add(thin3);
                }
            } else {
                return "redirect:/trainconfig-error?err=Please select 1 rolling stock from each selection list";
            }
        } else if(type.equalsIgnoreCase("FAT")){
            if(!fat1.equals("") && !fat2.equals("") && !fat3.equals("") && !fat4.equals("") && !fat5.equals("")) {
                if(fat1.equals(fat5) || fat2.equals(fat3) || fat2.equals(fat4) || fat3.equals(fat4)) {
                    return "redirect:/trainconfig-error?err=Please select different rolling stocks from each selection list";
                } else {
                    rsIDList.add(fat1);
                    rsIDList.add(fat2);
                    rsIDList.add(fat3);
                    rsIDList.add(fat4);
                    rsIDList.add(fat5);
                }
            } else {
                return "redirect:/trainconfig-error?err=Please select 1 rolling stock from each selection list";
            }
        }

        if(!type.equals("")) {
            trainSvc.createTrain(type, rsIDList, user);
            return "redirect:/trainconfig-viewall";
        } else
            return "redirect:/trainconfig-error?err=Unsuccessful creation (You have not select train type)";
    }

    @GetMapping("/trainconfig-editdetails")
    public String editTrainDetails(@RequestParam("trainID") String id,
                                   @ModelAttribute("name") SystemAccount user,
                                   Model model){
        Train t = trainSvc.getTrain(id).get(0);
        model.addAttribute("t", t);

        model.addAttribute("rs", trainSvc.getEditRSList(user.getStaff().getLocation(), id));

        List<RollingStock> rs = t.getRolling();
        Collections.sort(rs);
        if(t.getType().equalsIgnoreCase("THIN")){
            model.addAttribute("thin1", rs.get(0).getSerialNo());
            model.addAttribute("thin2", rs.get(1).getSerialNo());
            model.addAttribute("thin3", rs.get(2).getSerialNo());
        } else if(t.getType().equalsIgnoreCase("FAT")){
            model.addAttribute("fat1", rs.get(0).getSerialNo());
            model.addAttribute("fat2", rs.get(1).getSerialNo());
            model.addAttribute("fat3", rs.get(2).getSerialNo());
            model.addAttribute("fat4", rs.get(3).getSerialNo());
            model.addAttribute("fat5", rs.get(4).getSerialNo());
        }

        List<String> statusList = new ArrayList<>();
        statusList.add("Non-Operational");
        statusList.add("Operational");
        model.addAttribute("statusList", statusList);

        return "trainconfig-editdetails";
    }

    @PostMapping("/trainconfig-editdetails")
    public String editTrainDetails(@RequestParam("trainID") String id,
                                   @RequestParam(value="rs_thin1", required = false) String thin1,
                                   @RequestParam(value="rs_thin2", required = false) String thin2,
                                   @RequestParam(value="rs_thin3", required = false) String thin3,
                                   @RequestParam(value="rs_fat1", required = false) String fat1,
                                   @RequestParam(value="rs_fat2", required = false) String fat2,
                                   @RequestParam(value="rs_fat3", required = false) String fat3,
                                   @RequestParam(value="rs_fat4", required = false) String fat4,
                                   @RequestParam(value="rs_fat5", required = false) String fat5,
                                   @RequestParam("train_status") int status,
                                   @ModelAttribute("name") SystemAccount user){

        Train t = trainSvc.getTrain(id).get(0);
        ArrayList<String> rsIDList = new ArrayList<String>();

        if(t.getType().equalsIgnoreCase("THIN")){
            if(!thin1.equals("") && !thin2.equals("") && !thin3.equals("")) {
                if(thin1.equals(thin3)) {
                    return "redirect:/trainconfig-error?err=Please select different rolling stocks from each selection list";
                } else {
                    rsIDList.add(thin1);
                    rsIDList.add(thin2);
                    rsIDList.add(thin3);
                }
            } else {
                return "redirect:/trainconfig-error?err=Update Failed! Please select 1 rolling stock from each selection list";
            }
        } else if(t.getType().equalsIgnoreCase("FAT")){
            if(!fat1.equals("") && !fat2.equals("") && !fat3.equals("") && !fat4.equals("") && !fat5.equals("")) {
                if(fat1.equals(fat5) || fat2.equals(fat3) || fat2.equals(fat4) || fat3.equals(fat4)) {
                    return "redirect:/trainconfig-error?err=Please select different rolling stocks from each selection list";
                } else {
                    rsIDList.add(fat1);
                    rsIDList.add(fat2);
                    rsIDList.add(fat3);
                    rsIDList.add(fat4);
                    rsIDList.add(fat5);
                }
            } else {
                return "redirect:/trainconfig-error?err=Update Failed! Please select 1 rolling stock from each selection list";
            }
        }

        trainSvc.updateTrain(id, rsIDList, status);
        return "redirect:/trainconfig-viewdetails?trainID="+id;
    }

    @GetMapping("/trainconfig-delete")
    public String deleteTrainConfig(@RequestParam("trainID") String id){
        
        
        if(trainSvc.checkAssignment(id)) {
        	return "redirect:/trainconfig-error";
        }
        
        trainSvc.removeTrain(id);
        
        return "redirect:/trainconfig-viewall";
    }
}
