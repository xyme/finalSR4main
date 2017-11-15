package cabletie.cms.ops.operationDBDao.train;

import cabletie.cms.ops.operationDBModel.train.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDAO extends JpaRepository<Train, String>{

    List<Train> findByTrainId(String id);

    @Query("SELECT t FROM Train t WHERE Depot_depotID=?1")
    List<Train> findByDepot(String depotID);
    List<Train> findBystatus(int status);
}
