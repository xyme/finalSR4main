package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN6wkendph;


@Repository
public interface SN6wkendphDAO extends JpaRepository<SN6wkendph, Integer>{


	List<SN6wkendph> findByRouteID(SN6wkendph routeID);
	
	
}
