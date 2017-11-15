package cabletie.cms.ops.operationDBDao.procurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.procurement.ProcurementTask;

import java.util.List;

@Repository
public interface ProcurementTaskDAO extends JpaRepository<ProcurementTask, String>{

    List<ProcurementTask> findByProcID(String procID);
}