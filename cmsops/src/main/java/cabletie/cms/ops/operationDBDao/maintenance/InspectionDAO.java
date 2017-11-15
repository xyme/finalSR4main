package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionDAO extends JpaRepository<Inspection, String>{

    List<Inspection> findByInspectId(String id);
    List<Inspection> findByLocation(String loc);
    List<Inspection> findByLocationAndStaffTeam(String loc, String teamID);
}
