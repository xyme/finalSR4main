package cabletie.cms.ops.corporateDBModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	private String email;
	private String password;
	private String name;
	private String age;
	private String gender;
	private String nationality;
	private String occupation;
	private String points;

	public Passenger() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPoints() {
		return points;
	}
	public Passenger(String email, String password, String name, String age, String gender, String nationality, String occupation, String points) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
		this.occupation = occupation;
		this.points = points;
	}
}
