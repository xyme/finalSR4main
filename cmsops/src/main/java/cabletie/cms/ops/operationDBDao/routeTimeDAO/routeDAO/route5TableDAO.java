package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route5Table;


@Repository
public interface route5TableDAO extends JpaRepository<Route5Table, Integer>{


	List<Route5Table> findBycounter(int counter);
	
	
}
