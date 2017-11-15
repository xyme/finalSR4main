package cabletie.cms.ops.operationDBDao.routeTimeDAO.routeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.routeAssign.Route6Table;


@Repository
public interface route6TableDAO extends JpaRepository<Route6Table, Integer>{


	List<Route6Table> findBycounter(int counter);
	
	
}
