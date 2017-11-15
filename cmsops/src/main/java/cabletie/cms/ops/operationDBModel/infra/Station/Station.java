package cabletie.cms.ops.operationDBModel.infra.Station;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station {
	@Id
	private String stationID;
	private String name;
	private String location;
	private String prevStn;
	private String nextStn;
	private int status;


//	private List<StationLeaseSpace> leaseSpaces;
//	@OneToMany(mappedBy="stationID")
//	private List<StationAdSpace> adSpaces;

	public Station() {
	}

	public Station(String stationID, String name, String location, String prevStn, String nextStn, int status) {
		this.stationID = stationID;
		this.name = name;
		this.location = location;
		this.prevStn = prevStn;
		this.nextStn = nextStn;
		this.status = status;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

//
//	public List<StationLeaseSpace> getLeaseSpaces() {
//		return leaseSpaces;
//	}
//
//	public void setLeaseSpaces(List<StationLeaseSpace> leaseSpaces) {
//		this.leaseSpaces = leaseSpaces;
//	}
//
//	public List<StationAdSpace> getAdSpaces() {
//		return adSpaces;
//	}
//
//	public void setAdSpaces(List<StationAdSpace> adSpaces) {
//		this.adSpaces = adSpaces;
//	}
}
