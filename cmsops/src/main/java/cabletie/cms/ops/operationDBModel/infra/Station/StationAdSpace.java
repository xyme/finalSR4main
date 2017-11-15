package cabletie.cms.ops.operationDBModel.infra.Station;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cabletie.cms.ops.operationDBModel.infra.AdSpace;

@Entity
public class StationAdSpace extends AdSpace {

	@ManyToOne
	@JoinColumn(name="Station_stationID")
	private Station stationID;

	public StationAdSpace() {
	}

	public StationAdSpace(int size, String description, int value, int status) {
		super(size, description, value, status);
	}

	public Station getStationID() {
		return stationID;
	}

	public void setStationID(Station stationID) {
		this.stationID = stationID;
	}
	
	
}
