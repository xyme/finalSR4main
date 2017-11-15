package cabletie.cms.ops.corporateDBDao.eHR;

import cabletie.cms.ops.corporateDBModel.Staff;
import cabletie.cms.ops.corporateDBModel.eHR.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRecordDAO extends JpaRepository<AttendanceRecord, Long>{

    List<AttendanceRecord> findByStaff(Staff s);
}
