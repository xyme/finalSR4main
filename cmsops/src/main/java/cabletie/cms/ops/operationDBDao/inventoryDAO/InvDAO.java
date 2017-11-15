package cabletie.cms.ops.operationDBDao.inventoryDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.inventory.InventoryID;
import cabletie.cms.ops.operationDBModel.inventory.Item;

@Repository
public interface InvDAO extends JpaRepository<Item, Integer>{


	List<Item> findAllByitemID(int itemID);
	
	
}