package cabletie.cms.ops.corporateDBDao;

import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.SendMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveMessageDAO  extends JpaRepository<ReceiveMessage, String> {

    List<ReceiveMessage> findByreceivemsgID(String msgID);
    List<ReceiveMessage> findByuserID(String userID);

}