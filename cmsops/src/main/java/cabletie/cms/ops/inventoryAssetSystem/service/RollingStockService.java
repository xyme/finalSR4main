package cabletie.cms.ops.inventoryAssetSystem.service;

import cabletie.cms.ops.corporateDBModel.SystemAccount;
import cabletie.cms.ops.operationDBDao.infraDAO.depotDAO.DepotDAO;
import cabletie.cms.ops.operationDBDao.train.RollingStockDAO;
import cabletie.cms.ops.operationDBModel.train.RollingStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RollingStockService {
    @Autowired
    RollingStockDAO rsDao;
    @Autowired
    DepotDAO depotDao;

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
     * *Get Method* - Retrieve All Rolling Stock By Depots
     * @param String depotID
     * @return <List<RollingStock>>
     * Completed!
     */
    public List<RollingStock> getAllRSByDepot(String depotID){
        return rsDao.findByDepot(depotID);
    }

    /**
     * *Create Method* - Create Rolling Stock
     * @param String serial, String type, SystemAccount user
     * Completed!
     */
    public boolean createRS(String serial, String type, SystemAccount user){
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        boolean addSuccess = false;

        if(getRS(serial).isEmpty()){
            RollingStock rs = new RollingStock(serial, type, ts);
            rs.setParkDepot(depotDao.findBydepotID( user.getStaff().getLocation() ).get(0));
            rsDao.save(rs);
            addSuccess = true;
        }

        return addSuccess;
    }

    /**
     * *Update Method* - Update Rolling Stock Milleage
     * @param String trainID
     * Completed!
     */
    public void updateRS(String rsSerial, int milleage, int status) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        RollingStock rs = rsDao.findBySerialNo(rsSerial).get(0);

        rs.setMilleage(milleage);
        rs.setLastMilleageUpdate(ts);
        rs.setStatus(status);

        rsDao.save(rs);
    }

    /**
     * *Delete Method* - Delete Rolling Stock
     * @param String trainID
     * Completed!
     */
    public void removeRS(String rsSerial) {
        Date date = Calendar.getInstance().getTime();
        Timestamp ts =  new Timestamp(date.getTime());

        RollingStock rs = rsDao.findBySerialNo(rsSerial).get(0);

        rs.setStatus(-1);
        rs.setDateRemoved(ts);
        rsDao.save(rs);
    }
}
