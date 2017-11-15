package cabletie.cms.ops.operationDBDao.routeTimeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.RouteTrain;



@Repository
public interface RouteTrainDAO extends JpaRepository<RouteTrain, Integer>{

	List<RouteTrain> findByrouteTrainID(String routeTrainID);


	
}