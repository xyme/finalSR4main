package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.AccountRole;

@Repository
public interface AccountRoleDAO  extends JpaRepository<AccountRole, String> {

    List<AccountRole> findByroleID(String roleID);
}
