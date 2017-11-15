package cabletie.cms.ops.corporateDBModel;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "SendMessage")
public class SendMessage {
	
	
	@Id
    private String msgID;
    private String description;
    private String sender;
    private Timestamp timeSent;
    private String status;
    private String msgtype;
    private String userID;

	@OneToMany(mappedBy = "sendMessage")
	public List<ReceiveMessage> receiveMessage;

	public SendMessage() {
	}

	public SendMessage(String description, String sender, Timestamp timeSent, String msgtype,
					   String userID) {
		this.msgID = String.valueOf(System.nanoTime());
		this.description = description;
		this.sender = sender;
		this.timeSent = timeSent;
		this.status = "active";
		this.msgtype = msgtype;
		this.userID = userID;
	}

	public Timestamp getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Timestamp timeSent) {
		this.timeSent = timeSent;
	}

	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}


	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<ReceiveMessage> getReceiveMessages() {
		return receiveMessage;
	}

	public void setReceiveMessages(List<ReceiveMessage> receiveMessages) {
		this.receiveMessage = receiveMessages;
	}
}