package cabletie.cms.ops.corporateDBDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabletie.cms.ops.corporateDBModel.Feedback;
import cabletie.cms.ops.corporateDBModel.Passenger;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, String>

    {

        List<Feedback> findByemail(String email);
        List<Feedback> findBynum(String num);
    }