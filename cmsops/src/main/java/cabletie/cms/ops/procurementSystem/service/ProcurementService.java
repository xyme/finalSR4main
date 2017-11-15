package cabletie.cms.ops.procurementSystem.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.procurement.ProcurementTaskDAO;
import cabletie.cms.ops.operationDBDao.procurement.PurchaseItemDAO;
import cabletie.cms.ops.operationDBDao.procurement.RequestForPurchaseDAO;
import cabletie.cms.ops.operationDBModel.procurement.ProcurementTask;
import cabletie.cms.ops.operationDBModel.procurement.PurchaseItem;
import cabletie.cms.ops.operationDBModel.procurement.RequestForPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProcurementService {
    @Autowired
    PurchaseItemDAO purchaseItemDao;

    @Autowired
    RequestForPurchaseDAO rfpDao;

    @Autowired
    ProcurementTaskDAO procureTaskDao;

    @Autowired
    StaffDAO staffDao;

    /**
     * *Get Method* - Retrieve RequestForPurchase List
     * @param none
     * @return <List<RequestForPurchase>>
     * Completed!
     */
    public List<RequestForPurchase> getRFPList(){
        return rfpDao.findAll();
    }

    /**
     * *Get Method* - Retrieve RequestForPurchase by RFPID
     * @param String rfpID
     * @return <List<RequestForPurchase>>
     * Completed!
     */
    public List<RequestForPurchase> getRFP(String rfpID){
        return rfpDao.findByrfpID(rfpID);
    }

    /**
     * *Get Method* - Retrieve Staff by Account
     * @param String userID
     * @return <List<Staff>>
     * Completed!
     */
    public List<Staff> getStaffByAccount(String userID){
        return staffDao.findByAccount(userID);
    }

    /**
     * *Get Method* - Retrieve Staff by RFP createdBy
     * @param String staffID
     * @return <List<Staff>>
     * Completed!
     */
    public List<Staff> getRFPCreatedBy(String staffID){
        return staffDao.findByStaffId(staffID);
    }
    
    /**
     * *Get Method* - Retrieve item by rfpID
     * @param String rfpID
     * @return <List<PurchaseItem>>
     * Completed!
     */
    public List<PurchaseItem> getItemByRfpID(String rfpID){
        return purchaseItemDao.findByRfp(rfpID);
    }

    /**
     * *Get Method* - Retrieve Procurement Task List
     * @param none
     * @return <List<ProcurementTask>>
     * Completed!
     */
    public List<ProcurementTask> getPTList(){
        return procureTaskDao.findAll();
    }

    /**
     * *Get Method* - Retrieve Procurement Task
     * @param none
     * @return <List<ProcurementTask>>
     * Completed!
     */
    public List<ProcurementTask> getPT(String procID){
        return procureTaskDao.findByProcID(procID);
    }

    /**
     * *Create Method* - Create RFP record
     * @param String itemName, int itemQty, double itemCost, String itemDesc, String desc, SystemAccount user
     * @return none
     * Completed!
     */
    public void createRFP(String itemName, int itemQty, double itemCost, String itemDesc, String desc, SystemAccount user){
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

            //Get staff name using userID
            List<Staff> staff = staffDao.findByAccount(user.getUserID());

            List<RequestForPurchase> currentRecordList = rfpDao.findAll();
            RequestForPurchase rfp;
            //Create RFP
            if (currentRecordList.size() < 9) {
                rfp = new RequestForPurchase("R0" + (currentRecordList.size() + 1), ts, staff.get(0).getStaffId(), staff.get(0).getDepartment(), staff.get(0).getLocation(), desc);
            } else {
                rfp = new RequestForPurchase("R" + (currentRecordList.size() + 1), ts, staff.get(0).getStaffId(), staff.get(0).getDepartment(), staff.get(0).getLocation(), desc);
            }

            //Save RFP entity into database
            rfpDao.save(rfp);

            //Create PurchaseItem
            PurchaseItem item = new PurchaseItem(itemName, itemDesc, itemQty, itemCost);

            //Tag RFP to PurchaseItem
            item.setRfp(rfp);

            //Save PurchaseItem entity into database
            purchaseItemDao.save(item);
    }

    /**
     * *Update Method* - Approve RFP
     * @param RequestForPurchase rfp, String btn, SystemAccount user
     * @return none
     * Completed!
     */
    public void approveRFP(RequestForPurchase rfp, String btn, SystemAccount user){
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());
        List<Staff> staff = staffDao.findByAccount(user.getUserID());

        List<ProcurementTask> currentPTList = procureTaskDao.findAll();
        ProcurementTask pt;

        switch(btn) {
            case "approve":
                rfp.setApprovalStatus(1);
                //Create Procurement Task
                if(currentPTList.size() < 9) {
                    pt = new ProcurementTask("P0"+(currentPTList.size()+1), ts);
                }
                else{
                    pt = new ProcurementTask("P"+(currentPTList.size()+1), ts);
                }

                //Tag RFP to Procurement Task
                pt.setRfp(rfp);
                //Save ProcurementTask entity into database
                procureTaskDao.save(pt);
                break;
            case "reject":
                rfp.setApprovalStatus(-1);
                break;
        }
        rfp.setApprovedBy(staff.get(0).getStaffId());
        rfp.setApprovedDate(ts);
        rfpDao.save(rfp);
    }

    /**
     * *Update Method* - Update RFP record
     * @param RequestForPurchase rfp
     * @return none
     * Completed!
     */
    public void updateRFP(RequestForPurchase rfp){
         rfpDao.save(rfp);
    }

    /**
     * *Update Method* - Update Status of Procurement Task record
     * @param ProcurementTask pt, int status, SystemAccount user
     * @return none
     * Completed!
     */
    public void updatePT(ProcurementTask pt, int status, SystemAccount user){
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        List<Staff> staff = staffDao.findByAccount(user.getUserID());

        pt.setPurchaseBy(staff.get(0).getStaffId());
        pt.setPurchaseDate(ts);
        pt.setStatus(status);
        procureTaskDao.save(pt);
    }
}
