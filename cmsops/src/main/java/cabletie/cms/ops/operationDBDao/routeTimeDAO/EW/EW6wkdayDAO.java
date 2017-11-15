package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW6wkday;


@Repository
public interface EW6wkdayDAO extends JpaRepository<EW6wkday, Integer>{


	List<EW6wkday> findByRouteID(EW6wkday routeID);
	
	
}
