package cabletie.cms.ops.infrasystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.infraDAO.stationDAO.StationDAO;
import cabletie.cms.ops.operationDBModel.infra.Station.Station;



/**
 * @author xY
 * Service class to handle managing of Station (CRUD) functionality
 */
@Service
public class StationService {
	
	//Import DAOs
	@Autowired
	private StationDAO stationDAO;
	
	//Station Infrastructure Related Service Method//
	
	
	/**
	 * *Get Method* - To get one specific station object as first item in a list object
	 * @param stationID
	 * @return Station Object as first station in a List<Station>
	 * Completed!
	 */
	public List<Station> getStation(String stationID) {
		
		return stationDAO.findByStationID(stationID);
		
	}
	
	/**
	 * *Create Method* - To create a new Station (Status automatically put as Active i.e 1)
	 * @param stationID
	 * @param name
	 * @param location
	 * Completed!
	 * 
	 */
	public boolean createStation(String stationID, String name, String location, String prevStn, String nextStn) {
		
		//Check if assets already exist? - return false if not added
		if(!stationDAO.findByStationID(stationID).isEmpty()) {
			return false;
			
		}else {
				
			Station station = new Station(stationID, name, location, prevStn, nextStn,1);
		
			stationDAO.save(station);
			return true;
		
		}
		
		
		
	}


	
	/**
	 * *Read Method* - To get status of the Station || Also check if Station exist before fetching Item
	 * @param stationID
	 * @return 0:Not found | -1: Closed | 1:Found (Active)
	 */
	public int checkStation(String stationID) {
		
		//Station is not found
		if(getStation(stationID).isEmpty()) {
			
			return 0;
			
		}//Station is Closed
		else if(getStation(stationID).get(0).getStatus() == -1) {
			
			return -1;
		}//Station is Active

			return 1;
	}

	/**
	 * *Update Method* - To update details of an existing Station
	 * @param station - Fetch the whole station entry from the controller!
	 * Completed!
	 */
	public void updateStation(Station station) {
		
		stationDAO.save(station);
		
	}
	

	/**
	 * *Delete Method* - To mark a station as closed.
	 * @param stationID - Assume Station ID is a valid record
	 * Completed!
	 */
	
	public void removeStation(String stationID) {
		
		//Do an extra safety check on Station exist before consuming
		if(checkStation(stationID)==1) {
			
		Station station = getStation(stationID).get(0);
		station.setStatus(-1);
		stationDAO.save(station);
		
		}
		
		
	}
	
	//Return a list of all depot assets for the current depot.
	public List<Station> getAllStation(){
      
		return stationDAO.findAll();
		
    }
}
