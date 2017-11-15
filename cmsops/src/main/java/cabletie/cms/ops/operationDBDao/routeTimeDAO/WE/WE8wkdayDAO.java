package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE8wkday;


@Repository
public interface WE8wkdayDAO extends JpaRepository<WE8wkday, Integer>{


	List<WE8wkday> findByRouteID(WE8wkday routeID);
	
	
}
