package cabletie.cms.ops.operationDBModel.routeTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RouteTrain {
	
	@Id
	private String routeTrainID; // Unique Association ID
	private String trainID;
	
	public RouteTrain() {
		
	}
	
	public RouteTrain(String routeTrainID, String trainID) {
		this.routeTrainID = routeTrainID;
		this.trainID = trainID;
	}
	
	public String getRouteTrainID() {
		return routeTrainID;
	}
	public void setRouteTrainID(String routeTrainID) {
		this.routeTrainID = routeTrainID;
	}
	public String getTrainID() {
		return trainID;
	}
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	
	
}
