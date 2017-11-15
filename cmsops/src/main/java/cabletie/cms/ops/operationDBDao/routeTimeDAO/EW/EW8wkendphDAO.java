package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW8wkendph;


@Repository
public interface EW8wkendphDAO extends JpaRepository<EW8wkendph, Integer>{


	List<EW8wkendph> findByRouteID(EW8wkendph routeID);
	
	
}
