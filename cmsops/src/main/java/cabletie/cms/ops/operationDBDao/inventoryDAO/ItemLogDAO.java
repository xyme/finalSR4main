package cabletie.cms.ops.operationDBDao.inventoryDAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.inventory.Item;
import cabletie.cms.ops.operationDBModel.inventory.ItemLog;

@Repository
public interface ItemLogDAO extends JpaRepository<ItemLog, Integer>{

	List<ItemLog> findByitemID(int itemID);
	
}