package cabletie.cms.ops.infrasystem.controller;

public class Infrastructure {

	private String infraID;
	private String infraName;
	private String location;
	private String stn;
	private String prevStn;
	private String nextStn;
	private int status;
	
	public Infrastructure(String infraID, String infraName, String location, int status) {
		super();
		this.infraID = infraID;
		this.infraName = infraName;
		this.location = location;
		this.status = status;
	}
	
	//For Mall
	public Infrastructure(String infraID, String infraName, String location, String stn, int status) {
		super();
		this.infraID = infraID;
		this.infraName = infraName;
		this.location = location;
		this.stn = stn;
		this.status = status;
	}
	
	//For Station
	public Infrastructure(String infraID, String infraName, String location, String prevStn, String nextStn, int status) {
		super();
		this.infraID = infraID;
		this.infraName = infraName;
		this.location = location;
		this.prevStn = prevStn;
		this.nextStn = nextStn;
		this.status = status;
	}

	public String getInfraID() {
		return infraID;
	}

	public void setInfraID(String infraID) {
		this.infraID = infraID;
	}

	public String getInfraName() {
		return infraName;
	}

	public void setInfraName(String infraName) {
		this.infraName = infraName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStn() {
		return stn;
	}

	public void setStn(String stn) {
		this.stn = stn;
	}

	public String getPrevStn() {
		return prevStn;
	}

	public void setPrevStn(String prevStn) {
		this.prevStn = prevStn;
	}

	public String getNextStn() {
		return nextStn;
	}

	public void setNextStn(String nextStn) {
		this.nextStn = nextStn;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

