package cabletie.cms.ops.operationDBModel.infra.Mall;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cabletie.cms.ops.operationDBModel.infra.LeaseSpace;

@Entity
public class MallLeaseSpace extends LeaseSpace {

	//One space to One company
	//private Company tenant;
	@ManyToOne
	@JoinColumn(name="Mall_mallID")
	private Mall mallID;

	public MallLeaseSpace() {
	}

	public MallLeaseSpace(int size, String description, int value, int status) {
		super(size, description, value, status);
	}

	public Mall getMallID() {
		return mallID;
	}

	public void setMallID(Mall mallID) {
		this.mallID = mallID;
	}
	
	
}
