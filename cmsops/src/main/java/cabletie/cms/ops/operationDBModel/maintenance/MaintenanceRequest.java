package cabletie.cms.ops.operationDBModel.maintenance;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class MaintenanceRequest {

    @Id
    private String reqID;
    private Date reqDate;
    private Date dateSchedule;
    private Date dateComplete;
    private String reqStaff;
    private String reqLocation;
    //Ad-hoc | Urgent | Preventive | Predictive
    private String reqType;
    private String reqDescription;
    //-1|Cancelled, 0|Pending, 1|Assigned 2|In-Progress, 3|Completed
    private int status;
    private String assetCat;
    private String assetID;

    @OneToOne(mappedBy="mainReq")
    private MaintenanceTask mainTask;
    @OneToOne(mappedBy="mainReq")
    private MaintenanceReport mainReport;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(String reqID, Date reqDate, String reqStaff, String reqLocation, String reqType, String reqDescription, String assetCat, String assetID) {
        this.reqID = reqID;
        this.reqDate = reqDate;
        this.reqStaff = reqStaff;
        this.reqLocation = reqLocation;
        this.reqType = reqType;
        this.reqDescription = reqDescription;
        this.status = 0;
        this.assetCat = assetCat;
        this.assetID = assetID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getReqStaff() {
        return reqStaff;
    }

    public void setReqStaff(String reqStaff) {
        this.reqStaff = reqStaff;
    }

    public String getReqLocation() {
        return reqLocation;
    }

    public void setReqLocation(String reqLocation) {
        this.reqLocation = reqLocation;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAssetCat() {
        return assetCat;
    }

    public void setAssetCat(String assetCat) {
        this.assetCat = assetCat;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public MaintenanceTask getMainTask() {
        return mainTask;
    }

    public void setMainTask(MaintenanceTask mainTask) {
        this.mainTask = mainTask;
    }

    public MaintenanceReport getMainReport() {
        return mainReport;
    }

    public void setMainReport(MaintenanceReport mainReport) {
        this.mainReport = mainReport;
    }
}

