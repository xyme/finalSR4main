package cabletie.cms.ops.corporateDBDao;

import cabletie.cms.ops.corporateDBModel.StaffTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffTeamDAO extends JpaRepository<StaffTeam, String> {

    @Query("SELECT DISTINCT location FROM StaffTeam")
    List<String> getDistinctLocations();
    List<StaffTeam> findByLocation(String loc);
    List<StaffTeam> findByTeamId(String id);
}
