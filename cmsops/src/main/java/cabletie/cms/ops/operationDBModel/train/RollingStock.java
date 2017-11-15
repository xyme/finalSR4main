package cabletie.cms.ops.operationDBModel.train;

import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class RollingStock implements Comparable<RollingStock>{
    @Id
    private String serialNo;
    private String type;
    private Timestamp dateCreated;
    private Timestamp dateRemoved;
    private Integer milleage;
    private Timestamp lastMilleageUpdate;
    //1-First, 2-Second, 3-Third, 4-Fourth, 5-Last
    private Integer position;
    //-1|Decommissioned 0|Non-Operational 1|Operational 2|Assigned 3|Maintenance
    private Integer status;
    @ManyToOne
    @JoinColumn(name="Depot_depotID")
    private Depot parkDepot;
    @ManyToOne
    @JoinColumn(name="Train_trainID")
    private Train train;

    public RollingStock() {
    }

    public RollingStock(String serialNo, String type, Timestamp dateCreated) {
        this.serialNo = serialNo;
        this.type = type;
        this.dateCreated = dateCreated;
        this.position = 0;
        this.milleage = 0;
        this.status = 0;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Timestamp dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    public Timestamp getLastMilleageUpdate() {
        return lastMilleageUpdate;
    }

    public void setLastMilleageUpdate(Timestamp lastMilleageUpdate) {
        this.lastMilleageUpdate = lastMilleageUpdate;
    }

    public Integer getMilleage() {
        return milleage;
    }

    public void setMilleage(Integer milleage) {
        this.milleage = milleage;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Depot getParkDepot() {
        return parkDepot;
    }

    public void setParkDepot(Depot parkDepot) {
        this.parkDepot = parkDepot;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public int compareTo(RollingStock compareRS) {
        int comparepos=((RollingStock)compareRS).getPosition();

        //For Ascending order
        return this.position-comparepos;
    }
}
