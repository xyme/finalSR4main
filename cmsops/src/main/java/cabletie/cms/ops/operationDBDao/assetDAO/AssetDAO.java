package cabletie.cms.ops.operationDBDao.assetDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.assets.Asset;

@Repository
public interface AssetDAO extends JpaRepository<Asset, String>{


	List<Asset> findByassetSerialID(String assetSerialID);

	List<Asset> findByInfraID(String infraID);
	
}