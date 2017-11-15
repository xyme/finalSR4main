package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeTableDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeTable.CurrentEW;




@Repository
public interface CurrentEWDAO extends JpaRepository<CurrentEW, Integer>{


	List< CurrentEW> findByRouteID( CurrentEW routeID);
	
	
}
