package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS6wkday;


@Repository
public interface NS6wkdayDAO extends JpaRepository<NS6wkday, Integer>{


	List<NS6wkday> findByRouteID(NS6wkday routeID);
	
	
}
