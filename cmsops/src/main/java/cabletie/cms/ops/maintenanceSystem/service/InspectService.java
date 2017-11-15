package cabletie.cms.ops.maintenanceSystem.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.StaffTeamDAO;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.StaffTeam;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.assetDAO.AssetDAO;
import cabletie.cms.ops.operationDBDao.maintenance.InspectionDAO;
import cabletie.cms.ops.operationDBDao.maintenance.InspectionReportDAO;
import cabletie.cms.ops.operationDBDao.train.RollingStockDAO;
import cabletie.cms.ops.operationDBModel.assets.Asset;
import cabletie.cms.ops.operationDBModel.maintenance.Inspection;
import cabletie.cms.ops.operationDBModel.maintenance.InspectionReport;
import cabletie.cms.ops.operationDBModel.train.RollingStock;
import org.hibernate.procedure.ParameterStrategyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InspectService {
    @Autowired
    AssetDAO aDao;
    @Autowired
    RollingStockDAO rsDao;
    @Autowired
    StaffDAO sDao;
    @Autowired
    StaffTeamDAO stDao;
    @Autowired
    InspectionDAO iDao;
    @Autowired
    InspectionReportDAO iReportDao;

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
    public List<Asset> getAssetListBylocation(String infraID){
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
     * *Get Method* - Retrieve Inspection Record
     * @param String inspectId
     * @return <List<Inspection>>
     * Completed!
     */
    public List<Inspection> getInspect(String inspectID){
        return iDao.findByInspectId(inspectID);
    }

    /**
     * *Get Method* - Retrieve Inspection List
     * @param loc - Location
     * @return <List<Inspection>>
     * Completed!
     */
    public List<Inspection> getInspectByLocation(String loc){
        return iDao.findByLocation(loc);
    }

    /**
     * *Get Method* - Retrieve Inspection List By Location and Staff team
     * @param loc - Location
     * @param team - Staff Team ID
     * @return <List<MaintenanceTask>>
     * Completed!
     */
    public List<Inspection> getInspectListByLocationTeam(String loc, String team){
        return iDao.findByLocationAndStaffTeam(loc, team);
    }

    /**
     * *Create Method* - Create new Inspection Record
     * @param String reqType, String reqDesc, String assetID, SystemAccount user
     * @return none
     * Completed!
     */
    public void createInspection(String type, Date dateScheduled, String desc, String assetCat, String assetID, String teamID, SystemAccount user) {
        List<Inspection> currentList = iDao.findAll();
        Inspection i;

        //Create Inspection record
        if (currentList.size() < 9) {
            i = new Inspection("I0" + (currentList.size() + 1), dateScheduled, type, desc, user.getStaff().getLocation(),
                    teamID, assetCat, assetID);
        } else {
            i = new Inspection("I" + (currentList.size() + 1), dateScheduled, type, desc, user.getStaff().getLocation(),
                    teamID, assetCat, assetID);
        }
        iDao.save(i);
    }

    /**
     * *Update Method* - Edit Inspection Details
     * @param String reqType, String reqDesc, String assetID, SystemAccount user
     * @return none
     * Completed!
     */
    public void updateInspection(String id, String desc, String dateScheduled, String remarks, String teamID, int status, SystemAccount user) throws ParseException{
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date scheduled = df.parse(dateScheduled);
        String today = df.format(Calendar.getInstance().getTime());

        Inspection i = getInspect(id).get(0);

        i.setDescription(desc);
        i.setDateScheduled(scheduled);
        i.setRemarks(remarks);

        //If Date Scheduled is Today
        if(today.equals(dateScheduled)){
            i.setStatus(1);
        } else if(scheduled.after(Calendar.getInstance().getTime()) ){
            i.setStatus(0);
        }

        //A team is selected
        if(!teamID.equals(" ")){
            i.setStaffTeam(teamID);
        } else {
            i.setStaffTeam(teamID);
        }

        iDao.save(i);

        //"Completed" is selected for status
        if(status == 2){
            i.setStatus(2);
            i.setDateCompleted(ts);
            iDao.save(i);

            //Create Maintenance Report
            InspectionReport report = new InspectionReport(ts, user.getStaff().getStaffId());
            report.setInspect(i);
            iReportDao.save(report);
        }
    }

    /**
     * *Delete Method* - Remove Inspection Record
     * @param inspectID
     * @return none
     * Completed!
     */
    public void removeInspection(String id) {
        Inspection i = getInspect(id).get(0);

        i.setStatus(-1);
        iDao.save(i);
    }

    /**
     * *Update Method* - Check Schedule Dates of All Inspection Records and Update Status to In-Progress if Scheduled Date is today
     * @param none
     * @return none
     * Completed!
     */
    public void checkAllInspections(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String today = df.format(Calendar.getInstance().getTime());

        for(Inspection i:iDao.findAll()){
            if(i.getStatus() == 0) {
                String scheduled = df.format(i.getDateScheduled());
                //If Date Scheduled is Today
                if (today.equals(scheduled)) {
                    i.setStatus(1);
                    iDao.save(i);
                }
            }
        }
    }

    /**
     * *Get Method* - Retrieve Inspection Report
     * @param id - Report ID
     * @return <List<InspectionReport>>
     * Completed!
     */
    public List<InspectionReport> getInReport(String id){

        return iReportDao.findById(id);
    }

    /**
     * *Get Method* - Retrieve All Inspection Reports
     * @param none
     * @return <List<InspectionReport>>
     * Completed!
     */
    public List<InspectionReport> getAllInReport(){

        return iReportDao.findAll();
    }

    /**
     * *Get Method* - Retrieve Inspection Reports List By Request Location
     * @param user - SystemAccount
     * @return <List<InspectionReport>>
     * Completed!
     */
    public List<InspectionReport> getInReportList(SystemAccount user){
        List<InspectionReport> returnList = new ArrayList<>();

        for(InspectionReport r:iReportDao.findAll()){
            if(r.getInspect().getLocation().equalsIgnoreCase(user.getStaff().getLocation())){
                returnList.add(r);
            }
        }

        return returnList;
    }

    /**
     * *Update Method* - Update details of Inspection Report
     * @param id - Report ID
     * Completed!
     */
    public void updateReport(String id, String details) {
        InspectionReport r = getInReport(id).get(0);

        r.setDetails(details);
        iReportDao.save(r);
    }

    /**
     * *Update Method* - Approve Inspection Report
     * @param id - Report ID
     * @param user - SystemAccount
     * Completed!
     */
    public void approveReport(String id, SystemAccount user) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts = new Timestamp(date.getTime());

        InspectionReport r = getInReport(id).get(0);

        r.setStatus(1);
        r.setApprovalDate(ts);
        r.setApprovedBy(user.getStaff().getStaffId());
        iReportDao.save(r);
    }
}
