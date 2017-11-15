package cabletie.cms.ops.corporateDBModel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AccountRole")
public class AccountRole {
    @Id
    private String roleID;
    private String roleName;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany(mappedBy = "roles")
    public List<SystemAccount> sys;

    public List<SystemAccount> getSys() {
        return sys;
    }

    public void setSys(List<SystemAccount> sys) {
        this.sys = sys;
    }

    public AccountRole() {
    }

    public AccountRole(String roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.status = "Active";
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
