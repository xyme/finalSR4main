package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route5Table;
import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route6PTable;


@Repository
public interface route6PTableDAO extends JpaRepository<Route6PTable, Integer>{


	List<Route6PTable> findBycounter(int counter);

	List<Route5Table> findByrouteID(int routeID);
	
	
}
