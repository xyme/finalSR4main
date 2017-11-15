package cabletie.cms.ops.operationDBModel.train;

import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Train {
    private String trainId;
    private String type;
    private Timestamp dateCreated;
    private Timestamp dateRemoved;
    //-1|Decommissioned 0|Non-Operational 1|Operational 2|Maintenance
    private Integer status;
    private Depot parkDepot;
    private List<RollingStock> rolling;

    public Train() {
    }

    public Train(String trainId, String type, Timestamp dateCreated) {
        this.trainId = trainId;
        this.type = type;
        this.dateCreated = dateCreated;
        this.status = 0;
    }

    @Id
    @Column(name = "trainID")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "dateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "dateRemoved")
    public Timestamp getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Timestamp dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name="Depot_depotID")
    public Depot getParkDepot() {
        return parkDepot;
    }

    public void setParkDepot(Depot parkDepot) {
        this.parkDepot = parkDepot;
    }

    @OneToMany(mappedBy="train", fetch=FetchType.EAGER)
    public List<RollingStock> getRolling() {
        return rolling;
    }

    public void setRolling(List<RollingStock> rolling) {
        this.rolling = rolling;
    }
}
