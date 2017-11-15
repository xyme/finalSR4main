package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRequestDAO extends JpaRepository<MaintenanceRequest, String>{

    List<MaintenanceRequest> findByReqID(String id);
    List<MaintenanceRequest> findByReqLocation(String loc);
}
