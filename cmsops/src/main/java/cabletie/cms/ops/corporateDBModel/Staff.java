package cabletie.cms.ops.corporateDBModel;

import cabletie.cms.ops.corporateDBModel.eHR.AttendanceRecord;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Staff")
public class Staff {
    private String staffId;
    private String idNo;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String contactNo;
    private String email;
    private String address;
    private String nationality;
    private String race;
    private String maritalStatus;
    private String department;
    private String position;
    private String contractStart;
    private String contractEnd;
    private Integer salary;
    private Integer yearsWorked;
    private Integer numOfPenalty;
    private String promotionEligibility;
    private String status;
    private String location;
    private SystemAccount account;
    private StaffTeam team;
    private List<AttendanceRecord> attendances;

    public Staff() {
    }

    public Staff(String location, String idNo, String name, String gender, String dateOfBirth, String contactNo, String email, String address, String nationality, String race, String maritalStatus, String department, String position, String contractStart, String contractEnd, Integer salary, String status) {
        this.idNo = idNo;
        this.location = location;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.race = race;
        this.maritalStatus = maritalStatus;
        this.department = department;
        this.position = position;
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
        this.salary = salary;
        this.yearsWorked = 0;
        this.numOfPenalty = 0;
        this.promotionEligibility = "Not-eligible";
        this.status = status;

        //initialise the random generated id

        this.staffId = String.valueOf(System.nanoTime());
    }

    @Id
    @Column(name = "staffID")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "idNo")
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "contactNo")
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "race")
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Basic
    @Column(name = "maritalStatus")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Basic
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "contractStart")
    public String getContractStart() {
        return contractStart;
    }

    public void setContractStart(String contractStart) {
        this.contractStart = contractStart;
    }

    @Basic
    @Column(name = "contractEnd")
    public String getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(String contractEnd) {
        this.contractEnd = contractEnd;
    }

    @Basic
    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "yearsWorked")
    public Integer getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(Integer yearsWorked) {
        this.yearsWorked = yearsWorked;
    }

    @Basic
    @Column(name = "numOfPenalty")
    public Integer getNumOfPenalty() {
        return numOfPenalty;
    }

    public void setNumOfPenalty(Integer numOfPenalty) {
        this.numOfPenalty = numOfPenalty;
    }

    @Basic
    @Column(name = "promotionEligibility")
    public String getPromotionEligibility() {
        return promotionEligibility;
    }

    public void setPromotionEligibility(String promotionEligibility) {
        this.promotionEligibility = promotionEligibility;
    }

    @Basic
    @Column
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name="SystemAccount_userID")
    public SystemAccount getAccount() {
        return account;
    }

    public void setAccount(SystemAccount account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name="StaffTeam_teamID")
    public StaffTeam getTeam() {
        return team;
    }

    public void setTeam(StaffTeam team) {
        this.team = team;
    }

    @OneToMany(mappedBy="staff")
    public List<AttendanceRecord> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceRecord> attendances) {
        this.attendances = attendances;
    }
}
