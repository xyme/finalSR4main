package cabletie.cms.ops.corporateDBDao;

public class FeedbackBody {

	String email;
	String name;
	String feedback;

	public FeedbackBody() {
		
	}
	public FeedbackBody(String email, String name, String feedback){
		this.email = email;
		this.name = name;
		this.feedback = feedback;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getFeedback(){
		return feedback;
	}
}
