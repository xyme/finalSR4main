package cabletie.cms.ops.inventoryAssetSystem.controller;

import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.inventoryAssetSystem.service.RollingStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elvin
 *rs-error GET
 *rs-viewall GET
 *rs-viewdetails GET
 *rs-editdetails GET | POST
 *rs-addnew GET | POST
 *rs-delete GET
 */
@Controller
@SessionAttributes("name")
public class RollingStockController {
    @Autowired
    private RollingStockService rsSvc;

    @GetMapping("/rs-error")
    public String noAccessPage(@RequestParam("err") String err, Model model){
        model.addAttribute("error", err);

        return "rs-error";
    }

    @GetMapping("rs-viewall")
    public String viewAllRS(@ModelAttribute("name") SystemAccount user, Model model){
        String locale = user.getStaff().getLocation().substring(0, 1);

        //User must be from depot
        if(locale.equalsIgnoreCase("D")){
            model.addAttribute("rsList", rsSvc.getAllRSByDepot(user.getStaff().getLocation()));

            return "rs-viewall";
        }else {
            return "redirect:/rs-error?err=You have no access to this function";
        }
    }

    @GetMapping("rs-viewdetails")
    public String viewRSDetails(@RequestParam("rsSerial") String rsSerial, Model model){
        model.addAttribute("rs", rsSvc.getRS(rsSerial).get(0));

        return "rs-viewdetails";
    }

    @GetMapping("rs-editdetails")
    public String editRSDetails(@RequestParam("rsSerial") String rsSerial, Model model){
        model.addAttribute("rs", rsSvc.getRS(rsSerial).get(0));

        //Populate status dropdown
        List<String> statusList = new ArrayList<>();
        statusList.add("Non-Operational");
        statusList.add("Operational");
        model.addAttribute("statusList", statusList);

        return "rs-editdetails";
    }

    @PostMapping("/rs-editdetails")
    public String editRSDetails(@RequestParam("rsSerial") String rsSerial, @RequestParam("milleage") int milleage,
                                @RequestParam("status") int status){
        rsSvc.updateRS(rsSerial, milleage, status);

        return "redirect:/rs-viewdetails?rsSerial="+rsSerial;
    }

    @GetMapping("rs-addnew")
    public String addNewRS(){

        return "rs-addnew";
    }

    @PostMapping("rs-addnew")
    public String addNewRS(@RequestParam("serial") String serialNo, @RequestParam("type") String type,
                           @ModelAttribute("name") SystemAccount user){
        boolean addSuccess = rsSvc.createRS(serialNo, type, user);

        if(addSuccess)
            return "redirect:/rs-viewall";
        else
            return"redirect:/rs-error?err=Unsuccessful creation of Rolling Stock. (Duplicated Serial Number)";
    }

    @GetMapping("rs-delete")
    public String deleteRS(@RequestParam("rsSerial") String rsSerial){
        if(rsSvc.getRS(rsSerial).get(0).getTrain() != null) {
            return "redirect:/rs-error?err=Selected Rolling Stock can't be removed (It is currently assigned to a Train, Please unassign from Train before removing)";
        } else {
            rsSvc.removeRS(rsSerial);
            return "redirect:/rs-viewall";
        }
    }
}
