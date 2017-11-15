package cabletie.cms.ops.operationDBModel.maintenance;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class DefectReport {
    private String defectId;
    private Timestamp dateReport;
    private Timestamp dateRectified;
    private String description;
    private String location;
    private String email;
    //-1|Deleted 0|Pending 1|In-Progress 2|Rectified
    private Integer status;

    public DefectReport() {
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DefectReport(String defectId, Timestamp dateReport, String description, String location, String email) {
        this.defectId = defectId;
        this.dateReport = dateReport;
        this.description = description;
        this.location = location;
        this.email = email;
        this.status = status;
    }

    @Id
    @Column(name = "defectID")
    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    @Basic
    @Column(name = "dateReport")
    public Timestamp getDateReport() {
        return dateReport;
    }

    public void setDateReport(Timestamp dateReport) {
        this.dateReport = dateReport;
    }

    @Basic
    @Column(name = "dateRectified")
    public Timestamp getDateRectified() {
        return dateRectified;
    }

    public void setDateRectified(Timestamp dateRectified) {
        this.dateRectified = dateRectified;
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
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
