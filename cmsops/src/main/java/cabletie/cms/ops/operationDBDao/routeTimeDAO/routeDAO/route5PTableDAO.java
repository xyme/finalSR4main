package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route5PTable;


@Repository
public interface route5PTableDAO extends JpaRepository<Route5PTable, Integer>{


	List<Route5PTable> findBycounter(int counter);
	
	
}
