package cabletie.cms.ops.corporateDBDao;


import cabletie.cms.ops.corporateDBModel.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDAO extends JpaRepository<Staff, String> {

    List<Staff> findByStaffId(String staffID);
    List<Staff> findByIdNo(String idNo);

    @Query("SELECT s FROM Staff s WHERE location=?1 AND StaffTeam_teamID IS NULL")
    List<Staff> findUnassignedStaffByLocation(String loc);

    @Query("SELECT s FROM Staff s WHERE SystemAccount_userID=?1")
    List<Staff> findByAccount(String userID);

    @Query("SELECT s FROM Staff s WHERE department =?1")
    List<Staff> findByDepartment(String dept);
}

