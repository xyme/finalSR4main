package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.SystemAccount;

@Repository
public interface SystemAccountDAO extends JpaRepository<SystemAccount, String> {

    List<SystemAccount> findByuserID(String userID);
    List<SystemAccount> findByuserGroup(String userGroup);

}
