package cabletie.cms.ops.operationDBModel.maintenance;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MaintenanceTask {

    @Id
    private String taskID;
    private String staffTeamID;
    private Date dateSchedule;
    private Date dateComplete;
    private String description;
    private String location;
    private String remarks;
    private double cost;
    //-1|Cancelled, 0|Pending, 1|Assigned 2|In-Progress 3|Completed
    private int status;

    @OneToOne
    @JoinColumn(name="MaintenanceRequest_reqID")
    private MaintenanceRequest mainReq;

    public MaintenanceTask() {
    }

    public MaintenanceTask(String taskID, String description, String location, double cost) {
        this.taskID = taskID;
        this.description = description;
        this.location = location;
        this.cost = cost;
        this.status = 0;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getStaffTeamID() {
        return staffTeamID;
    }

    public void setStaffTeamID(String staffTeamID) {
        this.staffTeamID = staffTeamID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MaintenanceRequest getMainReq() {
        return mainReq;
    }

    public void setMainReq(MaintenanceRequest mainReq) {
        this.mainReq = mainReq;
    }
}
