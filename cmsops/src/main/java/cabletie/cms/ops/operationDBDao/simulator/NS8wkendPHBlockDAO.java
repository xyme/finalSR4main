package cabletie.cms.ops.operationDBDao.simulator;

import cabletie.cms.ops.operationDBModel.simulator.NS8wkendPHBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NS8wkendPHBlockDAO extends JpaRepository<NS8wkendPHBlock, String> {

    List<NS8wkendPHBlock> findByrouteID(String routeID);


}
