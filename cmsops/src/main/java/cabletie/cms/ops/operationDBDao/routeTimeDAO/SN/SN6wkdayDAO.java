package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN6wkday;


@Repository
public interface SN6wkdayDAO extends JpaRepository<SN6wkday, Integer>{


	List<SN6wkday> findByRouteID(SN6wkday routeID);
	
	
}
