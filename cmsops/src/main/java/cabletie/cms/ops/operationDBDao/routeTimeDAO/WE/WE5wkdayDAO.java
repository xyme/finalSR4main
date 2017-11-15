package cabletie.cms.ops.operationDBDao.routeTimeDAO.WE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.WE.WE5wkday;


@Repository
public interface WE5wkdayDAO extends JpaRepository<WE5wkday, Integer>{


	List<WE5wkday> findByRouteID(WE5wkday routeID);
	
	
}
