package cabletie.cms.ops.operationDBModel.infra.Warehouse;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Warehouse {
	
	@Id
	private String warehouseID;
	private String name;
	private String location;
	private int status;


	public Warehouse() {
	}

	public Warehouse(String warehouseID, String name, String location, int status) {
		this.warehouseID = warehouseID;
		this.name = name;
		this.location = location;
		this.status = status;
	}

	public String getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(String warehouseID) {
		this.warehouseID = warehouseID;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}


}
