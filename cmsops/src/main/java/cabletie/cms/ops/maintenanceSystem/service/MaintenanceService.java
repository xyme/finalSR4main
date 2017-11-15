package cabletie.cms.ops.maintenanceSystem.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.StaffTeamDAO;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.StaffTeam;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.assetDAO.AssetDAO;
import cabletie.cms.ops.operationDBDao.maintenance.DefectReportDAO;
import cabletie.cms.ops.operationDBDao.maintenance.MaintenanceReportDAO;
import cabletie.cms.ops.operationDBDao.maintenance.MaintenanceRequestDAO;
import cabletie.cms.ops.operationDBDao.maintenance.MaintenanceTaskDAO;
import cabletie.cms.ops.operationDBDao.train.RollingStockDAO;
import cabletie.cms.ops.operationDBDao.train.TrainDAO;
import cabletie.cms.ops.operationDBModel.assets.Asset;
import cabletie.cms.ops.operationDBModel.maintenance.*;
import cabletie.cms.ops.operationDBModel.train.RollingStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    AssetDAO aDao;
    @Autowired
    RollingStockDAO rsDao;
    @Autowired
    TrainDAO trainDao;
    @Autowired
    StaffDAO sDao;
    @Autowired
    StaffTeamDAO stDao;
    @Autowired
    DefectReportDAO defDao;
    @Autowired
    MaintenanceRequestDAO mrDao;
    @Autowired
    MaintenanceTaskDAO mtDao;
    @Autowired
    MaintenanceReportDAO mReportDao;
    @Autowired
    DefectReportDAO defectDao;

    /**
     * *Get Method* - Retrieve Asset
     * @param String assetSerial
     * @return <List<Asset>>
     * Completed!
     */
    public List<Asset> getAsset(String assetSerial){
        return aDao.findByassetSerialID(assetSerial);
    }

    /**
     * *Get Method* - Retrieve Asset List By Location
     * @param String infraID
     * @return <List<Asset>>
     * Completed!
     */
    public List<Asset> getAssetListByLocation(String infraID){
        return aDao.findByInfraID(infraID);
    }

    /**
     * *Get Method* - Retrieve RollingStock
     * @param String serial
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getRS(String serial){
        return rsDao.findBySerialNo(serial);
    }

    /**
     * *Get Method* - Retrieve RollingStock List By Location
     * @param String infraID
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getRSListBylocation(String infraID){
        return rsDao.findByDepot(infraID);
    }

    /**
     * *Get Method* - Retrieve Staff
     * @param String teamID
     * @return <List<Staff>>
     * Completed!
     */
    public List<Staff> getStaff(String staffId){
        return sDao.findByStaffId(staffId);
    }

    /**
     * *Get Method* - Retrieve Staff Team
     * @param String teamID
     * @return <List<StaffTeam>>
     * Completed!
     */
    public List<StaffTeam> getST(String teamID){
        return stDao.findByTeamId(teamID);
    }

    /**
     * *Get Method* - Retrieve Staff Team List
     * @param String loc
     * @return <List<StaffTeam>>
     * Completed!
     */
    public List<StaffTeam> getSTListByLocation(String loc){
        return stDao.findByLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Maintenance Request By Request ID
     * @param String id
     * @return <List<MaintenanceRequest>>
     * Completed!
     */
    public List<MaintenanceRequest> getMR(String id){
        return mrDao.findByReqID(id);
    }

    /**
     * *Get Method* - Retrieve Maintenance Request List By Location
     * @param String loc
     * @return <List<MaintenanceRequest>>
     * Completed!
     */
    public List<MaintenanceRequest> getMRListByLocation(String loc){
        return mrDao.findByReqLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Maintenance Request List By Location & By Team
     * @param String loc, SystemAccount user
     * @return <List<MaintenanceRequest>>
     * Completed!
     */
    public List<MaintenanceRequest> getMRListByLocationTeam(String loc, SystemAccount user){
        List<MaintenanceRequest> mrList = mrDao.findByReqLocation(loc);
        List<MaintenanceRequest> returnList = new ArrayList<>();

        for(MaintenanceRequest r:mrList){
            if(r.getReqStaff() != null && !r.getReqStaff().isEmpty()) {
                if (r.getReqStaff().equalsIgnoreCase(user.getStaff().getStaffId())) {
                    returnList.add(r);
                }
            }
        }

        return returnList;
    }

    /**
     * *Get Method* - Retrieve Pending/In-Progress Maintenance Request List By Location
     * @param String loc
     * @return <List<MaintenanceRequest>>
     * Completed!
     */
    public List<MaintenanceRequest> getPendingMRListByLocation(String loc){
        List<MaintenanceRequest> tempList = mrDao.findByReqLocation(loc);
        List<MaintenanceRequest> mrList = new ArrayList<>();

        for(MaintenanceRequest r:tempList){
            if(r.getStatus() == 0){
                mrList.add(r);
            }
        }

        return mrList;
    }

    /**
     * *Create Method* - Create new Maintenance Request
     * @param String reqType, String reqDesc, String assetID, SystemAccount user
     * @return none
     * Completed!
     */
    public void createMR(String reqType, String reqDesc, String assetCat, String assetID, SystemAccount user){
        Date date = Calendar.getInstance().getTime();

        List<MaintenanceRequest> currentMRList = mrDao.findAll();
        MaintenanceRequest mr;
        //Create Maintenance Request
        if (currentMRList.size() < 9) {
            mr = new MaintenanceRequest("MR0" + (currentMRList.size() + 1), date, user.getStaff().getStaffId(),
                                        user.getStaff().getLocation(), reqType, reqDesc, assetCat, assetID);
        } else {
            mr = new MaintenanceRequest("MR" + (currentMRList.size() + 1), date, user.getStaff().getStaffId(),
                                        user.getStaff().getLocation(), reqType, reqDesc, assetCat, assetID);
        }
        mrDao.save(mr);
    }

    /**
     * *Delete Method* - Update status of Maintenance Request as Cancelled.
     * @param reqID - Assume reqID is a valid record
     * Completed!
     */
    public void removeRequest(String reqID) {
        MaintenanceRequest mr = getMR(reqID).get(0);
        MaintenanceTask mt = mr.getMainTask();

        if(mt != null){
            mt.setStatus(-1);
            mtDao.save(mt);
        }

        mr.setStatus(-1);
        mrDao.save(mr);
    }

    /**
     * *Get Method* - Retrieve Maintenance Task By Task ID
     * @param String id
     * @return <List<MaintenanceTask>>
     * Completed!
     */
    public List<MaintenanceTask> getMT(String id){
        return mtDao.findByTaskID(id);
    }

    /**
     * *Get Method* - Retrieve Maintenance Task List
     * @param none
     * @return <List<MaintenanceTask>>
     * Completed!
     */
    public List<MaintenanceTask> getMTList(){
        return mtDao.findAll();
    }

    /**
     * *Get Method* - Retrieve Maintenance Task List By Location
     * @param String id
     * @return <List<MaintenanceTask>>
     * Completed!
     */
    public List<MaintenanceTask> getMTListByLocation(String loc){
        return mtDao.findByLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Maintenance Task List By Location and Staff team
     * @param String loc, String team
     * @return <List<MaintenanceTask>>
     * Completed!
     */
    public List<MaintenanceTask> getMTListByLocationTeam(String loc, String team){
        return mtDao.findByLocationAndStaffTeamID(loc, team);
    }

    /**
     * *Create Method* - Create new Maintenance Task
     * @param String reqType, String reqDesc, String assetID, SystemAccount user
     * @return none
     * Completed!
     */
    public void createMT(String reqID, String desc, double cost, String team, Date dateScheduled, SystemAccount user){
        MaintenanceRequest mr = getMR(reqID).get(0);

        List<MaintenanceTask> currentMTList = mtDao.findAll();
        MaintenanceTask mt;
        //Create Maintenance Task
        if (currentMTList.size() < 9) {
            mt = new MaintenanceTask("MT0" + (currentMTList.size() + 1), desc, user.getStaff().getLocation(), cost);
        } else {
            mt = new MaintenanceTask("MT" + (currentMTList.size() + 1), desc, user.getStaff().getLocation(), cost);
        }

        //Tag Staff Team to Maintenance Request
        mt.setStaffTeamID(team);

        //If teamID provided is not empty then set task status to "Assigned"
        if (!team.equals("")) {
            mt.setStatus(1);
            //Update Maintenance Request status to Assigned
            mr.setStatus(1);
        }

        //Set Schedule Date
        mt.setDateSchedule(dateScheduled);
        mr.setDateSchedule(dateScheduled);

        //Tag Maintenance Task to Maintenance Request
        mt.setMainReq(mr);
        mtDao.save(mt);

        mrDao.save(mr);
    }

    /**
     * *Update Method* - Update Maintenance Task Details.
     * @param String taskID, String teamID, int status
     * Completed!
     */
    public void updateTask(String taskID, Date dateSchedule, String desc, String teamID, int status, String remarks, SystemAccount user) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());
        String scheduled = df.format(dateSchedule);

        MaintenanceTask mt = mtDao.findByTaskID(taskID).get(0);
        MaintenanceRequest mr = mt.getMainReq();

        mt.setDescription(desc);
        mt.setRemarks(remarks);
        mt.setDateSchedule(dateSchedule);
        mt.setStaffTeamID(teamID);

        //A team is selected
        if(!teamID.equals(" ")) {
            //If Date Scheduled is Today
            if(today.equals(scheduled)){
                mt.setStatus(2);
                mr.setStatus(2);
                updateAssetStatus(mr, 1);
            } else if(dateSchedule.after(Calendar.getInstance().getTime()) ){
                mt.setStatus(1);
                mr.setStatus(1);
                mr.setDateSchedule(dateSchedule);
            }
            mtDao.save(mt);
            mrDao.save(mr);
        } else {
            mt.setStatus(0);
            mtDao.save(mt);
            mr.setStatus(0);
            mrDao.save(mr);
        }

        //"Completed" is selected for status
        if(status == 3){
            mt.setStatus(3);
            mt.setDateComplete(date);
            mr.setStatus(3);
            mr.setDateComplete(date);
            mtDao.save(mt);
            mrDao.save(mr);

            //Create Maintenance Report
            MaintenanceReport report = new MaintenanceReport(ts, user.getStaff().getStaffId());
            report.setMainReq(mr);
            mReportDao.save(report);

            //Set maintanance status of assets back to Not Under Maintenance
            updateAssetStatus(mr, 0);
        }
    }

    /**
     * *Delete Method* - Update status of MaintenanceTask as Cancelled.
     * @param taskID - Assume taskID is a valid record
     * Completed!
     */
    public void removeTask(String taskID) {
        MaintenanceTask mt = mtDao.findByTaskID(taskID).get(0);
        MaintenanceRequest mr = mt.getMainReq();

        mt.setStatus(-1);
        mt.setMainReq(null);
        mtDao.save(mt);
        //Set status of request back to "Pending"
        mr.setStatus(0);
        mr.setMainTask(null);
        mrDao.save(mr);
    }

    /**
     * *Update Method* - Check Schedule Dates of All Tasks and Update Status to In-Progress if Scheduled Date is today
     * @param none
     * @return none
     * Completed!
     */
    public void checkAllTasks(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        for(MaintenanceTask mt:mtDao.findAll()){
            if(mt.getStatus() == 1) {
                String scheduled = df.format(mt.getDateSchedule());
                //If Date Scheduled is Today
                if (today.equals(scheduled)) {
                    mt.setStatus(2);
                    mtDao.save(mt);
                    mt.getMainReq().setStatus(2);
                    mrDao.save(mt.getMainReq());
                    updateAssetStatus(mt.getMainReq(), 1);
                }
            }
        }
    }

    /**
     * *Update Method* - Update relevant asset's maintainance status
     * @param mr - MaintenanceRequest
     * @param maintain - 0|Not Maintaining 1|Maintaining
     * @return none
     * Completed!
     */
    public void updateAssetStatus(MaintenanceRequest mr, int maintain){
        switch(maintain) {
            case 1:
                //Update maintain status of asset to 1
                if (mr.getAssetCat().equalsIgnoreCase("Normal")) {
                    Asset a = aDao.findByassetSerialID(mr.getAssetID()).get(0);
                    a.setMaintainStatus(1);
                    aDao.save(a);
                }

                //Update status of rolling stock to 3|Maintenance
                else if (mr.getAssetCat().equalsIgnoreCase("RS")) {
                    RollingStock rs = rsDao.findBySerialNo(mr.getAssetID()).get(0);

                    //When RS is assigned to a train
                    if (rs.getTrain() != null) {
                        rs.setStatus(3);
                        rs.getTrain().setStatus(2);

                        rsDao.save(rs);
                        trainDao.save(rs.getTrain());
                    } else {
                        rs.setStatus(3);
                        rsDao.save(rs);
                    }
                }
            break;
            case 0:
                //Update maintain status of asset to 0
                if (mr.getAssetCat().equalsIgnoreCase("Normal")) {
                    Asset a = aDao.findByassetSerialID(mr.getAssetID()).get(0);
                    a.setMaintainStatus(0);
                    aDao.save(a);
                }

                //Update status of rolling stock
                else if (mr.getAssetCat().equalsIgnoreCase("RS")) {
                    RollingStock rs = rsDao.findBySerialNo(mr.getAssetID()).get(0);

                    //When RS is assigned to a train
                    if (rs.getTrain() != null) {
                        rs.setStatus(2);
                        rs.getTrain().setStatus(1);

                        rsDao.save(rs);
                        trainDao.save(rs.getTrain());
                    } else {
                        rs.setStatus(1);
                        rsDao.save(rs);
                    }
                }
                break;
        }
    }

    /**
     * *Get Method* - Retrieve Maintenance Report
     * @param id - Report ID
     * @return <List<MaintenanceReport>>
     * Completed!
     */
    public List<MaintenanceReport> getMReport(String id){

        return mReportDao.findById(id);
    }

    /**
     * *Get Method* - Retrieve All Maintenance Reports
     * @param none
     * @return <List<MaintenanceReport>>
     * Completed!
     */
    public List<MaintenanceReport> getAllMReport(){

        return mReportDao.findAll();
    }

    /**
     * *Get Method* - Retrieve Maintenance Reports List By Request Location
     * @param user - SystemAccount
     * @return <List<MaintenanceReport>>
     * Completed!
     */
    public List<MaintenanceReport> getMReportList(SystemAccount user){
        List<MaintenanceReport> returnList = new ArrayList<>();

        for(MaintenanceReport r:mReportDao.findAll()){
            if(r.getMainReq().getReqLocation().equalsIgnoreCase(user.getStaff().getLocation())){
                returnList.add(r);
            }
        }

        return returnList;
    }

    /**
     * *Update Method* - Update details of Maintenance Report
     * @param id - Report ID
     * Completed!
     */
    public void updateReport(String id, String details) {
        MaintenanceReport r = getMReport(id).get(0);

        r.setDetails(details);
        mReportDao.save(r);
    }

    /**
     * *Update Method* - Approve Maintenance Report
     * @param id - Report ID
     * @param user - System Account
     * Completed!
     */
    public void approveReport(String id, SystemAccount user) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());

        MaintenanceReport r = getMReport(id).get(0);

        r.setStatus(1);
        r.setApprovalDate(ts);
        r.setApprovedBy(user.getStaff().getStaffId());
        mReportDao.save(r);
    }

    /**
     * *Get Method* - Retrieve Defect
     * @param defectID
     * @return <List<DefectReport>>
     * Completed!
     */
    public List<DefectReport> getDefect(String defectID){
        return defectDao.findByDefectId(defectID);
    }

    /**
     * *Get Method* - Retrieve Defect List
     * @param defectID
     * @return <List<DefectReport>>
     * Completed!
     */
    public List<DefectReport> getDefectList(){
        return defectDao.findAll();
    }

    /**
     * *Update Method* - Update status of defect report
     * @param defectID
     * Completed!
     */
    public void updateDefect(String defectID, int status) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());

        DefectReport d = getDefect(defectID).get(0);

        d.setStatus(status);
        d.setDateRectified(ts);
        defectDao.save(d);
    }

    /**
     * *Delete Method* - Remove defect report
     * @param defectID
     * Completed!
     */
    public void removeDefect(String defectID) {
        DefectReport d = getDefect(defectID).get(0);

        d.setStatus(-1);
        defectDao.save(d);
    }
}
