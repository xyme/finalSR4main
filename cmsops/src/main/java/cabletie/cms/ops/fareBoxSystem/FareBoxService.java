package cabletie.cms.ops.fareBoxSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.farebox.FareRatesDAO;
import cabletie.cms.ops.operationDBModel.farebox.FareRates;

/**
 * @author xY
 *
 */
@Service
public class FareBoxService {

	@Autowired
	FareRatesDAO fareRatesDAO;
	
	public List<FareRates> getFareRate() {
		
		return fareRatesDAO.findAll();
		
	}
	
	public void updateBaseFareRate(int fareType, double rate) {
		
		List<FareRates> fareList = getFareRate();
		
		FareRates fareRate = fareList.get(fareType);
		
		fareRate.setStationFare1(rate);
		
		fareRatesDAO.save(fareRate);
		
	}

}
