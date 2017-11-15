package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN8wkendph;


@Repository
public interface SN8wkendphDAO extends JpaRepository<SN8wkendph, Integer>{


	List<SN8wkendph> findByRouteID(SN8wkendph routeID);
	
	
}
