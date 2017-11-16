package cabletie.cms.ops.operationDBModel.reports;

import java.util.Date;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportObj {
	
	
	@Id
	private String reportID; // SYS GEN
	private String reportType;
	private String submittedBy;
	private Timestamp dateSubmitted; //SYS GEN
	private String reportingStatus;
	private String description;
	private String shiftTime;
	private String trainID;
	private String tripID;
	
	
	public ReportObj () {
		
	}
	
	public ReportObj(String reportType, String submittedBy, String reportingStatus, String description,
			String shiftTime, String trainID, String tripID) {
		
		super();
		this.reportID = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999999999+1));
		
		Date date = new Date();
		long time = date.getTime();
		this.dateSubmitted = new Timestamp(time); // Current Date and Time
		
		this.reportType = reportType;
		this.submittedBy = submittedBy;
		this.reportingStatus = reportingStatus;
		this.description = description;
		this.shiftTime = shiftTime; 
		this.trainID = trainID; 
		this.tripID = tripID;
		
		
	}


	public String getReportID() {
		return reportID;
	}


	public void setReportID(String reportID) {
		this.reportID = reportID;
	}


	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getSubmittedBy() {
		return submittedBy;
	}


	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}


	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}


	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}


	public String getReportingStatus() {
		return reportingStatus;
	}


	public void setReportingStatus(String reportingStatus) {
		this.reportingStatus = reportingStatus;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getShiftTime() {
		return shiftTime;
	}


	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}


	public String getTrainID() {
		return trainID;
	}


	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}


	public String getTripID() {
		return tripID;
	}


	public void setTripID(String tripID) {
		this.tripID = tripID;
	}
	
	
	
}
