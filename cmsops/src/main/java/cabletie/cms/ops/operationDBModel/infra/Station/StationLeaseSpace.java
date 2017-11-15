package cabletie.cms.ops.operationDBModel.infra.Station;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cabletie.cms.ops.operationDBModel.infra.LeaseSpace;

@Entity
public class StationLeaseSpace extends LeaseSpace {

	@ManyToOne
	@JoinColumn(name="Station_stationID")
	private Station stationID;

	public StationLeaseSpace() {
	}

	public StationLeaseSpace(int size, String description, int value, int status) {
		super(size, description, value, status);
	}

	public Station getStationID() {
		return stationID;
	}

	public void setStationID(Station stationID) {
		this.stationID = stationID;
	}
	
	
}
