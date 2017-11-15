package cabletie.cms.ops.operationDBModel.maintenance;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class MaintenanceReport {
    private String id; //Auto-generated
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp approvalDate;
    private String approvedBy;
    private String details;
    //-1|Deleted 0|Pending 1|Approved
    private Integer status;
    private MaintenanceRequest mainReq;

    public MaintenanceReport() {
    }

    public MaintenanceReport(Timestamp dateCreated, String createdBy) {
        this.id = UUID.randomUUID().toString();
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.approvedBy = "";
        this.status = 0;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "approvalDate")
    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Basic
    @Column(name = "approvedBy")
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name="MaintenanceRequest_reqID")
    public MaintenanceRequest getMainReq() {
        return mainReq;
    }

    public void setMainReq(MaintenanceRequest mainReq) {
        this.mainReq = mainReq;
    }
}
