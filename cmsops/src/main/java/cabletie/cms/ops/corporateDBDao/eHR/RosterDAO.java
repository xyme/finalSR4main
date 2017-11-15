package cabletie.cms.ops.corporateDBDao.eHR;

import cabletie.cms.ops.corporateDBModel.StaffTeam;
import cabletie.cms.ops.corporateDBModel.eHR.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RosterDAO extends JpaRepository<Roster, Long> {

    @Query("SELECT DISTINCT month FROM Roster WHERE year=?1 AND location=?2")
    List<Integer> getMonthsGenerated(int year, String loc);

    List<Roster> findByRosterId(long id);
    List<Roster> findByMonthAndYear(int month, int year);
    List<Roster> findByLocation(String loc);

    @Query("SELECT r FROM Roster r WHERE month=?1 AND year=?2 AND StaffTeam_teamID=?3")
    List<Roster> findStaffRoster(int month, int year, String team);
}
