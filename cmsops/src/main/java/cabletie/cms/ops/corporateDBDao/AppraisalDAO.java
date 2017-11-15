package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.Appraisal;
import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.card;

@Repository
public interface AppraisalDAO extends JpaRepository<Appraisal, String> {
	
	List<Appraisal> findByappraisalID(String id);
	
	  @Query("SELECT s FROM Appraisal s WHERE sender =?1")
	    List<Appraisal> findBySender(String sender);
	  
	  @Query("SELECT s FROM Appraisal s WHERE receiver =?1")
	    List<Appraisal> findByReceiver(String receiver);
	  
}