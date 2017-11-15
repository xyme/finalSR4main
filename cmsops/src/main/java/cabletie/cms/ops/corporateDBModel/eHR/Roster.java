package cabletie.cms.ops.corporateDBModel.eHR;

import cabletie.cms.ops.corporateDBModel.StaffTeam;

import javax.persistence.*;

@Entity
public class Roster {
    private long rosterId;
    private String location;
    private int month;
    private int year;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    private String day6;
    private String day7;
    private String day8;
    private String day9;
    private String day10;
    private String day11;
    private String day12;
    private String day13;
    private String day14;
    private String day15;
    private String day16;
    private String day17;
    private String day18;
    private String day19;
    private String day20;
    private String day21;
    private String day22;
    private String day23;
    private String day24;
    private String day25;
    private String day26;
    private String day27;
    private String day28;
    private String day29;
    private String day30;
    private String day31;
    private StaffTeam team;

    public Roster() {
    }

    public Roster(String location, int month, int year, StaffTeam team) {
        this.location = location;
        this.month = month;
        this.year = year;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rosterID")
    public long getRosterId() {
        return rosterId;
    }

    public void setRosterId(long rosterId) {
        this.rosterId = rosterId;
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
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "day1")
    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    @Basic
    @Column(name = "day2")
    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    @Basic
    @Column(name = "day3")
    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    @Basic
    @Column(name = "day4")
    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    @Basic
    @Column(name = "day5")
    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    @Basic
    @Column(name = "day6")
    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    @Basic
    @Column(name = "day7")
    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    @Basic
    @Column(name = "day8")
    public String getDay8() {
        return day8;
    }

    public void setDay8(String day8) {
        this.day8 = day8;
    }

    @Basic
    @Column(name = "day9")
    public String getDay9() {
        return day9;
    }

    public void setDay9(String day9) {
        this.day9 = day9;
    }

    @Basic
    @Column(name = "day10")
    public String getDay10() {
        return day10;
    }

    public void setDay10(String day10) {
        this.day10 = day10;
    }

    @Basic
    @Column(name = "day11")
    public String getDay11() {
        return day11;
    }

    public void setDay11(String day11) {
        this.day11 = day11;
    }

    @Basic
    @Column(name = "day12")
    public String getDay12() {
        return day12;
    }

    public void setDay12(String day12) {
        this.day12 = day12;
    }

    @Basic
    @Column(name = "day13")
    public String getDay13() {
        return day13;
    }

    public void setDay13(String day13) {
        this.day13 = day13;
    }

    @Basic
    @Column(name = "day14")
    public String getDay14() {
        return day14;
    }

    public void setDay14(String day14) {
        this.day14 = day14;
    }

    @Basic
    @Column(name = "day15")
    public String getDay15() {
        return day15;
    }

    public void setDay15(String day15) {
        this.day15 = day15;
    }

    @Basic
    @Column(name = "day16")
    public String getDay16() {
        return day16;
    }

    public void setDay16(String day16) {
        this.day16 = day16;
    }

    @Basic
    @Column(name = "day17")
    public String getDay17() {
        return day17;
    }

    public void setDay17(String day17) {
        this.day17 = day17;
    }

    @Basic
    @Column(name = "day18")
    public String getDay18() {
        return day18;
    }

    public void setDay18(String day18) {
        this.day18 = day18;
    }

    @Basic
    @Column(name = "day19")
    public String getDay19() {
        return day19;
    }

    public void setDay19(String day19) {
        this.day19 = day19;
    }

    @Basic
    @Column(name = "day20")
    public String getDay20() {
        return day20;
    }

    public void setDay20(String day20) {
        this.day20 = day20;
    }

    @Basic
    @Column(name = "day21")
    public String getDay21() {
        return day21;
    }

    public void setDay21(String day21) {
        this.day21 = day21;
    }

    @Basic
    @Column(name = "day22")
    public String getDay22() {
        return day22;
    }

    public void setDay22(String day22) {
        this.day22 = day22;
    }

    @Basic
    @Column(name = "day23")
    public String getDay23() {
        return day23;
    }

    public void setDay23(String day23) {
        this.day23 = day23;
    }

    @Basic
    @Column(name = "day24")
    public String getDay24() {
        return day24;
    }

    public void setDay24(String day24) {
        this.day24 = day24;
    }

    @Basic
    @Column(name = "day25")
    public String getDay25() {
        return day25;
    }

    public void setDay25(String day25) {
        this.day25 = day25;
    }

    @Basic
    @Column(name = "day26")
    public String getDay26() {
        return day26;
    }

    public void setDay26(String day26) {
        this.day26 = day26;
    }

    @Basic
    @Column(name = "day27")
    public String getDay27() {
        return day27;
    }

    public void setDay27(String day27) {
        this.day27 = day27;
    }

    @Basic
    @Column(name = "day28")
    public String getDay28() {
        return day28;
    }

    public void setDay28(String day28) {
        this.day28 = day28;
    }

    @Basic
    @Column(name = "day29")
    public String getDay29() {
        return day29;
    }

    public void setDay29(String day29) {
        this.day29 = day29;
    }

    @Basic
    @Column(name = "day30")
    public String getDay30() {
        return day30;
    }

    public void setDay30(String day30) {
        this.day30 = day30;
    }

    @Basic
    @Column(name = "day31")
    public String getDay31() {
        return day31;
    }

    public void setDay31(String day31) {
        this.day31 = day31;
    }

    @ManyToOne
    @JoinColumn(name="StaffTeam_teamID")
    public StaffTeam getTeam() {
        return team;
    }

    public void setTeam(StaffTeam team) {
        this.team = team;
    }

    public String getDay(int day){
        String value="";
        switch(day){
            case 1:
                value = day1;
                break;
            case 2:
                value = day2;
                break;
            case 3:
                value = day3;
                break;
            case 4:
                value = day4;
                break;
            case 5:
                value = day5;
                break;
            case 6:
                value = day6;
                break;
            case 7:
                value = day7;
                break;
            case 8:
                value = day8;
                break;
            case 9:
                value = day9;
                break;
            case 10:
                value = day10;
                break;
            case 11:
                value = day11;
                break;
            case 12:
                value = day12;
                break;
            case 13:
                value = day13;
                break;
            case 14:
                value = day14;
                break;
            case 15:
                value = day15;
                break;
            case 16:
                value = day16;
                break;
            case 17:
                value = day17;
                break;
            case 18:
                value = day18;
                break;
            case 19:
                value = day19;
                break;
            case 20:
                value = day20;
                break;
            case 21:
                value = day21;
                break;
            case 22:
                value = day22;
            case 23:
                value = day23;
                break;
            case 24:
                value = day24;
                break;
            case 25:
                value = day25;
                break;
            case 26:
                value = day26;
                break;
            case 27:
                value = day27;
                break;
            case 28:
                value = day28;
                break;
            case 29:
                value = day29;
                break;
            case 30:
                value = day30;
                break;
            case 31:
                value = day31;
                break;
        }
        return value;
    }
    public void setDay(int day, String value){
        switch(day){
            case 1:
                setDay1(value);
                break;
            case 2:
                setDay2(value);
                break;
            case 3:
                setDay3(value);
                break;
            case 4:
                setDay4(value);
                break;
            case 5:
                setDay5(value);
                break;
            case 6:
                setDay6(value);
                break;
            case 7:
                setDay7(value);
                break;
            case 8:
                setDay8(value);
                break;
            case 9:
                setDay9(value);
                break;
            case 10:
                setDay10(value);
                break;
            case 11:
                setDay11(value);
                break;
            case 12:
                setDay12(value);
                break;
            case 13:
                setDay13(value);
                break;
            case 14:
                setDay14(value);
                break;
            case 15:
                setDay15(value);
                break;
            case 16:
                setDay16(value);
                break;
            case 17:
                setDay17(value);
                break;
            case 18:
                setDay18(value);
                break;
            case 19:
                setDay19(value);
                break;
            case 20:
                setDay20(value);
                break;
            case 21:
                setDay21(value);
                break;
            case 22:
                setDay22(value);
            case 23:
                setDay23(value);
                break;
            case 24:
                setDay24(value);
                break;
            case 25:
                setDay25(value);
                break;
            case 26:
                setDay26(value);
                break;
            case 27:
                setDay27(value);
                break;
            case 28:
                setDay28(value);
                break;
            case 29:
                setDay29(value);
                break;
            case 30:
                setDay30(value);
                break;
            case 31:
                setDay31(value);
                break;
        }
    }
}
