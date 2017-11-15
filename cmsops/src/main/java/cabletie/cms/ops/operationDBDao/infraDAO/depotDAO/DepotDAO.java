package cabletie.cms.ops.operationDBDao.infraDAO.depotDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Depot.Depot;

@Repository
public interface DepotDAO extends JpaRepository<Depot, String>{


	List<Depot> findBydepotID(String depotID);
	
}