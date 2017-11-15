package cabletie.cms.ops.operationDBModel.assets;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssetLog {

		@Id
		private long logID;
		private String assetID;
		private Timestamp modDate;
		private String modBy;
		private int modType; // -1 Decom | 0 - Warranty Date | 1 Dep Period | 3 Location Tag | 2 Newly create 
		private String modValue;
		
		public AssetLog() {
			
			
		}
		public AssetLog(String assetID,  String modBy, int change, String value) {
			
			super();
			
			int randomNum = ThreadLocalRandom.current().nextInt(1, 999999999 + 1);
			this.logID = Long.valueOf(randomNum);
			
			this.assetID = assetID;
				
			//Set current time 
			Date date = new Date();
			long time = date.getTime();
			this.modDate = new Timestamp(time);
			
			this.modBy = modBy;
			this.modType = change;
			this.modValue = value;
			
		}
		

		
		public long getLogID() {
			return logID;
		}
		public void setLogID(long logID) {
			this.logID = logID;
		}
		public String getAssetID() {
			return assetID;
		}
		public void setAssetID(String assetID) {
			this.assetID = assetID;
		}

		public Timestamp getModDate() {
			return modDate;
		}
		public void setModDate(Timestamp modDate) {
			this.modDate = modDate;
		}
		public String getModBy() {
			return modBy;
		}
		public void setModBy(String modBy) {
			this.modBy = modBy;
		}
		public int getModType() {
			return modType;
		}
		public void setModType(int modType) {
			this.modType = modType;
		}
		public String getModValue() {
			return modValue;
		}
		public void setModValue(String modValue) {
			this.modValue = modValue;
		}

		
		
		

}
