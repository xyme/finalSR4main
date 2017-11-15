package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN8wkday;


@Repository
public interface SN8wkdayDAO extends JpaRepository<SN8wkday, Integer>{


	List<SN8wkday> findByRouteID(SN8wkday routeID);
	
	
}
