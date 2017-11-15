package cabletie.cms.ops.inventoryAssetSystem.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.assetDAO.AssetDAO;
import cabletie.cms.ops.operationDBDao.assetDAO.AssetLogDAO;
import cabletie.cms.ops.operationDBModel.assets.Asset;
import cabletie.cms.ops.operationDBModel.assets.AssetLog;



/**
 * @author xY
 *
 */
@Service
public class AssetService {
	
	@Autowired
	private AssetDAO assDAO;
	@Autowired
	private AssetLogDAO assLogDAO;


	
	/**
	 * @param AssetID
	 * @param infraID
	 * @return List<Asset>
	 * *Get Method* - Get Asset Object as first object in a list 
	 */
	public List<Asset> getAsset(String assetSerialID) {

		return assDAO.findByassetSerialID(assetSerialID);
		
	}


	
	/**
	 * @param infraID
	 * @param name
	 * @param category
	 * @param description
	 * @param quantity
	 * *Create Method* - New Asset in Asset
	 */
	public void createAsset(String assetSerialID, String infraID, String name, String description, String category, 
			Timestamp puchaseDate, Timestamp warrantyDate, String depPeriod,String modBy) {

		Asset ass = new Asset(assetSerialID, infraID, name, description, category, 
				puchaseDate, warrantyDate, depPeriod);
		
		assDAO.save(ass);
		
		AssetLog assLog = new AssetLog(assetSerialID,modBy,2,"");
		
		assLogDAO.save(assLog);
		
	}
	

//
	
	public void updateAsset(String assetSerialID, String infraID, 
			Timestamp warrantyDate, String depPeriod, String modBy) {

		//private int change; // -1 Decom | 0 - Warranty Date | 1 Dep Period | 2 Newly create | 3 Change Location
		
		Asset ass = getAsset(assetSerialID).get(0);
		
		//Logging change warranty Date - 0
		if(!warrantyDate.equals(ass.getWarrantyDate())){
			AssetLog AssetLog = new AssetLog(assetSerialID,modBy,0,ass.getWarrantyDate().toString());
			
			assLogDAO.save(AssetLog);
		}
		
		//Logging change depreciation period - 1
		if(!depPeriod.equals(ass.getDepPeriod())) {
			
			AssetLog assLog = new AssetLog(assetSerialID,modBy,1,String.valueOf(ass.getDepPeriod()));
			
			assLogDAO.save(assLog);
		}
		
		//Logging change location - 3
		if(!infraID.equals(ass.getInfraID())) {
			
			AssetLog assLog = new AssetLog(assetSerialID,modBy,3,String.valueOf(ass.getInfraID()));
			
			assLogDAO.save(assLog);
			
		}
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp currDate = new Timestamp(time);
		
		//Set status of asset to Out of warranty!
		if(warrantyDate.before(currDate)){
			ass.setStatus(0);
		}
		
		ass.setWarrantyDate(warrantyDate);
		ass.setDepPeriod(depPeriod);
		ass.setInfraID(infraID);
		
		assDAO.save(ass);
		
	}
	
	
	/**
	 * @param assetSerialID
	 * @param infraID
	 * @param modBy
	 * 
	 * Disposed Method
	 */
	public void decomAsset(String assetSerialID, String modBy) {
		
		Asset ass = getAsset(assetSerialID).get(0);
		
		ass.setStatus(-1);
		AssetLog assLog = new AssetLog(assetSerialID,modBy,-1,"Disposed");
		
		assDAO.save(ass);
		
		assLogDAO.save(assLog);
	}
	


	/**
	 * @return List<Asset>
	 * 
	 * Return a List of all Asset for the current location
	 */
	public List<Asset> getAllAsset(){

		return assDAO.findAll();
		
	}
	
	public List<AssetLog> getAllAssetLog(){
		
		return assLogDAO.findAll();
	}
		

}
	
