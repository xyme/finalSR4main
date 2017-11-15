package cabletie.cms.ops.accountworkspace.service;

import cabletie.cms.ops.corporateDBDao.AccountRoleDAO;
import cabletie.cms.ops.corporateDBDao.StaffDAO;
import cabletie.cms.ops.corporateDBDao.SystemAccountDAO;
import cabletie.cms.ops.corporateDBModel.AccountRole;
import cabletie.cms.ops.corporateDBModel.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//splitted into role CRUD and tagging of roles to users
@Service
public class AccountRoleService {

    @Autowired
    private SystemAccountDAO accDAO;
    @Autowired
    private AccountRoleDAO accRole;

    //create new role
    //Input: role ID and role name
    public boolean createRole(String roleID, String roleName) {
        List<AccountRole> roles = accRole.findByroleID(roleID);
        //Avaliable to create the new role
        if(!roles.isEmpty()) {
            if (roles.get(0).getStatus().equals("deleted")) {
                return false;
            }
        }
            AccountRole role = new AccountRole(roleID, roleName);
            accRole.save(role);
            return true;
        }


        //extract role
    public boolean editRole(AccountRole role){
        //output: if the account is deleted return false;
        List<AccountRole> roles = accRole.findByroleID(role.getRoleID());
        if(roles.isEmpty()) {
            return false;
        } if (roles.get(0).getStatus().equals("deleted")) {
            return false;
        }
        //Do the saving here
        accRole.save(role);
        return true;

    }

    //delete role
    public boolean deleteRole (String roleID){
        List<AccountRole> roles = accRole.findByroleID(roleID);
        if(roles.isEmpty()) {
            return false;
        } if (roles.get(0).getStatus().equals("deleted")) {
            return false;
        }
        roles.get(0).setStatus("deleted");
        accRole.save(roles.get(0));

        //delete role from associated users
        return true;

    }


    //Retrive all avaliable role
    public List<AccountRole> retriveRole () {
        List<AccountRole> roles = accRole.findAll();
        List<AccountRole> nonDeletedRoles = new ArrayList<AccountRole>();

        if (roles.isEmpty()) {
            return new ArrayList<AccountRole>();
        }
        //store all non-deleted roles into nondeletedroles list
        for (AccountRole e : roles) {
            if (e.getStatus().equals("deleted")) {
            } else {
                //populate list
                nonDeletedRoles.add(e);
            }
        }
        return nonDeletedRoles;
    }



    //Retrive all deleted roles (for audit purpose)
    public List<AccountRole> retriveDeletedRole () {
        List<AccountRole> roles = accRole.findAll();
        List<AccountRole> deletedRoles = new ArrayList<AccountRole>();

        if (roles.isEmpty()) {
            return new ArrayList<AccountRole>();
        }
        //store all non-deleted roles into nondeletedroles list
        for (AccountRole e : roles) {
            if (!e.getStatus().equals("deleted")) {
            } else {
                //populate list
                deletedRoles.add(e);
            }
        }
        return deletedRoles;
    }


//    <<<<<--------------------Below methods manipulates roles with users --------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




    //tag role to a user
    public boolean tagRole (String userID, List<AccountRole> roles){
        //check if roleID exists
//        List<AccountRole> roles = accRole.findByroleID((roleID));
//        if(roles.isEmpty()) {
//            return false;
//        } if (roles.get(0).getStatus().equals("deleted")) {
//            return false;
//        }

        //check if userID exists
        List<SystemAccount> acct = accDAO.findByuserID(userID);
        if(acct.isEmpty()) {
            return false;
        } if (acct.get(0).getStatus().equals("deleted")) {
            return false;
        }

        //check if account already has the role
//        if(!acct.get(0).getRoles().isEmpty()) {
//            List<AccountRole> retrievedRoles = acct.get(0).getRoles();
//            for (int i = 0; i < retrievedRoles.size(); i++) {
//                if (retrievedRoles.get(i).getRoleID().equals(roleID)) {
//                    return false;
//                }
//            }
//        }

        //tag role to account
        acct.get(0).setRoles(null);

        for(int i = 0; i < roles.size(); i++ ){
           acct.get(0).getRoles().add(roles.get(i));
        }
        accDAO.save(acct);
        //tag account to role
        for(int i = 0 ; i < roles.size(); i++) {
            for (int j = 0; j < roles.get(i).getSys().size(); j++) {
                if (roles.get(i).getSys().get(j).getUserID().equals(acct.get(0).getUserID())) {
                } else {
                    //add user to role
                    roles.get(i).getSys().add(acct.get(0));
                }
            }
        }
        accRole.save(roles);
        return true;

    }

    //delete role from user
    public boolean deleteUserRole (String userID, String roleID){
        //skip the check for userID and roleID since it is inserted from the View layer
        List<SystemAccount> acct = accDAO.findByuserID(userID);
        List<AccountRole> roles = accRole.findByroleID((roleID));

        //if both are empty
        if(acct.isEmpty() || roles.isEmpty()){
            return false;
        }

        //check if account does have the role
        if(!acct.get(0).getRoles().isEmpty()) {
            List<AccountRole> retrievedRoles = acct.get(0).getRoles();
            for (int i = 0; i < retrievedRoles.size(); i++) {
                if (retrievedRoles.get(i).getRoleID().equals(roleID)) {
                    //untag role to account
                    acct.get(0).getRoles().remove(i);
                    accDAO.save(acct);
                    //untag account to role
                    roles.get(0).getSys().remove(acct.get(0));
                    accRole.save(roles);
                    return true;
                }
            }
        } else {
            return false;
        }

    return true;
    }



    public boolean deleteRoleUser(SystemAccount acct, List<AccountRole> roles){
        //remove users from roles
        for (int i = 0 ; i < roles.size(); i ++){
            roles.get(i).getSys().remove(acct);
        }
        accRole.save(roles);
        return true;
    }

    //retrieve all users from the role specified
    public List<SystemAccount> findAllUserWithRole(String roleID){
        List<SystemAccount> acct = accRole.findByroleID(roleID).get(0).getSys();
        return acct;
    }


    }
