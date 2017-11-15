package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW5wkendph;

@Repository
public interface EW5wkendphDAO extends JpaRepository<EW5wkendph, Integer>{


	List<EW5wkendph> findByRouteID(EW5wkendph routeID);
	
	
}

