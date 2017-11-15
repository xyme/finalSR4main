package cabletie.cms.ops.corporateDBModel;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "Appraisal")
	public class Appraisal {
		@Id
		private String appraisalID;
		private String sender;
		private String receiver;
		private String description;
		private String decision;
		private String quarter;
		private String date;
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getQuarter() {
			return quarter;
		}
		public void setQuarter(String quarter) {
			this.quarter = quarter;
		}
		public Appraisal() {
			
	}
		public Appraisal(String sender, String receiver, String description, String decision, String  currentQuarter, String timestamp) {
			this.appraisalID = String.valueOf(System.nanoTime());
			this.sender = sender;
			this.receiver = receiver;
			this.description = description;
			this.decision = decision;
			this.quarter = currentQuarter;
			
			this.date = timestamp;
		}
		public String getAppraisalID() {
			return appraisalID;
		}
		public void setAppraisalID(String appraisalID) {
			this.appraisalID = appraisalID;
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
		public String getDecision() {
			return decision;
		}
		public void setDecision(String decision) {
			this.decision = decision;
		}
		
		
}
