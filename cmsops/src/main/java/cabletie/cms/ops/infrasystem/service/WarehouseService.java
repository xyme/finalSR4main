package cabletie.cms.ops.infrasystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.infraDAO.warehouseDAO.WarehouseDAO;
import cabletie.cms.ops.operationDBModel.infra.Warehouse.Warehouse;



/**
 * @author xY
 * Service class to handle managing of Warehouse (CRUD) functionality
 */
@Service
public class WarehouseService {
	
	//Import DAOs
	@Autowired
	private WarehouseDAO warehouseDAO;
	
	//Warehouse Infrastructure Related Service Method//
	
	
	/**
	 * *Get Method* - To get one specific warehouse object as first item in a list object
	 * @param warehouseID
	 * @return Warehouse Object as first warehouse in a List<Warehouse>
	 * Completed!
	 */
	public List<Warehouse> getWarehouse(String warehouseID) {
		
		return warehouseDAO.findByWarehouseID(warehouseID);
		
	}
	
	/**
	 * *Create Method* - To create a new Warehouse (Status automatically put as Active i.e 1)
	 * @param warehouseID
	 * @param name
	 * @param location
	 * Completed!
	 * 
	 */
	public boolean createWarehouse(String warehouseID, String name, String location) {
		
		//Check if assets already exist? - return false if not added
				if(!warehouseDAO.findByWarehouseID(warehouseID).isEmpty()) {
					return false;
					
				}else {
						
					Warehouse warehouse = new Warehouse(warehouseID, name, location, 1);
				
					warehouseDAO.save(warehouse);
					return true;
				
				}
				
		
		
		
	}


	
	/**
	 * *Read Method* - To get status of the Warehouse || Also check if Warehouse exist before fetching Item
	 * @param warehouseID
	 * @return 0:Not found | -1: Closed | 1:Found (Active)
	 */
	public int checkWarehouse(String warehouseID) {
		
		//Warehouse is not found
		if(getWarehouse(warehouseID).isEmpty()) {
			
			return 0;
			
		}//Warehouse is Closed
		else if(getWarehouse(warehouseID).get(0).getStatus() == -1) {
			
			return -1;
		}//Warehouse is Active

			return 1;
	}

	/**
	 * *Update Method* - To update details of an existing Warehouse
	 * @param warehouse - Fetch the whole warehouse entry from the controller!
	 * Completed!
	 */
	public void updateWarehouse(Warehouse warehouse) {
		
		warehouseDAO.save(warehouse);
		
	}
	

	/**
	 * *Delete Method* - To mark a warehouse as closed.
	 * @param warehouseID - Assume Warehouse ID is a valid record
	 * Completed!
	 */
	
	public void removeWarehouse(String warehouseID) {
		
		//Do an extra safety check on Warehouse exist before consuming
		if(checkWarehouse(warehouseID)==1) {
			
		Warehouse warehouse = getWarehouse(warehouseID).get(0);
		warehouse.setStatus(-1);
		warehouseDAO.save(warehouse);
		
		}
		
		
	}
	//Return a list of all depot assets for the current depot.
	public List<Warehouse> getAllWarehouse(){
      
		return warehouseDAO.findAll();
		
    }
}
