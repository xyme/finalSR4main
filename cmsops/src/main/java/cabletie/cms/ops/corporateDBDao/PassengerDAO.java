package cabletie.cms.ops.corporateDBDao;

import cabletie.cms.ops.corporateDBModel.Passenger;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerDAO extends JpaRepository<Passenger, String>

    {

        List<Passenger> findByemail(String email);

    }