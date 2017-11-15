package cabletie.cms.ops.operationDBDao.routeTimeDAO.NS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.NS.NS5wkday;




@Repository
public interface NS5wkdayDAO extends JpaRepository<NS5wkday, Integer>{


	List<NS5wkday> findByRouteID(NS5wkday routeID);
	
	
}
