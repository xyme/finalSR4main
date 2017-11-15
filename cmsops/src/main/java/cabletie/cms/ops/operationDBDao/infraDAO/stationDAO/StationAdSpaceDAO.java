package cabletie.cms.ops.operationDBDao.infraDAO.stationDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Station.StationAdSpace;


@Repository
public interface StationAdSpaceDAO extends JpaRepository<StationAdSpace, String>{
	
}