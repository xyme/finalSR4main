package cabletie.cms.ops.corporateDBModel;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "Violation")
	public class Violation {
		@Id
		private String violationID;
		private String sender;
		private String receiver;
		private String description;
		private String date;
		
	
		public String getViolationID() {
			return violationID;
		}
		public void setViolationID(String violationID) {
			this.violationID = violationID;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public String getReceiver() {
			return receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public Violation() {
			
	}
		public Violation(String sender, String receiver, String description, String date) {
			this.violationID = String.valueOf(System.nanoTime());
			this.sender = sender;
			this.receiver = receiver;
			this.description = description;
	
			this.date = date;
		}
	}