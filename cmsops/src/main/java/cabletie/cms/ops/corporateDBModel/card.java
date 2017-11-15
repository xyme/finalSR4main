package cabletie.cms.ops.corporateDBModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class card {
	@Id
	String cardnum;
	String amount;
	String email;
	String expiry;
	
	public card() {
		
	}
	
	public card(String cardnum, String amount, String email, String expiry) {
		this.cardnum = cardnum;
		this.amount = amount;
		this.email = email;
		this.expiry = expiry;
	}
	
	public String getExpiry() {
		return expiry;
	}
	public String getCardnum() {
		return cardnum;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public String getEmail() {
		return email;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public void setAmount(String amt) {
		this.amount = amt;
		
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
