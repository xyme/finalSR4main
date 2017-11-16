package cabletie.cms.ops.simulator;

import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabletie.cms.ops.operationDBDao.simulator.NS8wkendPHBlockDAO;
import cabletie.cms.ops.operationDBModel.simulator.NS8wkendPHBlock;

@Service
public class SimulatorService {

	@Autowired
	NS8wkendPHBlockDAO ns8wkendPHBlockDAO;
	
	
	//Get the row (Route)
	public NS8wkendPHBlock getRoute(String routeID) {
		
		return ns8wkendPHBlockDAO.findByrouteID(routeID).get(0);
		
	}
	
	//Get all occupied Blocks
	public ArrayList<Integer> generateBlock(String simTime) {
		
		LocalTime simulatedTime = LocalTime.parse(simTime);
		
		ArrayList<Integer> occupiedBlocks = new ArrayList<Integer>();
		
		//OPTIMISED!
		//OFFSET backwards to startsearching -30mins
		LocalTime startSearchTime = LocalTime.parse(simTime).minusMinutes(29);
		
		//Start index
		int startSearchRoute = getStartSearchRoute(startSearchTime);
	
		
		int counter = startSearchRoute;
		
		//Hardcoded to search for the 4 trains 
		for(int i=0; i<4; i++) {
			
			if (counter>150) {
				break;
			}
			
			ArrayList<String> routeBlocks = formatRoute(getRoute(String.valueOf(counter)));
			
			for(int j=0;j<209;j++) {
				
				LocalTime blockTimeBefore = LocalTime.parse(routeBlocks.get(j));
				LocalTime blockTimeAfter = LocalTime.parse(routeBlocks.get(j+1));
				
				if(simulatedTime.isAfter(blockTimeBefore) && simulatedTime.isBefore(blockTimeAfter) || simulatedTime.equals(blockTimeBefore)){
					
					occupiedBlocks.add(j);
					
				}
			}
			
			counter++;
			
		}
		
		return occupiedBlocks;
		
	}
	
	
	//Get the first row to start searching for BlockTimes
	public int getStartSearchRoute (LocalTime startSearchTime) {
		
		//Find first route with searchTime
		
		int startSearchRoute = 0;
		
		for(int i=0; i<=150; i++) {
			
			LocalTime blockTime = LocalTime.parse(getRoute(String.valueOf(i)).getBlock0());
			
			if(blockTime.isAfter(startSearchTime)) {
				
				startSearchRoute = i;
				
				break;
				
			}
			
		}
		
		return startSearchRoute;
			
	}
	
	
	
	
	//Convert A Route Blocks times into corresponding index on an ArrayList
	
	public ArrayList<String> formatRoute(NS8wkendPHBlock route){
		
		ArrayList<String> blockTimes = new ArrayList<String>(209);
		
		blockTimes.add(route.getBlock0());
		blockTimes.add(route.getBlock1());
		blockTimes.add(route.getBlock2());
		blockTimes.add(route.getBlock3());
		blockTimes.add(route.getBlock4());
		blockTimes.add(route.getBlock5());
		blockTimes.add(route.getBlock6());
		blockTimes.add(route.getBlock7());
		blockTimes.add(route.getBlock8());
		blockTimes.add(route.getBlock9());
		blockTimes.add(route.getBlock10());
		blockTimes.add(route.getBlock11());
		blockTimes.add(route.getBlock12());
		blockTimes.add(route.getBlock13());
		blockTimes.add(route.getBlock14());
		blockTimes.add(route.getBlock15());
		blockTimes.add(route.getBlock16());
		blockTimes.add(route.getBlock17());
		blockTimes.add(route.getBlock18());
		blockTimes.add(route.getBlock19());
		blockTimes.add(route.getBlock20());
		blockTimes.add(route.getBlock21());
		blockTimes.add(route.getBlock22());
		blockTimes.add(route.getBlock23());
		blockTimes.add(route.getBlock24());
		blockTimes.add(route.getBlock25());
		blockTimes.add(route.getBlock26());
		blockTimes.add(route.getBlock27());
		blockTimes.add(route.getBlock28());
		blockTimes.add(route.getBlock29());
		blockTimes.add(route.getBlock30());
		blockTimes.add(route.getBlock31());
		blockTimes.add(route.getBlock32());
		blockTimes.add(route.getBlock33());
		blockTimes.add(route.getBlock34());
		blockTimes.add(route.getBlock35());
		blockTimes.add(route.getBlock36());
		blockTimes.add(route.getBlock37());
		blockTimes.add(route.getBlock38());
		blockTimes.add(route.getBlock39());
		blockTimes.add(route.getBlock40());
		blockTimes.add(route.getBlock41());
		blockTimes.add(route.getBlock42());
		blockTimes.add(route.getBlock43());
		blockTimes.add(route.getBlock44());
		blockTimes.add(route.getBlock45());
		blockTimes.add(route.getBlock46());
		blockTimes.add(route.getBlock47());
		blockTimes.add(route.getBlock48());
		blockTimes.add(route.getBlock49());
		blockTimes.add(route.getBlock50());
		blockTimes.add(route.getBlock51());
		blockTimes.add(route.getBlock52());
		blockTimes.add(route.getBlock53());
		blockTimes.add(route.getBlock54());
		blockTimes.add(route.getBlock55());
		blockTimes.add(route.getBlock56());
		blockTimes.add(route.getBlock57());
		blockTimes.add(route.getBlock58());
		blockTimes.add(route.getBlock59());
		blockTimes.add(route.getBlock60());
		blockTimes.add(route.getBlock61());
		blockTimes.add(route.getBlock62());
		blockTimes.add(route.getBlock63());
		blockTimes.add(route.getBlock64());
		blockTimes.add(route.getBlock65());
		blockTimes.add(route.getBlock66());
		blockTimes.add(route.getBlock67());
		blockTimes.add(route.getBlock68());
		blockTimes.add(route.getBlock69());
		blockTimes.add(route.getBlock70());
		blockTimes.add(route.getBlock71());
		blockTimes.add(route.getBlock72());
		blockTimes.add(route.getBlock73());
		blockTimes.add(route.getBlock74());
		blockTimes.add(route.getBlock75());
		blockTimes.add(route.getBlock76());
		blockTimes.add(route.getBlock77());
		blockTimes.add(route.getBlock78());
		blockTimes.add(route.getBlock79());
		blockTimes.add(route.getBlock80());
		blockTimes.add(route.getBlock81());
		blockTimes.add(route.getBlock82());
		blockTimes.add(route.getBlock83());
		blockTimes.add(route.getBlock84());
		blockTimes.add(route.getBlock85());
		blockTimes.add(route.getBlock86());
		blockTimes.add(route.getBlock87());
		blockTimes.add(route.getBlock88());
		blockTimes.add(route.getBlock89());
		blockTimes.add(route.getBlock90());
		blockTimes.add(route.getBlock91());
		blockTimes.add(route.getBlock92());
		blockTimes.add(route.getBlock93());
		blockTimes.add(route.getBlock94());
		blockTimes.add(route.getBlock95());
		blockTimes.add(route.getBlock96());
		blockTimes.add(route.getBlock97());
		blockTimes.add(route.getBlock98());
		blockTimes.add(route.getBlock99());
		blockTimes.add(route.getBlock100());
		blockTimes.add(route.getBlock101());
		blockTimes.add(route.getBlock102());
		blockTimes.add(route.getBlock103());
		blockTimes.add(route.getBlock104());
		blockTimes.add(route.getBlock105());
		blockTimes.add(route.getBlock106());
		blockTimes.add(route.getBlock107());
		blockTimes.add(route.getBlock108());
		blockTimes.add(route.getBlock109());
		blockTimes.add(route.getBlock110());
		blockTimes.add(route.getBlock111());
		blockTimes.add(route.getBlock112());
		blockTimes.add(route.getBlock113());
		blockTimes.add(route.getBlock114());
		blockTimes.add(route.getBlock115());
		blockTimes.add(route.getBlock116());
		blockTimes.add(route.getBlock117());
		blockTimes.add(route.getBlock118());
		blockTimes.add(route.getBlock119());
		blockTimes.add(route.getBlock120());
		blockTimes.add(route.getBlock121());
		blockTimes.add(route.getBlock122());
		blockTimes.add(route.getBlock123());
		blockTimes.add(route.getBlock124());
		blockTimes.add(route.getBlock125());
		blockTimes.add(route.getBlock126());
		blockTimes.add(route.getBlock127());
		blockTimes.add(route.getBlock128());
		blockTimes.add(route.getBlock129());
		blockTimes.add(route.getBlock130());
		blockTimes.add(route.getBlock131());
		blockTimes.add(route.getBlock132());
		blockTimes.add(route.getBlock133());
		blockTimes.add(route.getBlock134());
		blockTimes.add(route.getBlock135());
		blockTimes.add(route.getBlock136());
		blockTimes.add(route.getBlock137());
		blockTimes.add(route.getBlock138());
		blockTimes.add(route.getBlock139());
		blockTimes.add(route.getBlock140());
		blockTimes.add(route.getBlock141());
		blockTimes.add(route.getBlock142());
		blockTimes.add(route.getBlock143());
		blockTimes.add(route.getBlock144());
		blockTimes.add(route.getBlock145());
		blockTimes.add(route.getBlock146());
		blockTimes.add(route.getBlock147());
		blockTimes.add(route.getBlock148());
		blockTimes.add(route.getBlock149());
		blockTimes.add(route.getBlock150());
		blockTimes.add(route.getBlock151());
		blockTimes.add(route.getBlock152());
		blockTimes.add(route.getBlock153());
		blockTimes.add(route.getBlock154());
		blockTimes.add(route.getBlock155());
		blockTimes.add(route.getBlock156());
		blockTimes.add(route.getBlock157());
		blockTimes.add(route.getBlock158());
		blockTimes.add(route.getBlock159());
		blockTimes.add(route.getBlock160());
		blockTimes.add(route.getBlock161());
		blockTimes.add(route.getBlock162());
		blockTimes.add(route.getBlock163());
		blockTimes.add(route.getBlock164());
		blockTimes.add(route.getBlock165());
		blockTimes.add(route.getBlock166());
		blockTimes.add(route.getBlock167());
		blockTimes.add(route.getBlock168());
		blockTimes.add(route.getBlock169());
		blockTimes.add(route.getBlock170());
		blockTimes.add(route.getBlock171());
		blockTimes.add(route.getBlock172());
		blockTimes.add(route.getBlock173());
		blockTimes.add(route.getBlock174());
		blockTimes.add(route.getBlock175());
		blockTimes.add(route.getBlock176());
		blockTimes.add(route.getBlock177());
		blockTimes.add(route.getBlock178());
		blockTimes.add(route.getBlock179());
		blockTimes.add(route.getBlock180());
		blockTimes.add(route.getBlock181());
		blockTimes.add(route.getBlock182());
		blockTimes.add(route.getBlock183());
		blockTimes.add(route.getBlock184());
		blockTimes.add(route.getBlock185());
		blockTimes.add(route.getBlock186());
		blockTimes.add(route.getBlock187());
		blockTimes.add(route.getBlock188());
		blockTimes.add(route.getBlock189());
		blockTimes.add(route.getBlock190());
		blockTimes.add(route.getBlock191());
		blockTimes.add(route.getBlock192());
		blockTimes.add(route.getBlock193());
		blockTimes.add(route.getBlock194());
		blockTimes.add(route.getBlock195());
		blockTimes.add(route.getBlock196());
		blockTimes.add(route.getBlock197());
		blockTimes.add(route.getBlock198());
		blockTimes.add(route.getBlock199());
		blockTimes.add(route.getBlock200());
		blockTimes.add(route.getBlock201());
		blockTimes.add(route.getBlock202());
		blockTimes.add(route.getBlock203());
		blockTimes.add(route.getBlock204());
		blockTimes.add(route.getBlock205());
		blockTimes.add(route.getBlock206());
		blockTimes.add(route.getBlock207());
		blockTimes.add(route.getBlock208());
		blockTimes.add(route.getBlock209());
		
		
		return blockTimes;

	}
	
}
