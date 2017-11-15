package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route8Table;


@Repository
public interface route8TableDAO extends JpaRepository<Route8Table, Integer>{


	List<Route8Table> findBycounter(int counter);
	
	
}
