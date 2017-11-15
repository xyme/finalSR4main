package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeTableDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeTable.CurrentNS;




@Repository
public interface CurrentNSDAO extends JpaRepository<CurrentNS, Integer>{


	List< CurrentNS> findByRouteID( CurrentNS routeID);
	
	
}
