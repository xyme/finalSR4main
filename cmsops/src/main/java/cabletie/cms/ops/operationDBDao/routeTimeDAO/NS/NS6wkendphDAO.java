package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS6wkendph;


@Repository
public interface NS6wkendphDAO extends JpaRepository<NS6wkendph, Integer>{


	List<NS6wkendph> findByRouteID(NS6wkendph routeID);
	
	
}
