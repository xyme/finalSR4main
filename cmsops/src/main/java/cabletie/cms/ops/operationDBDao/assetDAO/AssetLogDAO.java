package cabletie.cms.ops.operationDBDao.assetDAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.assets.AssetLog;


@Repository
public interface AssetLogDAO extends JpaRepository<AssetLog, Long>{

	
	
}