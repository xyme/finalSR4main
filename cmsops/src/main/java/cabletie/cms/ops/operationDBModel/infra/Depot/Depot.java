package cabletie.cms.ops.operationDBModel.infra.Depot;

import cabletie.cms.ops.operationDBModel.train.RollingStock;
import cabletie.cms.ops.operationDBModel.train.Train;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Depot {
	@Id
	private String depotID;
	private String name;
	private String location;
	private int status;
	@OneToMany(mappedBy="parkDepot")
	private List<Train> trains;

	@OneToMany(mappedBy="parkDepot")
    private List<RollingStock> rollings;

	public Depot() {
		
	}

	public Depot(String depotID, String name, String location, int status) {
		this.depotID = depotID;
		this.name = name;
		this.location = location;
		this.status = status;
	}

	public String getDepotID() {
		return depotID;
	}
	public void setDepotID(String depotID) {
		this.depotID = depotID;
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
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() { return status;	}

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<RollingStock> getRollings() {
		return rollings;
	}

	public void setRollings(List<RollingStock> rollings) {
		this.rollings = rollings;
	}
}
