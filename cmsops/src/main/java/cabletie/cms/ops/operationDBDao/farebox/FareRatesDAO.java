package cabletie.cms.ops.operationDBDao.farebox;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.farebox.FareRates;
import cabletie.cms.ops.operationDBModel.routeTime.RouteTrain;



@Repository
public interface FareRatesDAO extends JpaRepository<FareRates, Integer>{


	
}