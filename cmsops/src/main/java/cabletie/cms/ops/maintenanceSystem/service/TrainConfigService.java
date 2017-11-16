package cabletie.cms.ops.maintenanceSystem.service;

import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.infraDAO.depotDAO.DepotDAO;
import cabletie.cms.ops.operationDBDao.routeTimeDAO.RouteTrainDAO;
import cabletie.cms.ops.operationDBDao.train.RollingStockDAO;
import cabletie.cms.ops.operationDBDao.train.TrainDAO;
import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceTask;
import cabletie.cms.ops.operationDBModel.routeTime.RouteTrain;
import cabletie.cms.ops.operationDBModel.train.RollingStock;
import cabletie.cms.ops.operationDBModel.train.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TrainConfigService {
    @Autowired
    RollingStockDAO rsDao;
    @Autowired
    TrainDAO tDao;
    @Autowired
    DepotDAO depotDao;
    @Autowired 
    RouteTrainDAO routeTrainDAO;

    /**
     * *Get Method* - Retrieve Rolling Stock
     * @param String serialNo
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getRS(String serialNo){
        return rsDao.findBySerialNo(serialNo);
    }

    /**
     * *Get Method* - Retrieve Operational Rolling Stock By Depots
     * @param String depotID
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getRSByDepot(String depotID){
        return rsDao.findByDepot(depotID);
    }

    /**
     * *Get Method* - Retrieve Rolling Stocks For Edit Details Page
     * @param String depotID, String trainID
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getEditRSList(String depotID, String trainID){
        return rsDao.getRSListEdit(depotID, trainID);
    }

    /**
     * *Get Method* - Retrieve Train
     * @param String trainID
     * @return <List<Train>>
     * Completed!
     */
    public List<Train> getTrain(String trainID){
        return tDao.findByTrainId(trainID);
    }

    /**
     * *Get Method* - Retrieve Train List By Depot
     * @param String depotID
     * @return <List<Train>>
     * Completed!
     */
    public List<Train> getDepotTrainList(String depotID){
        return tDao.findByDepot(depotID);
    }

    /**
     * *Create Method* - Create Train Configuration
     * @param none
     * Completed!
     */
    public void createTrain(String type, ArrayList<String> rsIDList, SystemAccount user) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        List<Train> currentList = tDao.findAll();
        Train t;
        //Instantiate Train Constructor
        if (currentList.size() < 9) {
            t = new Train("T0" + (currentList.size() + 1), type, ts);
        } else {
            t = new Train("T" + (currentList.size() + 1), type, ts);
        }

        //Tag Depot to Train according to which Depot the staff belongs to
        t.setParkDepot(depotDao.findBydepotID(user.getStaff().getLocation()).get(0));
        tDao.save(t);

        //Retrieve Rolling Stock List to be added to Train Configuration
        List<RollingStock> addRSList = new ArrayList<>();
        int pos=1;
        for(String s: rsIDList){
            RollingStock rs = rsDao.findBySerialNo(s).get(0);
            addRSList.add(rs);

            rs.setPosition(pos);
            rs.setStatus(2);
            rs.setTrain(t);
            rsDao.save(rs);
            pos++;
        }

        //Set addRSList to Train Configuration
        t.setRolling(addRSList);
        tDao.save(t);
    }

    /**
     * *Update Method* - Update Train Configuration
     * @param String trainID
     * Completed!
     */
    public void updateTrain(String trainID, ArrayList<String> rsIDList, int status) {
        Train t = getTrain(trainID).get(0);

        //Reset all current RS position/status/trainID
        List<RollingStock> oldRSList = t.getRolling();
        for(RollingStock rs:oldRSList){
            rs.setPosition(0);
            rs.setStatus(1);
            rs.setTrain(null);
            rsDao.save(rs);
        }

        //Retrieve Rolling Stock List to be added to Train Configuration
        List<RollingStock> addRSList = new ArrayList<>();
        int pos=1;
        for(String s: rsIDList){
            RollingStock rs = rsDao.findBySerialNo(s).get(0);
            addRSList.add(rs);

            rs.setPosition(pos);
            rs.setStatus(2);
            rs.setTrain(t);
            rsDao.save(rs);
            pos++;
        }

        t.setStatus(status);
        t.setRolling(addRSList);
        tDao.save(t);
    }

    /**
     * *Delete Method* - Delete Train Configuration
     * @param String trainID
     * Completed!
     */
    public void removeTrain(String trainID) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        Train t = getTrain(trainID).get(0);
        List<RollingStock> rsList = t.getRolling();

        for(RollingStock rs:rsList){
            rs.setPosition(0);
            rs.setStatus(1);
            rs.setTrain(null);
            rsDao.save(rs);
        }

        t.setStatus(-1);
        t.setDateRemoved(ts);
        t.setRolling(new ArrayList<RollingStock>());

        tDao.save(t);
    }
    
    public boolean checkAssignment(String trainID) {
    	
    	List<RouteTrain> trainList = routeTrainDAO.findAll();
    	
    	boolean assigned = false;
    	for (RouteTrain rt : trainList) {
    		if(trainID.equals(rt.getTrainID())){
    			assigned = true;
    		}
    	}
    	
    	return assigned;
    }
}
