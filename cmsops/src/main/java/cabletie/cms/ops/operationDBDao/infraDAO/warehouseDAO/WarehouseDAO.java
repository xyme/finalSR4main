package cabletie.cms.ops.operationDBDao.infraDAO.warehouseDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Warehouse.Warehouse;

@Repository
public interface WarehouseDAO extends JpaRepository<Warehouse, String>{

	List<Warehouse> findByWarehouseID(String warehouseID);
	
}