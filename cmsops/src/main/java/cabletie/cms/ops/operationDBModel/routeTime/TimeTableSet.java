package cabletie.cms.ops.operationDBModel.routeTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeTableSet {

	
	//Take in timetable design and enforce the current timetable
	@Id
	private int setID; // 0: Weekday 1: Weekend
	private int frequency; // 0 | 1 | 2 (5-2/3-6/4-8)
	private int serviceStart;
	private int serviceEnd;
	
	public TimeTableSet(){
		
	}
	
	public TimeTableSet (int setID, int frequency, int serviceStart, int serviceEnd) {
		
		super();
		this.setID = setID;
		this.frequency = frequency;
		this.serviceStart = serviceStart;
		this.serviceEnd = serviceEnd;
	}

	public int getSetID() {
		return setID;
	}

	public void setSetID(int setID) {
		this.setID = setID;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(int serviceStart) {
		this.serviceStart = serviceStart;
	}

	public int getServiceEnd() {
		return serviceEnd;
	}

	public void setServiceEnd(int serviceEnd) {
		this.serviceEnd = serviceEnd;
	}


	
	
	
}
