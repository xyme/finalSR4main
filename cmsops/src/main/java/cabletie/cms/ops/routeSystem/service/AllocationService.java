package cabletie.cms.ops.routeSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.routeTimeDAO.RouteTrainDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.TimeTableSetDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route5PTableDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route5TableDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route6PTableDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route6TableDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route8PTableDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO.route8TableDAO;
import cabletie.cms.ops.operationDBDao.train.TrainDAO;
import cabletie.cms.ops.operationDBModel.routeTime.RouteTrain;
import cabletie.cms.ops.operationDBModel.train.Train;

@Service
public class AllocationService {

	
	//Bunch of DAOs
	@Autowired
	private route5TableDAO r5tDAO;
	@Autowired
	private TimeTableSetDAO ttsetDAO;
	
	//Bunch of DAOs
	@Autowired
	private route6TableDAO r6tDAO;

	
	//Bunch of DAOs
	@Autowired
	private route8TableDAO r8tDAO;
	
	@Autowired
	private route5PTableDAO r5ptDAO;

	
	//Bunch of DAOs
	@Autowired
	private route6PTableDAO r6ptDAO;

	
	//Bunch of DAOs
	@Autowired
	private route8PTableDAO r8ptDAO;
	
	@Autowired
	private TimetableService timetableSvc;
	
	@Autowired
	private RouteTrainDAO routeTrainDAO;

	@Autowired
	private TrainDAO trainDAO;
	

	
	
	public void getRoutes(int counter) {
		
		r5tDAO.findBycounter(counter);
	}
	
	public ArrayList<ArrayList> generateTable5() {
		
		
		int startService = ttsetDAO.findBysetID(30).get(0).getServiceStart();
		int endService = ttsetDAO.findBysetID(30).get(0).getServiceEnd();
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		
		//List<Route5Table> r5t = r5tDAO.findAll(); //get DB table
		
		for(int j = 0; j<10 ; j++) {//do 10 times
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			startService++;
					
			for(int i=startService; i<endService; i+=10) {
				route.add(r5tDAO.findBycounter(i).get(0).getSvcID());
			}
			
			routeList.add(route);
			
		}
		
		return routeList;
	
		
	}
	
	
	public ArrayList<ArrayList> generateTable6() {
		
		
		int startService = ttsetDAO.findBysetID(30).get(0).getServiceStart();
		int endService = ttsetDAO.findBysetID(30).get(0).getServiceEnd();
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		
		//List<Route5Table> r5t = r5tDAO.findAll(); //get DB table
		
		for(int j = 0; j<8 ; j++) {//do 10 times
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			startService++;
					
			for(int i=startService; i<endService; i+=8) {
				route.add(r6tDAO.findBycounter(i).get(0).getSvcID());
			}
			
			routeList.add(route);
			
		}
		
		return routeList;
	
		
	}
	
	
	public ArrayList<ArrayList> generateTable8() {
		
		
		int startService = ttsetDAO.findBysetID(30).get(0).getServiceStart();
		int endService = ttsetDAO.findBysetID(30).get(0).getServiceEnd();
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		
		//List<Route5Table> r5t = r5tDAO.findAll(); //get DB table
		
		for(int j = 0; j<6 ; j++) {//do 6 times
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			startService++;
					
			for(int i=startService; i<endService; i+=6) {
				route.add(r8tDAO.findBycounter(i).get(0).getSvcID());
			}
			
			routeList.add(route);
			
		}
		
		//Use to add available Trains
		//get all train
		for(int j = 0; j<6 ; j++) {
			
			List<Train> trainList = trainDAO.findBystatus(1);
			
			ArrayList<String> trainID = new ArrayList<String>();
			
			trainID.add(trainList.get(j).getTrainId());
			
			routeList.add(trainID);
		}
		
		return routeList;
	
		
	}
	
	
	public ArrayList<ArrayList> generateTable5P() {
		
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		

		//Morning Peak Assignment
		for(int j = 0; j<15 ; j++) {
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			
			for(int i=j; i<r5ptDAO.findAll().size(); i+=15) {
				route.add(r5ptDAO.findBycounter(i).get(0).getSvcID());
			}
			
			
			routeList.add(route);
			
		}
		
		
		return routeList;
	
		
	}
	
	
	public ArrayList<ArrayList> generateTable6P() {
		
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		

		//Morning Peak Assignment
		for(int j = 0; j<8 ; j++) {
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			
			for(int i=j; i<r6ptDAO.findAll().size(); i+=8) {
				route.add(r6ptDAO.findBycounter(i).get(0).getSvcID());
			}
			
			
			routeList.add(route);
			
		}
		
		
		return routeList;
	}
	
	
	
	
	public ArrayList<ArrayList> generateTable8P() {
		
		
		ArrayList<ArrayList> routeList = new ArrayList<ArrayList>();
		

		//Morning Peak Assignment
		for(int j = 0; j<6 ; j++) {
			
			ArrayList<Integer> route = new ArrayList<Integer>();
			
			
			for(int i=j; i<r8ptDAO.findAll().size(); i+=6) {
				route.add(r8ptDAO.findBycounter(i).get(0).getSvcID());
			}
			
			
			routeList.add(route);
			
			
		}
		
		//Use to add available Trains
		//get all train
		for(int j = 0; j<6 ; j++) {
			
			List<Train> trainList = trainDAO.findBystatus(1);
			
			ArrayList<String> trainID = new ArrayList<String>();
			
			trainID.add(trainList.get(j+6).getTrainId());
			
			routeList.add(trainID);
		}
		
		
		return routeList;
	}
	
	
	
	public List<RouteTrain> getTrainAssignment() {
		
		return routeTrainDAO.findAll();
		
	}
	
	public void autoTrainRoute() {
		
		//Flush
		routeTrainDAO.deleteAll();
		
		
		//Allocate new Trains
		for(int j = 0; j<6 ; j++) {
			
			List<Train> trainList = trainDAO.findBystatus(1);
			
			RouteTrain newRouteTrain = new RouteTrain(String.valueOf(j+1),trainList.get(j).getTrainId());
			
			routeTrainDAO.save(newRouteTrain);
			
		}	
	}
	
	public void editTrainRoute(String routeTrainID, String trainID) {
		
		//Edit Train
		RouteTrain oldRoute = routeTrainDAO.findByrouteTrainID(routeTrainID).get(0);
		
		oldRoute.setTrainID(trainID);
		
		routeTrainDAO.save(oldRoute);
		
	}
		
	
}
