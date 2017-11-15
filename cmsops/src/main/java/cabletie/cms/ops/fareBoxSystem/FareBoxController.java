package cabletie.cms.ops.fareBoxSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cabletie.cms.ops.operationDBModel.farebox.FareRates;

@Controller
@SessionAttributes("name")
public class FareBoxController {

	@Autowired
	FareBoxService fareBoxSvc;
	
	@GetMapping("/farebox-farerates")
    public String viewFareRates(Model model) {
		
		List<FareRates> fareList = fareBoxSvc.getFareRate();
		
		model.addAttribute("fareRates", fareList);
		
		return "farebox-farerates";
	}
	
	@PostMapping("/farebox-farerates")
    public String editFareRates(@RequestParam int fareType, @RequestParam double fareRate,  Model model) {
		
		fareBoxSvc.updateBaseFareRate(fareType, fareRate);
		
		return "redirect:/farebox-farerates";
	}
	
	
}
