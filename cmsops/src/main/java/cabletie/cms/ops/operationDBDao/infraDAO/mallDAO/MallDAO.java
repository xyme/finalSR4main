package cabletie.cms.ops.operationDBDao.infraDAO.mallDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.operationDBModel.infra.Mall.Mall;

@Repository
public interface MallDAO extends JpaRepository<Mall, String>{

	List<Mall> findByMallID(String mallID);
	

}