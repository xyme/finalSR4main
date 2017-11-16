package cabletie.cms.ops.operationDBDao.reportsDAO;

import cabletie.cms.ops.operationDBModel.reports.ReportObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportObjDAO extends JpaRepository<ReportObj, String>{

    List<ReportObj> findByreportID(String reportID);


}
