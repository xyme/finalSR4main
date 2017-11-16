package cabletie.cms.ops.reporting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.reportsDAO.ReportObjDAO;
import cabletie.cms.ops.operationDBModel.reports.ReportObj;

@Service
public class ReportService {
	

	@Autowired
	ReportObjDAO reportObjDAO;
	
	public ReportObj getReport(String reportID) {
		
		return reportObjDAO.findByreportID(reportID).get(0);
		
	}
	
	public List<ReportObj> getAllReport(String reportID){
		
		return reportObjDAO.findAll();
	}
	
	public void createReport(String reportType, String submittedBy, String reportingStatus, String description,
			String shiftTime, String trainID, String tripID) {
		
		ReportObj report = new ReportObj(reportType, submittedBy, reportingStatus, description,
			shiftTime, trainID, tripID);
		
		reportObjDAO.save(report);
	}
}
