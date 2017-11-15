package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE6wkendph;


@Repository
public interface WE6wkendphDAO extends JpaRepository<WE6wkendph, Integer>{


	List<WE6wkendph> findByRouteID(WE6wkendph routeID);
	
	
}
