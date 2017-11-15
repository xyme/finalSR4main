package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW8wkday;


@Repository
public interface EW8wkdayDAO extends JpaRepository<EW8wkday, Integer>{


	List<EW8wkday> findByRouteID(EW8wkday routeID);
	
	
}
