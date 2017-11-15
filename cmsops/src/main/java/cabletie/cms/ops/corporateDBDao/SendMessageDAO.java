package cabletie.cms.ops.corporateDBDao;

import cabletie.cms.ops.corporateDBModel.SendMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMessageDAO extends JpaRepository<SendMessage, String> {

    List<SendMessage> findBymsgID(String msgID);
    List<SendMessage> findByuserID(String userID);
}
