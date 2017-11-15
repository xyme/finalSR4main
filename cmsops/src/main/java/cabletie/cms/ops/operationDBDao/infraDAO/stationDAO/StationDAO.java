package cabletie.cms.ops.operationDBDao.infraDAO.stationDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Station.Station;

@Repository
public interface StationDAO extends JpaRepository<Station, String>{

	List<Station> findByStationID(String stationID);
	
}