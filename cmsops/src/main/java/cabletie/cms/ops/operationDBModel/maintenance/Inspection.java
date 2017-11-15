package cabletie.cms.ops.operationDBModel.maintenance;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
public class Inspection {
    private String inspectId;
    private Date dateScheduled;
    private Timestamp dateCompleted;
    //Pre-Routine | Post-Routine | Ad-hoc
    private String type;
    private String description;
    private String location;
    private String staffTeam;
    private String remarks;
    //-1|Cancelled 0|Pending 1|In-Progress 2|Completed
    private Integer status;
    private String assetCat;
    private String assetId;
    private InspectionReport inspectReport;

    public Inspection() {
    }

    public Inspection(String inspectId, Date dateScheduled, String type, String description, String location, String staffTeam, String assetCat, String assetId) {
        this.inspectId = inspectId;
        this.dateScheduled = dateScheduled;
        this.type = type;
        this.description = description;
        this.location = location;
        this.staffTeam = staffTeam;
        this.assetCat = assetCat;
        this.assetId = assetId;
        this.status = 0;
    }

    @Id
    @Column(name = "inspectID")
    public String getInspectId() {
        return inspectId;
    }

    public void setInspectId(String inspectId) {
        this.inspectId = inspectId;
    }

    @Basic
    @Column(name = "dateScheduled")
    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    @Basic
    @Column(name = "dateCompleted")
    public Timestamp getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Timestamp dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "staffTeam")
    public String getStaffTeam() {
        return staffTeam;
    }

    public void setStaffTeam(String staffTeam) {
        this.staffTeam = staffTeam;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "assetCat")
    public String getAssetCat() {
        return assetCat;
    }

    public void setAssetCat(String assetCat) {
        this.assetCat = assetCat;
    }

    @Basic
    @Column(name = "assetID")
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @OneToOne(mappedBy="inspect")
    public InspectionReport getInspectReport() {
        return inspectReport;
    }

    public void setInspectReport(InspectionReport inspectReport) {
        this.inspectReport = inspectReport;
    }
}
