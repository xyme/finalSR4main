package cabletie.cms.ops.operationDBDao.infraDAO.stationDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Station.StationLeaseSpace;

@Repository
public interface StationLeaseSpaceDAO extends JpaRepository<StationLeaseSpace, String>{
	
}