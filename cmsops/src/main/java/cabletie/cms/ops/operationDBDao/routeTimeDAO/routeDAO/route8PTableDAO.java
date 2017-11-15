package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route5Table;
import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route8PTable;


@Repository
public interface route8PTableDAO extends JpaRepository<Route8PTable, Integer>{


	List<Route8PTable> findBycounter(int counter);

	List<Route5Table> findByrouteID(int routeID);
	
	
}
