package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS5wkendph;

@Repository
public interface NS5wkendphDAO extends JpaRepository<NS5wkendph, Integer>{


	List<NS5wkendph> findByRouteID(NS5wkendph routeID);
	
	
}

