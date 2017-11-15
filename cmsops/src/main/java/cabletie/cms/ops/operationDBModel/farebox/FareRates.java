package cabletie.cms.ops.operationDBModel.farebox;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FareRates {
	
	@Id
	private int fareID; //0 | 1 | 2 | 3 | 4 | 5 | 6 | 7
	private String fareType; //Adult,Child,Senior Citizen,Cash
	//0.77,0.37,0.54,2.50 (Unlimited Distance) - Fixed
	//0.85,0.37,0.54,2.50 (Unlimited Distance) - Fixed
	
	
	//Number of Stops away
	//0-1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22
	//+0.05 in each step (Constant) - Fixed for now
	
	private double stationFare1;
	private double stationFare2;
	private double stationFare3;
	private double stationFare4;
	private double stationFare5;
	private double stationFare6;
	private double stationFare7;
	private double stationFare8;
	private double stationFare9;
	private double stationFare10;
	private double stationFare11;
	private double stationFare12;
	private double stationFare13;
	private double stationFare14;
	private double stationFare15;
	private double stationFare16;
	private double stationFare17;
	private double stationFare18;
	private double stationFare19;
	private double stationFare20;
	private double stationFare21;
	private double stationFare22;


	public FareRates() {
		
		
	}


	public int getFareID() {
		return fareID;
	}


	public void setFareID(int fareID) {
		this.fareID = fareID;
	}


	public String getFareType() {
		return fareType;
	}


	public void setFareType(String fareType) {
		this.fareType = fareType;
	}


	public double getStationFare1() {
		return stationFare1;
	}


	public void setStationFare1(double stationFare1) {
		this.stationFare1 = stationFare1;
	}


	public double getStationFare2() {
		return stationFare2;
	}


	public void setStationFare2(double stationFare2) {
		this.stationFare2 = stationFare2;
	}


	public double getStationFare3() {
		return stationFare3;
	}


	public void setStationFare3(double stationFare3) {
		this.stationFare3 = stationFare3;
	}


	public double getStationFare4() {
		return stationFare4;
	}


	public void setStationFare4(double stationFare4) {
		this.stationFare4 = stationFare4;
	}


	public double getStationFare5() {
		return stationFare5;
	}


	public void setStationFare5(double stationFare5) {
		this.stationFare5 = stationFare5;
	}


	public double getStationFare6() {
		return stationFare6;
	}


	public void setStationFare6(double stationFare6) {
		this.stationFare6 = stationFare6;
	}


	public double getStationFare7() {
		return stationFare7;
	}


	public void setStationFare7(double stationFare7) {
		this.stationFare7 = stationFare7;
	}


	public double getStationFare8() {
		return stationFare8;
	}


	public void setStationFare8(double stationFare8) {
		this.stationFare8 = stationFare8;
	}


	public double getStationFare9() {
		return stationFare9;
	}


	public void setStationFare9(double stationFare9) {
		this.stationFare9 = stationFare9;
	}


	public double getStationFare10() {
		return stationFare10;
	}


	public void setStationFare10(double stationFare10) {
		this.stationFare10 = stationFare10;
	}


	public double getStationFare11() {
		return stationFare11;
	}


	public void setStationFare11(double stationFare11) {
		this.stationFare11 = stationFare11;
	}


	public double getStationFare12() {
		return stationFare12;
	}


	public void setStationFare12(double stationFare12) {
		this.stationFare12 = stationFare12;
	}


	public double getStationFare13() {
		return stationFare13;
	}


	public void setStationFare13(double stationFare13) {
		this.stationFare13 = stationFare13;
	}


	public double getStationFare14() {
		return stationFare14;
	}


	public void setStationFare14(double stationFare14) {
		this.stationFare14 = stationFare14;
	}


	public double getStationFare15() {
		return stationFare15;
	}


	public void setStationFare15(double stationFare15) {
		this.stationFare15 = stationFare15;
	}


	public double getStationFare16() {
		return stationFare16;
	}


	public void setStationFare16(double stationFare16) {
		this.stationFare16 = stationFare16;
	}


	public double getStationFare17() {
		return stationFare17;
	}


	public void setStationFare17(double stationFare17) {
		this.stationFare17 = stationFare17;
	}


	public double getStationFare18() {
		return stationFare18;
	}


	public void setStationFare18(double stationFare18) {
		this.stationFare18 = stationFare18;
	}


	public double getStationFare19() {
		return stationFare19;
	}


	public void setStationFare19(double stationFare19) {
		this.stationFare19 = stationFare19;
	}


	public double getStationFare20() {
		return stationFare20;
	}


	public void setStationFare20(double stationFare20) {
		this.stationFare20 = stationFare20;
	}


	public double getStationFare21() {
		return stationFare21;
	}


	public void setStationFare21(double stationFare21) {
		this.stationFare21 = stationFare21;
	}


	public double getStationFare22() {
		return stationFare22;
	}


	public void setStationFare22(double stationFare22) {
		this.stationFare22 = stationFare22;
	}

	
	
	
	

	
}