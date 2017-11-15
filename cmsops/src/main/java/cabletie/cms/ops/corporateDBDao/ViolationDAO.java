package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.Appraisal;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.Violation;
import cabletie.cms.ops.corporateDBModel.card;

@Repository
public interface ViolationDAO extends JpaRepository<Violation, String> {
	
	List<Violation> findByviolationID(String id);
	
	  @Query("SELECT s FROM Violation s WHERE sender =?1")
	    List<Violation> findBySender(String sender);
	  
	  @Query("SELECT s FROM Violation s WHERE receiver =?1")
	    List<Violation> findByReceiver(String receiver);
	  
}