package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN5wkendph;

@Repository
public interface SN5wkendphDAO extends JpaRepository<SN5wkendph, Integer>{


	List<SN5wkendph> findByRouteID(SN5wkendph routeID);
	
	
}

