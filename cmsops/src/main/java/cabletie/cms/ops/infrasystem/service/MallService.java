package cabletie.cms.ops.infrasystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.infraDAO.mallDAO.MallDAO;
import cabletie.cms.ops.operationDBModel.infra.Mall.Mall;



/**
 * @author xY
 * Service class to handle managing of Mall (CRUD) functionality
 */
@Service
public class MallService {
	
	//Import DAOs
	@Autowired
	private MallDAO mallDAO;

	
	//Mall Infrastructure Related Service Method//
	
	
	/**
	 * *Get Method* - To get one specific mall object as first item in a list object
	 * @param mallID
	 * @return Mall Object as first mall in a List<Mall>
	 * Completed!
	 */
	public List<Mall> getMall(String mallID) {
		
		return mallDAO.findByMallID(mallID);
		
	}
	
	/**
	 * *Create Method* - To create a new Mall (Status automatically put as Active i.e 1)
	 * @param mallID
	 * @param name
	 * @param location
	 * Completed!
	 * 
	 */
	public boolean createMall(String mallID, String name, String stationID, String location) {
		
		
		//Check if assets already exist? - return false if not added
		if(!mallDAO.findByMallID(mallID).isEmpty()) {
			return false;
			
		}else {
				
			Mall mall = new Mall(mallID, name, location, stationID, 1);
		
			mallDAO.save(mall);
			return true;
		
		}
		
		
		
		
	}


	
	/**
	 * *Read Method* - To get status of the Mall || Also check if Mall exist before fetching Item
	 * @param mallID
	 * @return 0:Not found | -1: Closed | 1:Found (Active)
	 */
	public int checkMall(String mallID) {
		
		//Mall is not found
		if(getMall(mallID).isEmpty()) {
			
			return 0;
			
		}//Mall is Closed
		else if(getMall(mallID).get(0).getStatus() == -1) {
			
			return -1;
		}//Mall is Active

			return 1;
	}

	/**
	 * *Update Method* - To update details of an existing Mall
	 * @param mall - Fetch the whole mall entry from the controller!
	 * Completed!
	 */
	public void updateMall(Mall mall) {
		
		mallDAO.save(mall);
		
	}
	

	/**
	 * *Delete Method* - To mark a mall as closed.
	 * @param mallID - Assume Mall ID is a valid record
	 * Completed!
	 */
	
	public void removeMall(String mallID) {
		
		//Do an extra safety check on Mall exist before consuming
		if(checkMall(mallID)==1) {
			
		Mall mall = getMall(mallID).get(0);
		mall.setStatus(-1);
		mallDAO.save(mall);
		
		}
		
		
	}
	//Return a list of all depot assets for the current depot.
	public List<Mall> getAllMall(){
      
		return mallDAO.findAll();
		
    }
}
