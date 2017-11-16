package cabletie.cms.ops.operationDBDao.routeTimeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.OpService;


@Repository
public interface OpServiceDAO extends JpaRepository<OpService, String>{

	List<OpService> findBylineID(String lineID);


	
}