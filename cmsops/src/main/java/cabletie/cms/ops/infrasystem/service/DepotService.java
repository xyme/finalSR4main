package cabletie.cms.ops.infrasystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.infraDAO.depotDAO.DepotDAO;
import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;



/**
 * @author xY
 * Service class to handle managing of Depot (CRUD) functionality
 */
@Service
public class DepotService {
	
	//Import DAOs
	@Autowired
	private DepotDAO depotDAO;
	
	//Depot Infrastructure Related Service Method//
	
	
	/**
	 * *Get Method* - To get one specific depot object as first item in a list object
	 * @param depotID
	 * @return Depot Object as first depot in a List<Depot>
	 * Completed!
	 */
	public List<Depot> getDepot(String depotID) {
		
		return depotDAO.findBydepotID(depotID);
		
	}
	
	/**
	 * *Create Method* - To create a new Depot (Status automatically put as Active i.e 1)
	 * @param depotID
	 * @param name
	 * @param location
	 * Completed!
	 * 
	 */
	public boolean createDepot(String depotID, String name, String location) {
		
		//Check if assets already exist? - return false if not added
		if(!depotDAO.findBydepotID(depotID).isEmpty()) {
			return false;
			
		}else {
				
		Depot depot = new Depot(depotID, name, location, 1);
		
		depotDAO.save(depot);
			return true;
		
		}
						
		
	}


	
	/**
	 * *Read Method* - To get status of the Depot || Also check if Depot exist before fetching Item
	 * @param depotID
	 * @return 0:Not found | -1: Closed | 1:Found (Active)
	 */
	public int checkDepot(String depotID) {
		
		//Depot is not found
		if(getDepot(depotID).isEmpty()) {
			
			return 0;
			
		}//Depot is Closed
		else if(getDepot(depotID).get(0).getStatus() == -1) {
			
			return -1;
		}//Depot is Active

			return 1;
	}

	/**
	 * *Update Method* - To update details of an existing Depot
	 * @param depot - Fetch the whole depot entry from the controller!
	 * Completed!
	 */
	public void updateDepot(Depot depot) {
		
		depotDAO.save(depot);
		
	}
	

	/**
	 * *Delete Method* - To mark a depot as closed.
	 * @param depotID - Assume Depot ID is a valid record
	 * Completed!
	 */
	
	public void removeDepot(String depotID) {
		
		//Do an extra safety check on Depot exist before consuming
		if(checkDepot(depotID)==1) {
			
		Depot depot = getDepot(depotID).get(0);
		depot.setStatus(-1);
		depotDAO.save(depot);
		
		}
		
		
	}
	//Return a list of all depot assets for the current depot.
	public List<Depot> getAllDepot(){
      
		return depotDAO.findAll();
		
    }
}
