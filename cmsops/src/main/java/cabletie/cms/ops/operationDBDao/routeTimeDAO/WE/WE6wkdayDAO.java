package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE6wkday;


@Repository
public interface WE6wkdayDAO extends JpaRepository<WE6wkday, Integer>{


	List<WE6wkday> findByRouteID(WE6wkday routeID);
	
	
}
