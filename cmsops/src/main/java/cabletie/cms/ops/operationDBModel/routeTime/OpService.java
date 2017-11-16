package cabletie.cms.ops.operationDBModel.routeTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OpService {

	@Id
	private String lineID; // EW:NS
	private String status; // Whatever
	private String fault;
	
	
	public OpService() {
		
		
	}


	public String getLineID() {
		return lineID;
	}


	public void setLineID(String lineID) {
		this.lineID = lineID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFault() {
		return fault;
	}


	public void setFault(String fault) {
		this.fault = fault;
	}
	
	
	
}
