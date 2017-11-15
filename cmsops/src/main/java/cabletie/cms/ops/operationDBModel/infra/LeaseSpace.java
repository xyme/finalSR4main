package cabletie.cms.ops.operationDBModel.infra;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class LeaseSpace {

	@Id
	private String leaseSpaceID;
	private int size;
	private String description;
	private int value;
	private int status;

	public LeaseSpace() {
	}

	public LeaseSpace(int size, String description, int value, int status) {
		this.size = size;
		this.description = description;
		this.value = value;
		this.status = status;
	}

	public String getLeaseSpaceID() {
		return leaseSpaceID;
	}
	public void setLeaseSpaceID(String leaseSpaceID) {
		this.leaseSpaceID = leaseSpaceID;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
