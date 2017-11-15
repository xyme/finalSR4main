package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.InspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionReportDAO extends JpaRepository<InspectionReport, String>{
    List<InspectionReport> findById(String id);
}
