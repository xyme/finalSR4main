package cabletie.cms.ops.corporateDBModel;

import cabletie.cms.ops.corporateDBModel.eHR.Roster;

import javax.persistence.*;
import java.util.List;

@Entity
public class StaffTeam {
    private String teamId;
    private String location;
    //-1|Inactive 1|Active
    private Integer status;

    private List<Staff> staffs;
    private List<Roster> roster;

    public StaffTeam() {
    }

    public StaffTeam(String teamId, String location) {
        this.teamId = teamId;
        this.location = location;
        this.status = 1;
    }

    @Id
    @Column(name = "teamID")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @OneToMany(mappedBy="team")
    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @OneToMany(mappedBy="team")
    public List<Roster> getRoster() {
        return roster;
    }

    public void setRoster(List<Roster> roster) {
        this.roster = roster;
    }
}
