package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeTableDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeTable.CurrentWE;




@Repository
public interface CurrentWEDAO extends JpaRepository<CurrentWE, Integer>{


	List< CurrentWE> findByRouteID( CurrentWE routeID);
	
	
}
