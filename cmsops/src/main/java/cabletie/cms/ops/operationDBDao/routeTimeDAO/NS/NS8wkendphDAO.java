package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS8wkendph;


@Repository
public interface NS8wkendphDAO extends JpaRepository<NS8wkendph, Integer>{


	List<NS8wkendph> findByRouteID(NS8wkendph routeID);
	
	
}
