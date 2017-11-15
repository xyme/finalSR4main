package cabletie.cms.ops.corporateDBModel;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

//Define table name
@Entity
@Table(name = "SystemAccount")
public class SystemAccount{

    //primary key
    @Id
    private String userID;
    private String password;
    private String status;
    private String userGroup;
    private Timestamp dateCreated;

    @OneToOne(mappedBy="account")
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "AccountRole_has_SystemAccount", joinColumns = @JoinColumn(name = "SystemAccount_userID"), inverseJoinColumns = @JoinColumn(name = "AccountRole_roleID"))
    private List<AccountRole> roles;

    public List<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AccountRole> roles) {
        this.roles = roles;
    }

    public SystemAccount() {
    }

    public SystemAccount(String userID, String password, String status, String userGroup, Timestamp dateCreated) {
        this.userID = userID;
        this.password = password;
        this.status = status;
        this.userGroup = userGroup;
        this.dateCreated = dateCreated;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
