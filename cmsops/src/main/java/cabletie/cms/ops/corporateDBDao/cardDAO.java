package cabletie.cms.ops.corporateDBDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import cabletie.cms.ops.corporateDBModel.card;

@Repository
public interface cardDAO extends JpaRepository<card, String> {
	
	List<card> findBycardnum(String cardnum);
}
