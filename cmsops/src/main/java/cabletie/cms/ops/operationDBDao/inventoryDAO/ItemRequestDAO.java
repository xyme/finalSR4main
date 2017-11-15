package cabletie.cms.ops.operationDBDao.inventoryDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.transferItem.ItemRequest;

@Repository
public interface ItemRequestDAO extends JpaRepository<ItemRequest, Integer>{
	
	List<ItemRequest> findByrequestID(int requestID);
}