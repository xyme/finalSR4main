package cabletie.cms.ops.operationDBModel.infra.Mall;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cabletie.cms.ops.operationDBModel.infra.AdSpace;

@Entity
public class MallAdSpace extends AdSpace {

	//One space to One company
	//private Company client; - To Implement Company Later
	@ManyToOne
	@JoinColumn(name="Mall_mallID")
	private Mall mallID;

	public MallAdSpace() {
	}

	public MallAdSpace(int size, String description, int value, int status) {
		super(size, description, value, status);
	}

	public Mall getMallID() {
		return mallID;
	}

	public void setMallID(Mall mallID) {
		this.mallID = mallID;
	}
	
	
}
