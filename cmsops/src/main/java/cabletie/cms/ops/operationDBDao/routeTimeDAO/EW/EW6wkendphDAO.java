package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW6wkendph;


@Repository
public interface EW6wkendphDAO extends JpaRepository<EW6wkendph, Integer>{


	List<EW6wkendph> findByRouteID(EW6wkendph routeID);
	
	
}
