package cabletie.cms.ops.operationDBModel.infra.Mall;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Mall {

	@Id
	private String mallID;
	private String name;
	private String location;
	private String stationID;

	private int status;

	public Mall() {
	}

	public Mall(String mallID, String name, String location, String stationID, int status) {
		this.mallID = mallID;
		this.name = name;
		this.location = location;
		this.stationID = stationID;
		this.status = status;
	}

	public String getMallID() {
		return mallID;
	}
	public void setMallID(String mallID) {
		this.mallID = mallID;
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
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) { this.stationID = stationID;}


	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
	
	
}
