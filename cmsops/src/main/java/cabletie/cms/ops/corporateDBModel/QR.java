package cabletie.cms.ops.corporateDBModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qr")
public class QR {

	@Id
	String qrcode;
	String amount;
	String email;
	
	public QR() {
		
	}
	
	public QR(String QRCODE, String amount, String email) {
		this.qrcode = QRCODE;
		this.amount = amount;
		this.email = email;
	}
	
	public String getQrcode() {
		return qrcode;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public String getEmail() {
		return email;
	}
}
