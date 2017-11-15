package cabletie.cms.ops.operationDBDao.procurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.procurement.RequestForPurchase;

import java.util.List;


@Repository
public interface RequestForPurchaseDAO extends JpaRepository<RequestForPurchase, String>{

    List<RequestForPurchase> findByrfpID(String rfpID);
}