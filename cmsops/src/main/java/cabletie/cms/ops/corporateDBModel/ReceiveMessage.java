package cabletie.cms.ops.corporateDBModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ReceiveMessage")
public class ReceiveMessage {

    @Id
    private String receivemsgID;
    private String userID;
    private String status;
    private String flaggedstatus;

    @ManyToOne
    @JoinColumn(name="SendMessage_msgID")
    public SendMessage sendMessage;


    public String getReceivemsgID() {
        return receivemsgID;
    }

    public void setReceivemsgID(String receivemsgID) {
        this.receivemsgID = receivemsgID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }



    public ReceiveMessage() {
    }

    public ReceiveMessage(String userID) {
        this.userID = userID;
        this.status = "active";
        this.receivemsgID = String.valueOf(System.nanoTime());
        this.flaggedstatus = "no";
    }

    public String getFlaggedstatus() {
        return flaggedstatus;
    }

    public void setFlaggedstatus(String flaggedstatus) {
        this.flaggedstatus = flaggedstatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SendMessage getSendmsg() {
        return sendMessage;
    }

    public void setSendmsg(SendMessage sendmsg) {
        this.sendMessage = sendmsg;
    }
}
