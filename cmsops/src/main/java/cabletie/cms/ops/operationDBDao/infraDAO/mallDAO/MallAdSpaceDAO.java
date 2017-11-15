package cabletie.cms.ops.operationDBDao.infraDAO.mallDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Mall.MallAdSpace;

@Repository
public interface MallAdSpaceDAO extends JpaRepository<MallAdSpace, String>{
	
}