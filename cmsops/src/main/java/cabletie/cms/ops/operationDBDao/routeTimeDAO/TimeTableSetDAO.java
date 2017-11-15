package cabletie.cms.ops.operationDBDao.routeTimeDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.routeTime.TimeTableSet;


@Repository
public interface TimeTableSetDAO extends JpaRepository<TimeTableSet, Integer>{


	List<TimeTableSet> findBysetID(int setID);
	
}
