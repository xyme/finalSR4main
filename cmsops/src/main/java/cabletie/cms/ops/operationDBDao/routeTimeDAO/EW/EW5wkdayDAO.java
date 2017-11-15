package cabletie.cms.ops.operationDBDao.routeTimeDAO.EW;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.eastwest.EW.EW5wkday;


@Repository
public interface EW5wkdayDAO extends JpaRepository<EW5wkday, Integer>{


	List<EW5wkday> findByRouteID(EW5wkday routeID);
	
	
}
