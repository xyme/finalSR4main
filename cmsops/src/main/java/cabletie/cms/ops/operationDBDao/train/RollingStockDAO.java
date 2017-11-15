package cabletie.cms.ops.operationDBDao.train;

import cabletie.cms.ops.operationDBModel.train.RollingStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollingStockDAO extends JpaRepository<RollingStock, String> {

    List<RollingStock> findBySerialNo(String serial);

    @Query("SELECT rs FROM RollingStock rs WHERE Depot_depotID=?1")
    List<RollingStock> findByDepot(String depotID);

    @Query("SELECT rs FROM RollingStock rs WHERE Depot_depotID=?1 AND Train_trainID=?2 AND status=2 OR Depot_depotID=?1 AND Train_trainID IS NULL AND status=1")
    List<RollingStock> getRSListEdit(String depotID, String trainID);
}
