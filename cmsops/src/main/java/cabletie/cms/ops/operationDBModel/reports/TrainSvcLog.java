package cabletie.cms.ops.operationDBModel.reports;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrainSvcLog {

	@Id
	private String logID;
	private String trainID;
	private String driverID;
	private Timestamp startTripTime;
	private Timestamp endTripTime;
	
	public TrainSvcLog() {
	
	}
	
	public TrainSvcLog(String trainID, String driverID, Timestamp startTripTime) {
		
		
		super();
		this.logID = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999999999+1));
		this.trainID = trainID;
		this.driverID = driverID;
		this.startTripTime = startTripTime;
		
		
	}
	
	
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getTrainID() {
		return trainID;
	}
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public Timestamp getStartTripTime() {
		return startTripTime;
	}
	public void setStartTripTime(Timestamp startTripTime) {
		this.startTripTime = startTripTime;
	}
	public Timestamp getEndTripTime() {
		return endTripTime;
	}
	public void setEndTripTime(Timestamp endTripTime) {
		this.endTripTime = endTripTime;
	}
	
	
	
}
