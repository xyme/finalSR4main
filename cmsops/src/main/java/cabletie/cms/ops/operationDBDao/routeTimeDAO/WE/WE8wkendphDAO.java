package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE8wkendph;


@Repository
public interface WE8wkendphDAO extends JpaRepository<WE8wkendph, Integer>{


	List<WE8wkendph> findByRouteID(WE8wkendph routeID);
	
	
}
