package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.MaintenanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceReportDAO extends JpaRepository<MaintenanceReport, String>{
    List<MaintenanceReport> findById(String id);
}
