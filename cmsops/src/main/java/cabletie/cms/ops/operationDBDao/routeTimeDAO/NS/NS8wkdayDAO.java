package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS8wkday;


@Repository
public interface NS8wkdayDAO extends JpaRepository<NS8wkday, Integer>{


	List<NS8wkday> findByRouteID(NS8wkday routeID);
	
	
}
