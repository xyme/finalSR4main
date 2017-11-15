package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceRequest;
import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.applet.Main;

import java.util.List;

@Repository
public interface MaintenanceTaskDAO extends JpaRepository<MaintenanceTask, String>{

    List<MaintenanceTask> findByTaskID(String id);
    List<MaintenanceTask> findByLocation(String loc);
    List<MaintenanceTask> findByLocationAndStaffTeamID(String loc, String team);
}
