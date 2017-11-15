package cabletie.cms.ops.accountworkspace.service;

import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceManagementService {

    @Autowired
    private SystemAccountDAO acctDAO;

    //check if login account exists
    public boolean loginCheck(String userid){
        List<SystemAccount> sys = acctDAO.findByuserID(userid);
        if (sys.isEmpty()) {
          return false;
        } if ( sys.get(0).getStatus().equals("deleted")){
           return false;
        }
    return true;
    }

    //check if password used is correct
    public boolean validateLogin(String userid, String password){
        List<SystemAccount> sys = acctDAO.findByuserID(userid);
        if(sys.get(0).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public List<SystemAccount> retrieveAcct(String userid){
        return acctDAO.findByuserID(userid);
    }

}
