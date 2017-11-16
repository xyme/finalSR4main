package cabletie.cms.ops.reporting;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.reportsDAO.TrainSvcLogDAO;
import cabletie.cms.ops.operationDBModel.reports.TrainSvcLog;

@Service
public class TrainSvcLogService {

	@Autowired
	TrainSvcLogDAO trainSvcLogDAO;
	
	
	public void createLog(String trainID, String driverID, Timestamp startTripTime) {
		
		TrainSvcLog serviceLog = new TrainSvcLog(trainID, driverID, startTripTime);
		
		trainSvcLogDAO.save(serviceLog);
		
	}
	
	public List<TrainSvcLog> getAllLogs (){
		
		return trainSvcLogDAO.findAll();
	}
	
	public TrainSvcLog getSvcLog(String logID){
		
		return trainSvcLogDAO.findBylogID(logID).get(0);
	}
}
