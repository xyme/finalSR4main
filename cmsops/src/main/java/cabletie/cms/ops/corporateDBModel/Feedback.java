package cabletie.cms.ops.corporateDBModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	String num;
	String email;
	String name;
	String feedback;
	
	public Feedback() {
		
	}
	
	public Feedback(String email, String name, String feedback){
		this.num = String.valueOf(System.nanoTime());
		this.email = email;
		this.name = name;
		this.feedback = feedback;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	public String getFeedback() {
		return feedback;
	}
	
	
	
}
