package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeTableDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeTable.CurrentSN;




@Repository
public interface CurrentSNDAO extends JpaRepository<CurrentSN, Integer>{


	List< CurrentSN> findByRouteID( CurrentSN routeID);
	
	
}
