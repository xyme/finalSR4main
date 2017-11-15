package cabletie.cms.ops.operationDBDao.routeTimeDAO.SN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.fixTable.northsouth.SN.SN5wkday;


@Repository
public interface SN5wkdayDAO extends JpaRepository<SN5wkday, Integer>{


	List<SN5wkday> findByRouteID(SN5wkday routeID);
	
	
}
