package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE5wkendph;

@Repository
public interface WE5wkendphDAO extends JpaRepository<WE5wkendph, Integer>{


	List<WE5wkendph> findByRouteID(WE5wkendph routeID);
	
	
}

