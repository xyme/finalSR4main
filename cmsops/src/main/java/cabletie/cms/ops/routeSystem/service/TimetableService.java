package cabletie.cms.ops.routeSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.routeTimeDAO.TimeTableSetDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW5wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW5wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW6wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW6wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW8wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.EW.EW8wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS5wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS5wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS6wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS6wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS8wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.NS.NS8wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN5wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN5wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN6wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN6wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN8wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.SN.SN8wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE5wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE5wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE6wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE6wkendphDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE8wkdayDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.WE.WE8wkendphDAO;
import cabletie.cms.ops.operationDBModel.routeTime.TimeTableSet;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW5wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW5wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW6wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW6wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW8wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW8wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE5wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE5wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE6wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE6wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE8wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE8wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS5wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS5wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS6wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS6wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS8wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS8wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN5wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN5wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN6wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN6wkendph;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN8wkday;
import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN8wkendph;

/**
 * @author xY
 *
 */
@Service
public class TimetableService {

	//Bunch of DAOs here.
	
	////-----Weekday DAOs------/////
	
	//EW Weekday DAO
	@Autowired
	private EW5wkdayDAO ew5wkdayDAO;
	@Autowired
	private EW6wkdayDAO ew6wkdayDAO;
	@Autowired
	private EW8wkdayDAO ew8wkdayDAO;
	
	//WE Weekday DAO
	@Autowired
	private WE5wkdayDAO we5wkdayDAO;
	@Autowired
	private WE6wkdayDAO we6wkdayDAO;
	@Autowired
	private WE8wkdayDAO we8wkdayDAO;
	
	//NS Weekday DAO
	@Autowired
	private NS5wkdayDAO ns5wkdayDAO;
	@Autowired
	private NS6wkdayDAO ns6wkdayDAO;
	@Autowired
	private NS8wkdayDAO ns8wkdayDAO;
	
	//SN Weekend DAO
	@Autowired
	private SN5wkdayDAO sn5wkdayDAO;
	@Autowired
	private SN6wkdayDAO sn6wkdayDAO;
	@Autowired
	private SN8wkdayDAO sn8wkdayDAO;
	
	////-----Weekend DAOs------/////	
	
	//EW Weekday DAO
	@Autowired
	private EW5wkendphDAO ew5wkendphDAO;
	@Autowired
	private EW6wkendphDAO ew6wkendphDAO;
	@Autowired
	private EW8wkendphDAO ew8wkendphDAO;
	
	//WE Weekday DAO
	@Autowired
	private WE5wkendphDAO we5wkendphDAO;
	@Autowired
	private WE6wkendphDAO we6wkendphDAO;
	@Autowired
	private WE8wkendphDAO we8wkendphDAO;
	
	//NS Weekday DAO
	@Autowired
	private NS5wkendphDAO ns5wkendphDAO;
	@Autowired
	private NS6wkendphDAO ns6wkendphDAO;
	@Autowired
	private NS8wkendphDAO ns8wkendphDAO;
	
	//SN Weekend DAO
	@Autowired
	private SN5wkendphDAO sn5wkendphDAO;
	@Autowired
	private SN6wkendphDAO sn6wkendphDAO;
	@Autowired
	private SN8wkendphDAO sn8wkendphDAO;
	

	@Autowired
	private TimeTableSetDAO timeSetDAO;
	
	//Time table settings method //
	/**
	 * @param scheduleType
	 * @param frequency
	 * @param serviceStart
	 * @param serviceEnd
	 */
	public void setTimetableSettings(int scheduleType, int frequency, int serviceStart, int serviceEnd) {
		
		if(scheduleType == 30) {
			TimeTableSet settings = timeSetDAO.findBysetID(scheduleType).get(0);
			settings.setFrequency(frequency);
			settings.setServiceStart(serviceStart);;
			settings.setServiceEnd(serviceEnd);
			timeSetDAO.save(settings);
			
		}else {
			TimeTableSet settings = timeSetDAO.findBysetID(scheduleType).get(0);
			settings.setFrequency(frequency);
			settings.setServiceStart(serviceStart);;
			settings.setServiceEnd(serviceEnd);
			timeSetDAO.save(settings);
		}
		

		
	}
	
	//Get time table settings
	public List<TimeTableSet> getTimetableSettings() {
		
		return timeSetDAO.findAll(); //0 for weekday | 1 for weekend
		
	}
	

	
	
	//Getter method to get timetable in ready format //
	public ArrayList<ArrayList> getEWDay(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkdayset = settings.get(0);
		
		int serviceStart = wkdayset.getServiceStart();
		int serviceEnd = wkdayset.getServiceEnd();
		
		
		if(wkdayset.getFrequency()==0) { //EW-2/5
			return populateEW5wkdayTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkdayset.getFrequency()==1) { //EW-3/6
			return populateEW6wkdayTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateEW8wkdayTable(serviceStart,serviceEnd);
		}
		

	}
	
	public ArrayList<ArrayList> getWEDay(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkdayset = settings.get(0);

		int serviceStart = wkdayset.getServiceStart();
		int serviceEnd = wkdayset.getServiceEnd();
		
		if(wkdayset.getFrequency()==0) { //EW-2/5
			return populateWE5wkdayTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkdayset.getFrequency()==1) { //EW-3/6
			return populateWE6wkdayTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateWE8wkdayTable(serviceStart,serviceEnd);
		}
		
	}
	
	public ArrayList<ArrayList> getNSDay(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkdayset = settings.get(0);

		int serviceStart = wkdayset.getServiceStart();
		int serviceEnd = wkdayset.getServiceEnd();
		
		if(wkdayset.getFrequency()==0) { 
			return populateNS5wkdayTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkdayset.getFrequency()==1) { 
			return populateNS6wkdayTable(serviceStart,serviceEnd);
			
		}else {
			return populateNS8wkdayTable(serviceStart,serviceEnd);
		}
	}
	
	public ArrayList<ArrayList> getSNDay(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkdayset = settings.get(0);

		int serviceStart = wkdayset.getServiceStart();
		int serviceEnd = wkdayset.getServiceEnd();
		
		
		if(wkdayset.getFrequency()==0) { 
			return populateNS5wkdayTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkdayset.getFrequency()==1) { 
			return populateNS6wkdayTable(serviceStart,serviceEnd);
			
		}else {
			return populateNS8wkdayTable(serviceStart,serviceEnd);
		}
	}
	
	
	
	
	
	//Getter method to get timetable in ready format //
	public ArrayList<ArrayList> getEWend(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkEndset = settings.get(1);
	
		int serviceStart = wkEndset.getServiceStart();
		int serviceEnd = wkEndset.getServiceEnd();
		
		if(wkEndset.getFrequency()==0) { //EW-2/5
			return populateEW5wkendphTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkEndset.getFrequency()==1) { //EW-3/6
			return populateEW6wkendphTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateEW8wkendphTable(serviceStart,serviceEnd);
		}
		

	}
	
	public ArrayList<ArrayList> getWEend(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkEndset = settings.get(1);
	
		int serviceStart = wkEndset.getServiceStart();
		int serviceEnd = wkEndset.getServiceEnd();
		
		if(wkEndset.getFrequency()==0) { //EW-2/5
			return populateWE5wkendphTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkEndset.getFrequency()==1) { //EW-3/6
			return populateWE6wkendphTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateWE8wkendphTable(serviceStart,serviceEnd);
		}
		

	}
	
	public ArrayList<ArrayList> getNSend(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkEndset = settings.get(1);
	
		int serviceStart = wkEndset.getServiceStart();
		int serviceEnd = wkEndset.getServiceEnd();
		
		if(wkEndset.getFrequency()==0) { //EW-2/5
			return populateNS5wkendphTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkEndset.getFrequency()==1) { //EW-3/6
			return populateNS6wkendphTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateNS8wkendphTable(serviceStart,serviceEnd);
		}
		

	}

	public ArrayList<ArrayList> getSNend(){
		
		List<TimeTableSet> settings = getTimetableSettings();
		
		TimeTableSet wkEndset = settings.get(1);
	
		int serviceStart = wkEndset.getServiceStart();
		int serviceEnd = wkEndset.getServiceEnd();
		
		if(wkEndset.getFrequency()==0) { //EW-2/5
			return populateSN5wkendphTable(serviceStart,serviceEnd);
					
		}
		
		else if(wkEndset.getFrequency()==1) { //EW-3/6
			return populateSN6wkendphTable(serviceStart,serviceEnd);
			
		}else {//EW4/8
			return populateSN8wkendphTable(serviceStart,serviceEnd);
		}
		

	}
	
	
	//EW Table
	public ArrayList<ArrayList> populateEW5wkdayTable(int serviceStart, int serviceEnd) {
		
		ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
		
		List<EW5wkday> ew5timetable = ew5wkdayDAO.findAll();
		
		
		
		for(EW5wkday rt : ew5timetable) {
			ArrayList<String> row = new ArrayList<String>();
			
			int routeType = rt.getRouteType(); //default 0

			row.add(String.valueOf(rt.getRouteID())); //ID column
			
			if(rt.getRouteID()>=serviceStart){
				
			
			if(rt.getRouteID()==serviceStart) {
				routeType = 0;
			}
			if(rt.getRouteID()==serviceEnd) {
				routeType = -1;
			}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
				routeType = 3;
			}
			
			row.add(rt.getEW1());
			row.add(rt.getEW2());
			row.add(rt.getEW3());
			row.add(rt.getEW4());
			row.add(rt.getEW5());
			row.add(rt.getEW6());
			row.add(rt.getEW7());		
			row.add(rt.getEW8());
			row.add(rt.getEW9());
			row.add(rt.getEW10());
			row.add(rt.getEW11());
			row.add(rt.getEW12());
			row.add(rt.getEW13());
			row.add(rt.getEW14());
			row.add(rt.getEW15());
			row.add(rt.getEW16());
			row.add(rt.getEW17());
			row.add(rt.getEW18());
			row.add(rt.getEW19());
			row.add(rt.getEW20());
			row.add(rt.getEW21());
			row.add(rt.getEW22());
			
			
			if(routeType==-1) {
				row.add("Last Service");
			}else if(routeType==0) {
				row.add("Start Service");
			}else if(routeType==1) {
				row.add("Peak Service");
			}else if(routeType==2){
				row.add("Non-Peak Service");
			}else {
				row.add("Not-in-Use");
			}

			
			timetableEW.add(row);
			}
	}
		
		return timetableEW;
	
}
	
	//WE Table
	public ArrayList<ArrayList> populateWE5wkdayTable(int serviceStart, int serviceEnd) {
		
		ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
		
		List<WE5wkday> we5timetable = we5wkdayDAO.findAll();
		
		
		for(WE5wkday rt : we5timetable) {
			ArrayList<String> row = new ArrayList<String>();
			
			int routeType = rt.getRouteType();

			row.add(String.valueOf(rt.getRouteID())); //ID column
			
			if(rt.getRouteID()>=serviceStart){
			
			if(rt.getRouteID()==serviceStart) {
				routeType = 0;
			}
			if(rt.getRouteID()==serviceEnd) {
				routeType = -1;
			}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
				routeType = 3;
			}
			
			row.add(rt.getEW22());
			row.add(rt.getEW21());
			row.add(rt.getEW20());
			row.add(rt.getEW19());
			row.add(rt.getEW18());
			row.add(rt.getEW17());
			row.add(rt.getEW16());		
			row.add(rt.getEW15());
			row.add(rt.getEW14());
			row.add(rt.getEW13());
			row.add(rt.getEW12());
			row.add(rt.getEW11());
			row.add(rt.getEW10());
			row.add(rt.getEW9());
			row.add(rt.getEW8());
			row.add(rt.getEW7());
			row.add(rt.getEW6());
			row.add(rt.getEW5());
			row.add(rt.getEW4());
			row.add(rt.getEW3());
			row.add(rt.getEW2());
			row.add(rt.getEW1());
			
			if(routeType==-1) {
				row.add("Last Service");
			}else if(routeType==0) {
				row.add("Start Service");
			}else if(routeType==1) {
				row.add("Peak Service");
			}else if(routeType==2){
				row.add("Non-Peak Service");
			}else {
				row.add("Not-in-Use");
			}


			
			timetableWE.add(row);
			}
	}
		
		return timetableWE;
	
}
	
	//NS Table
	public ArrayList<ArrayList> populateNS5wkdayTable(int serviceStart, int serviceEnd) {
		
		ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
		
		List<NS5wkday> ns5timetable = ns5wkdayDAO.findAll();
		
		
		for(NS5wkday rt : ns5timetable) {
			ArrayList<String> row = new ArrayList<String>();
			
			int routeType = rt.getRouteType();

			row.add(String.valueOf(rt.getRouteID())); //ID column
			
			if(rt.getRouteID()>=serviceStart){
			
			
			if(rt.getRouteID()==serviceStart) {
				routeType = 0;
			}
			if(rt.getRouteID()==serviceEnd) {
				routeType = -1;
			}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
				routeType = 3;
			}
			
			row.add(rt.getNS1());
			row.add(rt.getNS2());
			row.add(rt.getNS3());
			row.add(rt.getNS4());
			row.add(rt.getNS5());
			row.add(rt.getNS6());
			row.add(rt.getNS7());		
			row.add(rt.getNS8());
			row.add(rt.getNS9());
			row.add(rt.getNS10());
			row.add(rt.getNS11());
			row.add(rt.getNS12());
			row.add(rt.getNS13());
			row.add(rt.getNS14());
			row.add(rt.getNS15());

			if(routeType==-1) {
				row.add("Last Service");
			}else if(routeType==0) {
				row.add("Start Service");
			}else if(routeType==1) {
				row.add("Peak Service");
			}else if(routeType==2){
				row.add("Non-Peak Service");
			}else {
				row.add("Not-in-Use");
			}

			
			timetableNS.add(row);
			}
	}
		
		return timetableNS;
	
}
	//SN Table
		public ArrayList<ArrayList> populateSN5wkdayTable(int serviceStart, int serviceEnd) {
			
			ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
			
			List<SN5wkday> sn5timetable = sn5wkdayDAO.findAll();
			
			
			for(SN5wkday rt : sn5timetable) {
				ArrayList<String> row = new ArrayList<String>();
				
				int routeType = rt.getRouteType();

				row.add(String.valueOf(rt.getRouteID())); //ID column
				
				if(rt.getRouteID()>=serviceStart){
				
				if(rt.getRouteID()==serviceStart) {
					routeType = 0;
				}
				if(rt.getRouteID()==serviceEnd) {
					routeType = -1;
				}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
					routeType = 3;
				}
				
				row.add(rt.getNS15());
				row.add(rt.getNS14());
				row.add(rt.getNS13());
				row.add(rt.getNS12());
				row.add(rt.getNS11());
				row.add(rt.getNS10());
				row.add(rt.getNS9());
				row.add(rt.getNS8());
				row.add(rt.getNS7());
				row.add(rt.getNS6());
				row.add(rt.getNS5());
				row.add(rt.getNS4());
				row.add(rt.getNS3());
				row.add(rt.getNS2());
				row.add(rt.getNS1());
				
				if(routeType==-1) {
					row.add("Last Service");
				}else if(routeType==0) {
					row.add("Start Service");
				}else if(routeType==1) {
					row.add("Peak Service");
				}else if(routeType==2){
					row.add("Non-Peak Service");
				}else {
					row.add("Not-in-Use");
				}


				
				timetableSN.add(row);
				}
		}
			
			return timetableSN;
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//EW Table
		public ArrayList<ArrayList> populateEW6wkdayTable(int serviceStart, int serviceEnd) {
			
			ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
			
			List<EW6wkday> ew6timetable = ew6wkdayDAO.findAll();
			
			
			for(EW6wkday rt : ew6timetable) {
				ArrayList<String> row = new ArrayList<String>();
				
				int routeType = rt.getRouteType();

				row.add(String.valueOf(rt.getRouteID())); //ID column
				
				if(rt.getRouteID()>=serviceStart){
				
				if(rt.getRouteID()==serviceStart) {
					routeType = 0;
				}
				if(rt.getRouteID()==serviceEnd) {
					routeType = -1;
				}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
					routeType = 3;
				}
				
				row.add(rt.getEW1());
				row.add(rt.getEW2());
				row.add(rt.getEW3());
				row.add(rt.getEW4());
				row.add(rt.getEW5());
				row.add(rt.getEW6());
				row.add(rt.getEW7());		
				row.add(rt.getEW8());
				row.add(rt.getEW9());
				row.add(rt.getEW10());
				row.add(rt.getEW11());
				row.add(rt.getEW12());
				row.add(rt.getEW13());
				row.add(rt.getEW14());
				row.add(rt.getEW15());
				row.add(rt.getEW16());
				row.add(rt.getEW17());
				row.add(rt.getEW18());
				row.add(rt.getEW19());
				row.add(rt.getEW20());
				row.add(rt.getEW21());
				row.add(rt.getEW22());
				
				
				if(routeType==-1) {
					if(routeType==-1) {
						row.add("Last Service");
					}else if(routeType==0) {
						row.add("Start Service");
					}else if(routeType==1) {
						row.add("Peak Service");
					}else if(routeType==2){
						row.add("Non-Peak Service");
					}else {
						row.add("Not-in-Use");
					}
				}

				timetableEW.add(row);
				}
		}
			
			return timetableEW;
		
	}
		
		//WE Table
		public ArrayList<ArrayList> populateWE6wkdayTable(int serviceStart, int serviceEnd) {
			
			ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
			
			List<WE6wkday> we6timetable = we6wkdayDAO.findAll();
			
			
			for(WE6wkday rt : we6timetable) {
				ArrayList<String> row = new ArrayList<String>();
				
				int routeType = rt.getRouteType();

				row.add(String.valueOf(rt.getRouteID())); //ID column
				
				if(rt.getRouteID()>=serviceStart){
					
				
				if(rt.getRouteID()==serviceStart) {
					routeType = 0;
				}
				if(rt.getRouteID()==serviceEnd) {
					routeType = -1;
				}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
					routeType = 3;
				}
				
				row.add(rt.getEW22());
				row.add(rt.getEW21());
				row.add(rt.getEW20());
				row.add(rt.getEW19());
				row.add(rt.getEW18());
				row.add(rt.getEW17());
				row.add(rt.getEW16());		
				row.add(rt.getEW15());
				row.add(rt.getEW14());
				row.add(rt.getEW13());
				row.add(rt.getEW12());
				row.add(rt.getEW11());
				row.add(rt.getEW10());
				row.add(rt.getEW9());
				row.add(rt.getEW8());
				row.add(rt.getEW7());
				row.add(rt.getEW6());
				row.add(rt.getEW5());
				row.add(rt.getEW4());
				row.add(rt.getEW3());
				row.add(rt.getEW2());
				row.add(rt.getEW1());
				
				if(routeType==-1) {
					row.add("Last Service");
				}else if(routeType==0) {
					row.add("Start Service");
				}else if(routeType==1) {
					row.add("Peak Service");
				}else if(routeType==2){
					row.add("Non-Peak Service");
				}else {
					row.add("Not-in-Use");
				}


				
				timetableWE.add(row);
				}
		}
			
			return timetableWE;
		
	}
		
		//NS Table
		public ArrayList<ArrayList> populateNS6wkdayTable(int serviceStart, int serviceEnd) {
			
			ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
			
			List<NS6wkday> ns6timetable = ns6wkdayDAO.findAll();
			
			
			for(NS6wkday rt : ns6timetable) {
				ArrayList<String> row = new ArrayList<String>();
				
				int routeType = rt.getRouteType();

				row.add(String.valueOf(rt.getRouteID())); //ID column
				
				if(rt.getRouteID()>=serviceStart){
				if(rt.getRouteID()==serviceStart) {
					routeType = 0;
				}
				if(rt.getRouteID()==serviceEnd) {
					routeType = -1;
				}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
					routeType = 3;
				}
				
				row.add(rt.getNS1());
				row.add(rt.getNS2());
				row.add(rt.getNS3());
				row.add(rt.getNS4());
				row.add(rt.getNS5());
				row.add(rt.getNS6());
				row.add(rt.getNS7());		
				row.add(rt.getNS8());
				row.add(rt.getNS9());
				row.add(rt.getNS10());
				row.add(rt.getNS11());
				row.add(rt.getNS12());
				row.add(rt.getNS13());
				row.add(rt.getNS14());
				row.add(rt.getNS15());

				
				if(routeType==-1) {
					row.add("Last Service");
				}else if(routeType==0) {
					row.add("Start Service");
				}else if(routeType==1) {
					row.add("Peak Service");
				}else if(routeType==2){
					row.add("Non-Peak Service");
				}else {
					row.add("Not-in-Use");
				}


				
				timetableNS.add(row);
				}
		}
			
			return timetableNS;
		
	}
		//SN Table
			public ArrayList<ArrayList> populateSN6wkdayTable(int serviceStart, int serviceEnd) {
				
				ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
				
				List<SN6wkday> sn6timetable = sn6wkdayDAO.findAll();
				
				
				for(SN6wkday rt : sn6timetable) {
					ArrayList<String> row = new ArrayList<String>();
					
					int routeType = rt.getRouteType();

					row.add(String.valueOf(rt.getRouteID())); //ID column
					
					if(rt.getRouteID()>=serviceStart){
					if(rt.getRouteID()==serviceStart) {
						routeType = 0;
					}
					if(rt.getRouteID()==serviceEnd) {
						routeType = -1;
					}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
						routeType = 3;
					}
					
					row.add(rt.getNS15());
					row.add(rt.getNS14());
					row.add(rt.getNS13());
					row.add(rt.getNS12());
					row.add(rt.getNS11());
					row.add(rt.getNS10());
					row.add(rt.getNS9());
					row.add(rt.getNS8());
					row.add(rt.getNS7());
					row.add(rt.getNS6());
					row.add(rt.getNS5());
					row.add(rt.getNS4());
					row.add(rt.getNS3());
					row.add(rt.getNS2());
					row.add(rt.getNS1());
					
					if(routeType==-1) {
						row.add("Last Service");
					}else if(routeType==0) {
						row.add("Start Service");
					}else if(routeType==1) {
						row.add("Peak Service");
					}else if(routeType==2){
						row.add("Non-Peak Service");
					}else {
						row.add("Not-in-Use");
					}


					
					timetableSN.add(row);
					}
			}
				
				return timetableSN;
			
		}
			
			
			
			
			//EW Table
			public ArrayList<ArrayList> populateEW8wkdayTable(int serviceStart, int serviceEnd) {
				
				ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
				
				List<EW8wkday> ew8timetable = ew8wkdayDAO.findAll();
				
				
				for(EW8wkday rt : ew8timetable) {
					ArrayList<String> row = new ArrayList<String>();
					
					int routeType = rt.getRouteType();

					row.add(String.valueOf(rt.getRouteID())); //ID column
					if(rt.getRouteID()>=serviceStart){
						
					
					if(rt.getRouteID()==serviceStart) {
						routeType = 0;
					}
					if(rt.getRouteID()==serviceEnd) {
						routeType = -1;
					}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
						routeType = 3;
					}
					
					row.add(rt.getEW1());
					row.add(rt.getEW2());
					row.add(rt.getEW3());
					row.add(rt.getEW4());
					row.add(rt.getEW5());
					row.add(rt.getEW6());
					row.add(rt.getEW7());		
					row.add(rt.getEW8());
					row.add(rt.getEW9());
					row.add(rt.getEW10());
					row.add(rt.getEW11());
					row.add(rt.getEW12());
					row.add(rt.getEW13());
					row.add(rt.getEW14());
					row.add(rt.getEW15());
					row.add(rt.getEW16());
					row.add(rt.getEW17());
					row.add(rt.getEW18());
					row.add(rt.getEW19());
					row.add(rt.getEW20());
					row.add(rt.getEW21());
					row.add(rt.getEW22());
					
					if(routeType==-1) {
						row.add("Last Service");
					}else if(routeType==0) {
						row.add("Start Service");
					}else if(routeType==1) {
						row.add("Peak Service");
					}else if(routeType==2){
						row.add("Non-Peak Service");
					}else {
						row.add("Not-in-Use");
					}

					timetableEW.add(row);
					}
			}
				
				return timetableEW;
			
		}
			
			//WE Table
			public ArrayList<ArrayList> populateWE8wkdayTable(int serviceStart, int serviceEnd) {
				
				ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
				
				List<WE8wkday> we8timetable = we8wkdayDAO.findAll();
				
				
				for(WE8wkday rt : we8timetable) {
					ArrayList<String> row = new ArrayList<String>();
					
					int routeType = rt.getRouteType();

					row.add(String.valueOf(rt.getRouteID())); //ID column
					
					if(rt.getRouteID()>=serviceStart){
					if(rt.getRouteID()==serviceStart) {
						routeType = 0;
					}
					if(rt.getRouteID()==serviceEnd) {
						routeType = -1;
					}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
						routeType = 3;
					}
					
					row.add(rt.getEW22());
					row.add(rt.getEW21());
					row.add(rt.getEW20());
					row.add(rt.getEW19());
					row.add(rt.getEW18());
					row.add(rt.getEW17());
					row.add(rt.getEW16());		
					row.add(rt.getEW15());
					row.add(rt.getEW14());
					row.add(rt.getEW13());
					row.add(rt.getEW12());
					row.add(rt.getEW11());
					row.add(rt.getEW10());
					row.add(rt.getEW9());
					row.add(rt.getEW8());
					row.add(rt.getEW7());
					row.add(rt.getEW6());
					row.add(rt.getEW5());
					row.add(rt.getEW4());
					row.add(rt.getEW3());
					row.add(rt.getEW2());
					row.add(rt.getEW1());
					
					if(routeType==-1) {
						row.add("Last Service");
					}else if(routeType==0) {
						row.add("Start Service");
					}else if(routeType==1) {
						row.add("Peak Service");
					}else if(routeType==2){
						row.add("Non-Peak Service");
					}else {
						row.add("Not-in-Use");
					}

					
					timetableWE.add(row);
					}
			}
				
				return timetableWE;
			
		}
			
			//NS Table
			public ArrayList<ArrayList> populateNS8wkdayTable(int serviceStart, int serviceEnd) {
				
				ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
				
				List<NS8wkday> ns8timetable = ns8wkdayDAO.findAll();
				
				
				for(NS8wkday rt : ns8timetable) {
					ArrayList<String> row = new ArrayList<String>();
					
					int routeType = rt.getRouteType();

					row.add(String.valueOf(rt.getRouteID())); //ID column
					
					if(rt.getRouteID()>=serviceStart){
					if(rt.getRouteID()==serviceStart) {
						routeType = 0;
					}
					if(rt.getRouteID()==serviceEnd) {
						routeType = -1;
					}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
						routeType = 3;
					}
					
					row.add(rt.getNS1());
					row.add(rt.getNS2());
					row.add(rt.getNS3());
					row.add(rt.getNS4());
					row.add(rt.getNS5());
					row.add(rt.getNS6());
					row.add(rt.getNS7());		
					row.add(rt.getNS8());
					row.add(rt.getNS9());
					row.add(rt.getNS10());
					row.add(rt.getNS11());
					row.add(rt.getNS12());
					row.add(rt.getNS13());
					row.add(rt.getNS14());
					row.add(rt.getNS15());

					
					if(routeType==-1) {
						row.add("Last Service");
					}else if(routeType==0) {
						row.add("Start Service");
					}else if(routeType==1) {
						row.add("Peak Service");
					}else if(routeType==2){
						row.add("Non-Peak Service");
					}else {
						row.add("Not-in-Use");
					}


					
					timetableNS.add(row);
					}
			}
				
				return timetableNS;
			
		}
			//SN Table
				public ArrayList<ArrayList> populateSN8wkdayTable(int serviceStart, int serviceEnd) {
					
					ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
					
					List<SN8wkday> sn8timetable = sn8wkdayDAO.findAll();
					
					
					for(SN8wkday rt : sn8timetable) {
						ArrayList<String> row = new ArrayList<String>();
						
						int routeType = rt.getRouteType();

						row.add(String.valueOf(rt.getRouteID())); //ID column
						
						if(rt.getRouteID()>=serviceStart){
						if(rt.getRouteID()==serviceStart) {
							routeType = 0;
						}
						if(rt.getRouteID()==serviceEnd) {
							routeType = -1;
						}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
							routeType = 3;
						}
						
						row.add(rt.getNS15());
						row.add(rt.getNS14());
						row.add(rt.getNS13());
						row.add(rt.getNS12());
						row.add(rt.getNS11());
						row.add(rt.getNS10());
						row.add(rt.getNS9());
						row.add(rt.getNS8());
						row.add(rt.getNS7());
						row.add(rt.getNS6());
						row.add(rt.getNS5());
						row.add(rt.getNS4());
						row.add(rt.getNS3());
						row.add(rt.getNS2());
						row.add(rt.getNS1());
						
						if(routeType==-1) {
							row.add("Last Service");
						}else if(routeType==0) {
							row.add("Start Service");
						}else if(routeType==1) {
							row.add("Peak Service");
						}else if(routeType==2){
							row.add("Non-Peak Service");
						}else {
							row.add("Not-in-Use");
						}


						
						timetableSN.add(row);
						}
				}
					
					return timetableSN;
				
			}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//Populate Weekend table
				
				public ArrayList<ArrayList> populateEW5wkendphTable(int serviceStart, int serviceEnd) {
					
					ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
					
					List<EW5wkendph> ew5timetable = ew5wkendphDAO.findAll();
					
					
					
					for(EW5wkendph rt : ew5timetable) {
						ArrayList<String> row = new ArrayList<String>();
						
						int routeType = rt.getRouteType(); //default 0

						row.add(String.valueOf(rt.getRouteID())); //ID column
						
						if(rt.getRouteID()>=serviceStart){
							
						if(rt.getRouteID()==serviceStart) {
							routeType = 0;
						}
						if(rt.getRouteID()==serviceEnd) {
							routeType = -1;
						}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
							routeType = 3;
						}
						
						row.add(rt.getEW1());
						row.add(rt.getEW2());
						row.add(rt.getEW3());
						row.add(rt.getEW4());
						row.add(rt.getEW5());
						row.add(rt.getEW6());
						row.add(rt.getEW7());		
						row.add(rt.getEW8());
						row.add(rt.getEW9());
						row.add(rt.getEW10());
						row.add(rt.getEW11());
						row.add(rt.getEW12());
						row.add(rt.getEW13());
						row.add(rt.getEW14());
						row.add(rt.getEW15());
						row.add(rt.getEW16());
						row.add(rt.getEW17());
						row.add(rt.getEW18());
						row.add(rt.getEW19());
						row.add(rt.getEW20());
						row.add(rt.getEW21());
						row.add(rt.getEW22());
						
						
						if(routeType==-1) {
							row.add("Last Service");
						}else if(routeType==0) {
							row.add("Start Service");
						}else if(routeType==1) {
							row.add("Peak Service");
						}else if(routeType==2){
							row.add("Non-Peak Service");
						}else {
							row.add("Not-in-Use");
						}

						
						timetableEW.add(row);
						}
				}
					
					return timetableEW;
				
			}
				
				//WE Table
				public ArrayList<ArrayList> populateWE5wkendphTable(int serviceStart, int serviceEnd) {
					
					ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
					
					List<WE5wkendph> we5timetable = we5wkendphDAO.findAll();
					
					
					for(WE5wkendph rt : we5timetable) {
						ArrayList<String> row = new ArrayList<String>();
						
						int routeType = rt.getRouteType();

						row.add(String.valueOf(rt.getRouteID())); //ID column
						
						if(rt.getRouteID()==serviceStart) {
							routeType = 0;
						}
						if(rt.getRouteID()==serviceEnd) {
							routeType = -1;
						}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
							routeType = 3;
						}
						
						row.add(rt.getEW22());
						row.add(rt.getEW21());
						row.add(rt.getEW20());
						row.add(rt.getEW19());
						row.add(rt.getEW18());
						row.add(rt.getEW17());
						row.add(rt.getEW16());		
						row.add(rt.getEW15());
						row.add(rt.getEW14());
						row.add(rt.getEW13());
						row.add(rt.getEW12());
						row.add(rt.getEW11());
						row.add(rt.getEW10());
						row.add(rt.getEW9());
						row.add(rt.getEW8());
						row.add(rt.getEW7());
						row.add(rt.getEW6());
						row.add(rt.getEW5());
						row.add(rt.getEW4());
						row.add(rt.getEW3());
						row.add(rt.getEW2());
						row.add(rt.getEW1());
						
						if(routeType==-1) {
							row.add("Last Service");
						}else if(routeType==0) {
							row.add("Start Service");
						}else if(routeType==1) {
							row.add("Peak Service");
						}else if(routeType==2){
							row.add("Non-Peak Service");
						}else {
							row.add("Not-in-Use");
						}


						
						timetableWE.add(row);
				}
					
					return timetableWE;
				
			}
				
				//NS Table
				public ArrayList<ArrayList> populateNS5wkendphTable(int serviceStart, int serviceEnd) {
					
					ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
					
					List<NS5wkendph> ns5timetable = ns5wkendphDAO.findAll();
					
					
					for(NS5wkendph rt : ns5timetable) {
						ArrayList<String> row = new ArrayList<String>();
						
						int routeType = rt.getRouteType();

						row.add(String.valueOf(rt.getRouteID())); //ID column
						
						if(rt.getRouteID()==serviceStart) {
							routeType = 0;
						}
						if(rt.getRouteID()==serviceEnd) {
							routeType = -1;
						}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
							routeType = 3;
						}
						
						row.add(rt.getNS1());
						row.add(rt.getNS2());
						row.add(rt.getNS3());
						row.add(rt.getNS4());
						row.add(rt.getNS5());
						row.add(rt.getNS6());
						row.add(rt.getNS7());		
						row.add(rt.getNS8());
						row.add(rt.getNS9());
						row.add(rt.getNS10());
						row.add(rt.getNS11());
						row.add(rt.getNS12());
						row.add(rt.getNS13());
						row.add(rt.getNS14());
						row.add(rt.getNS15());

						if(routeType==-1) {
							row.add("Last Service");
						}else if(routeType==0) {
							row.add("Start Service");
						}else if(routeType==1) {
							row.add("Peak Service");
						}else if(routeType==2){
							row.add("Non-Peak Service");
						}else {
							row.add("Not-in-Use");
						}

						
						timetableNS.add(row);
				}
					
					return timetableNS;
				
			}
				//SN Table
					public ArrayList<ArrayList> populateSN5wkendphTable(int serviceStart, int serviceEnd) {
						
						ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
						
						List<SN5wkendph> sn5timetable = sn5wkendphDAO.findAll();
						
						
						for(SN5wkendph rt : sn5timetable) {
							ArrayList<String> row = new ArrayList<String>();
							
							int routeType = rt.getRouteType();

							row.add(String.valueOf(rt.getRouteID())); //ID column
							
							if(rt.getRouteID()==serviceStart) {
								routeType = 0;
							}
							if(rt.getRouteID()==serviceEnd) {
								routeType = -1;
							}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
								routeType = 3;
							}
							
							row.add(rt.getNS15());
							row.add(rt.getNS14());
							row.add(rt.getNS13());
							row.add(rt.getNS12());
							row.add(rt.getNS11());
							row.add(rt.getNS10());
							row.add(rt.getNS9());
							row.add(rt.getNS8());
							row.add(rt.getNS7());
							row.add(rt.getNS6());
							row.add(rt.getNS5());
							row.add(rt.getNS4());
							row.add(rt.getNS3());
							row.add(rt.getNS2());
							row.add(rt.getNS1());
							
							if(routeType==-1) {
								row.add("Last Service");
							}else if(routeType==0) {
								row.add("Start Service");
							}else if(routeType==1) {
								row.add("Peak Service");
							}else if(routeType==2){
								row.add("Non-Peak Service");
							}else {
								row.add("Not-in-Use");
							}


							
							timetableSN.add(row);
					}
						
						return timetableSN;
					
				}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//EW Table
					public ArrayList<ArrayList> populateEW6wkendphTable(int serviceStart, int serviceEnd) {
						
						ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
						
						List<EW6wkendph> ew6timetable = ew6wkendphDAO.findAll();
						
						
						for(EW6wkendph rt : ew6timetable) {
							ArrayList<String> row = new ArrayList<String>();
							
							int routeType = rt.getRouteType();

							row.add(String.valueOf(rt.getRouteID())); //ID column
							
							if(rt.getRouteID()==serviceStart) {
								routeType = 0;
							}
							if(rt.getRouteID()==serviceEnd) {
								routeType = -1;
							}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
								routeType = 3;
							}
							
							row.add(rt.getEW1());
							row.add(rt.getEW2());
							row.add(rt.getEW3());
							row.add(rt.getEW4());
							row.add(rt.getEW5());
							row.add(rt.getEW6());
							row.add(rt.getEW7());		
							row.add(rt.getEW8());
							row.add(rt.getEW9());
							row.add(rt.getEW10());
							row.add(rt.getEW11());
							row.add(rt.getEW12());
							row.add(rt.getEW13());
							row.add(rt.getEW14());
							row.add(rt.getEW15());
							row.add(rt.getEW16());
							row.add(rt.getEW17());
							row.add(rt.getEW18());
							row.add(rt.getEW19());
							row.add(rt.getEW20());
							row.add(rt.getEW21());
							row.add(rt.getEW22());
							
							
							if(routeType==-1) {
								if(routeType==-1) {
									row.add("Last Service");
								}else if(routeType==0) {
									row.add("Start Service");
								}else if(routeType==1) {
									row.add("Peak Service");
								}else if(routeType==2){
									row.add("Non-Peak Service");
								}else {
									row.add("Not-in-Use");
								}
							}

							timetableEW.add(row);
					}
						
						return timetableEW;
					
				}
					
					//WE Table
					public ArrayList<ArrayList> populateWE6wkendphTable(int serviceStart, int serviceEnd) {
						
						ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
						
						List<WE6wkendph> we6timetable = we6wkendphDAO.findAll();
						
						
						for(WE6wkendph rt : we6timetable) {
							ArrayList<String> row = new ArrayList<String>();
							
							int routeType = rt.getRouteType();

							row.add(String.valueOf(rt.getRouteID())); //ID column
							
							if(rt.getRouteID()==serviceStart) {
								routeType = 0;
							}
							if(rt.getRouteID()==serviceEnd) {
								routeType = -1;
							}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
								routeType = 3;
							}
							
							row.add(rt.getEW22());
							row.add(rt.getEW21());
							row.add(rt.getEW20());
							row.add(rt.getEW19());
							row.add(rt.getEW18());
							row.add(rt.getEW17());
							row.add(rt.getEW16());		
							row.add(rt.getEW15());
							row.add(rt.getEW14());
							row.add(rt.getEW13());
							row.add(rt.getEW12());
							row.add(rt.getEW11());
							row.add(rt.getEW10());
							row.add(rt.getEW9());
							row.add(rt.getEW8());
							row.add(rt.getEW7());
							row.add(rt.getEW6());
							row.add(rt.getEW5());
							row.add(rt.getEW4());
							row.add(rt.getEW3());
							row.add(rt.getEW2());
							row.add(rt.getEW1());
							
							if(routeType==-1) {
								row.add("Last Service");
							}else if(routeType==0) {
								row.add("Start Service");
							}else if(routeType==1) {
								row.add("Peak Service");
							}else if(routeType==2){
								row.add("Non-Peak Service");
							}else {
								row.add("Not-in-Use");
							}


							
							timetableWE.add(row);
					}
						
						return timetableWE;
					
				}
					
					//NS Table
					public ArrayList<ArrayList> populateNS6wkendphTable(int serviceStart, int serviceEnd) {
						
						ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
						
						List<NS6wkendph> ns6timetable = ns6wkendphDAO.findAll();
						
						
						for(NS6wkendph rt : ns6timetable) {
							ArrayList<String> row = new ArrayList<String>();
							
							int routeType = rt.getRouteType();

							row.add(String.valueOf(rt.getRouteID())); //ID column
							
							if(rt.getRouteID()==serviceStart) {
								routeType = 0;
							}
							if(rt.getRouteID()==serviceEnd) {
								routeType = -1;
							}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
								routeType = 3;
							}
							
							row.add(rt.getNS1());
							row.add(rt.getNS2());
							row.add(rt.getNS3());
							row.add(rt.getNS4());
							row.add(rt.getNS5());
							row.add(rt.getNS6());
							row.add(rt.getNS7());		
							row.add(rt.getNS8());
							row.add(rt.getNS9());
							row.add(rt.getNS10());
							row.add(rt.getNS11());
							row.add(rt.getNS12());
							row.add(rt.getNS13());
							row.add(rt.getNS14());
							row.add(rt.getNS15());

							
							if(routeType==-1) {
								row.add("Last Service");
							}else if(routeType==0) {
								row.add("Start Service");
							}else if(routeType==1) {
								row.add("Peak Service");
							}else if(routeType==2){
								row.add("Non-Peak Service");
							}else {
								row.add("Not-in-Use");
							}


							
							timetableNS.add(row);
					}
						
						return timetableNS;
					
				}
					//SN Table
						public ArrayList<ArrayList> populateSN6wkendphTable(int serviceStart, int serviceEnd) {
							
							ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
							
							List<SN6wkendph> sn6timetable = sn6wkendphDAO.findAll();
							
							
							for(SN6wkendph rt : sn6timetable) {
								ArrayList<String> row = new ArrayList<String>();
								
								int routeType = rt.getRouteType();

								row.add(String.valueOf(rt.getRouteID())); //ID column
								
								if(rt.getRouteID()==serviceStart) {
									routeType = 0;
								}
								if(rt.getRouteID()==serviceEnd) {
									routeType = -1;
								}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
									routeType = 3;
								}
								
								row.add(rt.getNS15());
								row.add(rt.getNS14());
								row.add(rt.getNS13());
								row.add(rt.getNS12());
								row.add(rt.getNS11());
								row.add(rt.getNS10());
								row.add(rt.getNS9());
								row.add(rt.getNS8());
								row.add(rt.getNS7());
								row.add(rt.getNS6());
								row.add(rt.getNS5());
								row.add(rt.getNS4());
								row.add(rt.getNS3());
								row.add(rt.getNS2());
								row.add(rt.getNS1());
								
								if(routeType==-1) {
									row.add("Last Service");
								}else if(routeType==0) {
									row.add("Start Service");
								}else if(routeType==1) {
									row.add("Peak Service");
								}else if(routeType==2){
									row.add("Non-Peak Service");
								}else {
									row.add("Not-in-Use");
								}


								
								timetableSN.add(row);
						}
							
							return timetableSN;
						
					}
						
						
						
						
						//EW Table
						public ArrayList<ArrayList> populateEW8wkendphTable(int serviceStart, int serviceEnd) {
							
							ArrayList<ArrayList> timetableEW = new ArrayList<ArrayList>(); // timetable row
							
							List<EW8wkendph> ew8timetable = ew8wkendphDAO.findAll();
							
							
							for(EW8wkendph rt : ew8timetable) {
								ArrayList<String> row = new ArrayList<String>();
								
								int routeType = rt.getRouteType();

								row.add(String.valueOf(rt.getRouteID())); //ID column
								
								if(rt.getRouteID()==serviceStart) {
									routeType = 0;
								}
								if(rt.getRouteID()==serviceEnd) {
									routeType = -1;
								}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
									routeType = 3;
								}
								
								row.add(rt.getEW1());
								row.add(rt.getEW2());
								row.add(rt.getEW3());
								row.add(rt.getEW4());
								row.add(rt.getEW5());
								row.add(rt.getEW6());
								row.add(rt.getEW7());		
								row.add(rt.getEW8());
								row.add(rt.getEW9());
								row.add(rt.getEW10());
								row.add(rt.getEW11());
								row.add(rt.getEW12());
								row.add(rt.getEW13());
								row.add(rt.getEW14());
								row.add(rt.getEW15());
								row.add(rt.getEW16());
								row.add(rt.getEW17());
								row.add(rt.getEW18());
								row.add(rt.getEW19());
								row.add(rt.getEW20());
								row.add(rt.getEW21());
								row.add(rt.getEW22());
								
								if(routeType==-1) {
									row.add("Last Service");
								}else if(routeType==0) {
									row.add("Start Service");
								}else if(routeType==1) {
									row.add("Peak Service");
								}else if(routeType==2){
									row.add("Non-Peak Service");
								}else {
									row.add("Not-in-Use");
								}

								timetableEW.add(row);
						}
							
							return timetableEW;
						
					}
						
						//WE Table
						public ArrayList<ArrayList> populateWE8wkendphTable(int serviceStart, int serviceEnd) {
							
							ArrayList<ArrayList> timetableWE = new ArrayList<ArrayList>(); // timetable row
							
							List<WE8wkendph> we8timetable = we8wkendphDAO.findAll();
							
							
							for(WE8wkendph rt : we8timetable) {
								ArrayList<String> row = new ArrayList<String>();
								
								int routeType = rt.getRouteType();

								row.add(String.valueOf(rt.getRouteID())); //ID column
								
								if(rt.getRouteID()==serviceStart) {
									routeType = 0;
								}
								if(rt.getRouteID()==serviceEnd) {
									routeType = -1;
								}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
									routeType = 3;
								}
								
								row.add(rt.getEW22());
								row.add(rt.getEW21());
								row.add(rt.getEW20());
								row.add(rt.getEW19());
								row.add(rt.getEW18());
								row.add(rt.getEW17());
								row.add(rt.getEW16());		
								row.add(rt.getEW15());
								row.add(rt.getEW14());
								row.add(rt.getEW13());
								row.add(rt.getEW12());
								row.add(rt.getEW11());
								row.add(rt.getEW10());
								row.add(rt.getEW9());
								row.add(rt.getEW8());
								row.add(rt.getEW7());
								row.add(rt.getEW6());
								row.add(rt.getEW5());
								row.add(rt.getEW4());
								row.add(rt.getEW3());
								row.add(rt.getEW2());
								row.add(rt.getEW1());
								
								if(routeType==-1) {
									row.add("Last Service");
								}else if(routeType==0) {
									row.add("Start Service");
								}else if(routeType==1) {
									row.add("Peak Service");
								}else if(routeType==2){
									row.add("Non-Peak Service");
								}else {
									row.add("Not-in-Use");
								}

								
								timetableWE.add(row);
						}
							
							return timetableWE;
						
					}
						
						//NS Table
						public ArrayList<ArrayList> populateNS8wkendphTable(int serviceStart, int serviceEnd) {
							
							ArrayList<ArrayList> timetableNS = new ArrayList<ArrayList>(); // timetable row
							
							List<NS8wkendph> ns8timetable = ns8wkendphDAO.findAll();
							
							
							for(NS8wkendph rt : ns8timetable) {
								ArrayList<String> row = new ArrayList<String>();
								
								int routeType = rt.getRouteType();

								row.add(String.valueOf(rt.getRouteID())); //ID column
								
								if(rt.getRouteID()==serviceStart) {
									routeType = 0;
								}
								if(rt.getRouteID()==serviceEnd) {
									routeType = -1;
								}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
									routeType = 3;
								}
								
								row.add(rt.getNS1());
								row.add(rt.getNS2());
								row.add(rt.getNS3());
								row.add(rt.getNS4());
								row.add(rt.getNS5());
								row.add(rt.getNS6());
								row.add(rt.getNS7());		
								row.add(rt.getNS8());
								row.add(rt.getNS9());
								row.add(rt.getNS10());
								row.add(rt.getNS11());
								row.add(rt.getNS12());
								row.add(rt.getNS13());
								row.add(rt.getNS14());
								row.add(rt.getNS15());

								
								if(routeType==-1) {
									row.add("Last Service");
								}else if(routeType==0) {
									row.add("Start Service");
								}else if(routeType==1) {
									row.add("Peak Service");
								}else if(routeType==2){
									row.add("Non-Peak Service");
								}else {
									row.add("Not-in-Use");
								}


								
								timetableNS.add(row);
						}
							
							return timetableNS;
						
					}
						//SN Table
							public ArrayList<ArrayList> populateSN8wkendphTable(int serviceStart, int serviceEnd) {
								
								ArrayList<ArrayList> timetableSN = new ArrayList<ArrayList>(); // timetable row
								
								List<SN8wkendph> sn8timetable = sn8wkendphDAO.findAll();
								
								
								for(SN8wkendph rt : sn8timetable) {
									ArrayList<String> row = new ArrayList<String>();
									
									int routeType = rt.getRouteType();

									row.add(String.valueOf(rt.getRouteID())); //ID column
									
									if(rt.getRouteID()==serviceStart) {
										routeType = 0;
									}
									if(rt.getRouteID()==serviceEnd) {
										routeType = -1;
									}if(rt.getRouteID() < serviceStart || rt.getRouteID() > serviceEnd) {
										routeType = 3;
									}
									
									row.add(rt.getNS15());
									row.add(rt.getNS14());
									row.add(rt.getNS13());
									row.add(rt.getNS12());
									row.add(rt.getNS11());
									row.add(rt.getNS10());
									row.add(rt.getNS9());
									row.add(rt.getNS8());
									row.add(rt.getNS7());
									row.add(rt.getNS6());
									row.add(rt.getNS5());
									row.add(rt.getNS4());
									row.add(rt.getNS3());
									row.add(rt.getNS2());
									row.add(rt.getNS1());
									
									if(routeType==-1) {
										row.add("Last Service");
									}else if(routeType==0) {
										row.add("Start Service");
									}else if(routeType==1) {
										row.add("Peak Service");
									}else if(routeType==2){
										row.add("Non-Peak Service");
									}else {
										row.add("Not-in-Use");
									}


									
									timetableSN.add(row);
							}
								
								return timetableSN;
							
						}
}
