package cabletie.cms.ops.operationDBDao.procurement;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.procurement.PurchaseItem;

@Repository
public interface PurchaseItemDAO extends JpaRepository<PurchaseItem, String>{
	
	@Query("SELECT pi FROM PurchaseItem pi WHERE RequestForPurchase_rfpID=?1")
	List<PurchaseItem> findByRfp(String rfpId);
	List<PurchaseItem> findByItemID(String itemId);
}