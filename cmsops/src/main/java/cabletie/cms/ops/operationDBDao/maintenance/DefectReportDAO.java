package cabletie.cms.ops.operationDBDao.maintenance;

import cabletie.cms.ops.operationDBModel.maintenance.DefectReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectReportDAO extends JpaRepository<DefectReport, String> {
    List<DefectReport> findByDefectId(String id);
    
    @Query("SELECT s FROM DefectReport s WHERE email =?1")
    List<DefectReport> findByEmail(String email);
}
