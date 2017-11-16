package cabletie.cms.ops.operationDBDao.reportsDAO;


import cabletie.cms.ops.operationDBModel.reports.TrainSvcLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainSvcLogDAO extends JpaRepository<TrainSvcLog, String>{

    List<TrainSvcLog> findBylogID(String logID);


}
