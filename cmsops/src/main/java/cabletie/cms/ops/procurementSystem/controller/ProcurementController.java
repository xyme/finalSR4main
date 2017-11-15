package cabletie.cms.ops.procurementSystem.controller;

import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.procurement.ProcurementTaskDAO;
import cabletie.cms.ops.operationDBModel.procurement.ProcurementTask;
import cabletie.cms.ops.operationDBModel.procurement.PurchaseItem;
import cabletie.cms.ops.operationDBModel.procurement.RequestForPurchase;
import cabletie.cms.ops.procurementSystem.service.ProcurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elvin
 *rfp-viewall GET
 *rfp-viewdetails GET
 *rfp-addnew GET | POST
 *procure-viewall GET
 *procure-viewdetails GET | POST
 *procure-editdetails GET | POST
 *
 */
@Controller
@SessionAttributes("name")
public class ProcurementController {
    @Autowired
    private ProcurementService procureSvc;

    @GetMapping("/procure-noaccess")
    public String noAccessPage(Model model){
        String err = "You have no access to this function";
        model.addAttribute("error", err);

        return "procure-noaccess";
    }

    @GetMapping("/rfp-viewall")
    public String viewRFPList(Model model, @ModelAttribute("name") SystemAccount user) {
        //Retrieve RFP List from controller
        List<RequestForPurchase> rfpList = procureSvc.getRFPList();
        model.addAttribute("rfpList", rfpList);

        List<Staff> staff = procureSvc.getStaffByAccount(user.getUserID());
        model.addAttribute("staff", staff.get(0));

        //Get the staff's location type (D/EW/NS/W/M)
        model.addAttribute("locale", staff.get(0).getLocation().substring(0, 1));

        return "rfp-viewall";
    }

    @GetMapping("/rfp-viewdetails")
    public String viewRFPDetails(@RequestParam("sentRFPID") String rfpId, Model model) {
        List<RequestForPurchase> rfpList = procureSvc.getRFP(rfpId);
        model.addAttribute("rfp", rfpList.get(0));
        
        List<PurchaseItem> itemList = procureSvc.getItemByRfpID(rfpId);
        model.addAttribute("item", itemList.get(0));
        
        List<Staff> reqStaff = procureSvc.getRFPCreatedBy(rfpList.get(0).getCreatedBy());
        model.addAttribute("rStaff", reqStaff.get(0));

        if(rfpList.get(0).getApprovedBy() != null && rfpList.get(0).getApprovedDate() != null) {
            List<Staff> appStaff = procureSvc.getRFPCreatedBy(rfpList.get(0).getApprovedBy());
            model.addAttribute("aStaff", appStaff.get(0));
        }
        return "rfp-viewdetails";
    }

    //Handles Updates Of Status Of RFP
    @PostMapping("/rfp-viewdetails")
    public String viewRFPDetails(@RequestParam("approve_reject") String btn, @RequestParam("sentRFPID") String rfpId, @ModelAttribute("name") SystemAccount user) {
        RequestForPurchase rfp = procureSvc.getRFP(rfpId).get(0);

        procureSvc.approveRFP(rfp, btn, user);

        return "redirect:/rfp-viewdetails?sentRFPID="+rfpId;
    }

    @GetMapping("/rfp-addnew")
    public String addNewRFP() {
        return "rfp-addnew";
    }

    @PostMapping("/rfp-addnew")
    public String addNewRFP(@RequestParam("itemName") String itemName,
                            @RequestParam("itemQty") int itemQty,
                            @RequestParam("itemCost") double itemCost,
                            @RequestParam("itemDesc") String itemDesc,
                            @RequestParam("rfp_desc") String rfpDesc,
                            @ModelAttribute("name") SystemAccount user) {
        procureSvc.createRFP(itemName, itemQty, itemCost, itemDesc, rfpDesc, user);

        return "redirect:/rfp-viewall";
    }

    @GetMapping("/procure-viewall")
    public String viewProcureList(Model model, @ModelAttribute("name") SystemAccount user) {
        String locale = procureSvc.getStaffByAccount(user.getUserID()).get(0).getLocation();
        List<ProcurementTask> ptList = procureSvc.getPTList();
        model.addAttribute("ptList", ptList);

        if(locale.substring(0, 1).equalsIgnoreCase("W")) {
            return "procure-viewall";
        } else {
            return "redirect:/procure-noaccess";
        }
    }

    @GetMapping("/procure-viewdetails")
    public String viewProcureDetails(@RequestParam("ptID") String procID, Model model) {
        List<ProcurementTask> task = procureSvc.getPT(procID);
        model.addAttribute("pt", task.get(0));

        if(task.get(0).getPurchaseBy() != null) {
            List<Staff> pStaff = procureSvc.getRFPCreatedBy(task.get(0).getPurchaseBy());
            model.addAttribute("pStaff", pStaff.get(0));
        }

        return "procure-viewdetails";
    }

    @GetMapping("/procure-editdetails")
    public String editProcureDetails(@RequestParam("ptID") String procID, Model model) {
        List<ProcurementTask> task = procureSvc.getPT(procID);
        model.addAttribute("pt", task.get(0));

        if(task.get(0).getPurchaseBy() != null) {
            List<Staff> pStaff = procureSvc.getRFPCreatedBy(task.get(0).getPurchaseBy());
            model.addAttribute("pStaff", pStaff.get(0));
        }

        return "procure-editdetails";
    }

    //Handle Updates To Procurement Task
    @PostMapping("/procure-editdetails")
    public String editProcureDetails(@RequestParam("ptID") String procID,
                                     @RequestParam("procure_status") int status,
                                     @ModelAttribute("name") SystemAccount user) {
        List<ProcurementTask> task = procureSvc.getPT(procID);
        procureSvc.updatePT(task.get(0), status, user);

        return "redirect:/procure-viewdetails?ptID="+procID;
    }
}
