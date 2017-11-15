package cabletie.cms.ops.operationDBModel.routeTime.routeAssign;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Route8PTable {


	@Id
	private int counter;
	private int routeID;
	
	public Route8PTable() {
		
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getSvcID() {
		return this.routeID;
	}

	public void setrouteID(int routeID) {
		this.routeID = routeID;
	}
	
}
