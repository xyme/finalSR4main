package cabletie.cms.ops.corporateDBModel.eHR;

import cabletie.cms.ops.corporateDBModel.Staff;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class AttendanceRecord {
    private long id;
    private Date date;
    private String shift;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private Staff staff;

    public AttendanceRecord() {
    }

    public AttendanceRecord(Date date, String shift, Timestamp checkInTime, Staff staff) {
        this.date = date;
        this.shift = shift;
        this.checkInTime = checkInTime;
        this.staff = staff;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "shift")
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Basic
    @Column(name = "checkInTime")
    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    @Basic
    @Column(name = "checkOutTime")
    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    @ManyToOne
    @JoinColumn(name="Staff_staffID")
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
