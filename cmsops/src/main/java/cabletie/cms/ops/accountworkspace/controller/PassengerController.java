package cabletie.cms.ops.accountworkspace.controller;


import cabletie.cms.ops.accountworkspace.service.PassengerService;
import cabletie.cms.ops.corporateDBDao.FeedbackBody;
import cabletie.cms.ops.corporateDBDao.FeedbackDAO;
import cabletie.cms.ops.corporateDBDao.PassengerDAO;
import cabletie.cms.ops.corporateDBDao.QRDAO;
import cabletie.cms.ops.corporateDBDao.ReceiveMessageDAO;
import cabletie.cms.ops.corporateDBDao.cardDAO;
import cabletie.cms.ops.corporateDBModel.Feedback;
import cabletie.cms.ops.corporateDBModel.Passenger;
import cabletie.cms.ops.corporateDBModel.QR;
import cabletie.cms.ops.corporateDBModel.ReceiveMessage;
import cabletie.cms.ops.corporateDBModel.SendMessage;
import cabletie.cms.ops.corporateDBModel.card;
import cabletie.cms.ops.operationDBDao.maintenance.DefectReportDAO;
import cabletie.cms.ops.operationDBModel.maintenance.DefectReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PassengerController {

    @Autowired
    PassengerDAO passengerDAO;
    @Autowired
    FeedbackDAO feedbackDAO;
    @Autowired
    cardDAO cardD;
    @Autowired
    QRDAO qrDAO;
    @Autowired
    ReceiveMessageDAO receiveDAO;
    @Autowired
    DefectReportDAO defectreport;
    
    PassengerService passengerService;
    // -------------------Retrieve All Users---------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Passenger>> listAllUsers() {
        List<Passenger> users = passengerDAO.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Passenger>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Passenger> getUser(@PathVariable("id") String id) {

        List<Passenger> passenger = passengerDAO.findByemail(id);
        if (passenger.isEmpty()) {

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Passenger>(passenger.get(0), HttpStatus.OK);
    }
//
//    // -------------------Create a User and user points-------------------------------------------

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public void createUser( @RequestBody Passenger passenger, UriComponentsBuilder ucBuilder) {
    	
     
        passengerDAO.save(passenger);
        //passengerDAO.save(pass);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<String>(headers, HttpStatus.CREATED
    }
//
//    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(@RequestBody Passenger passenger) {

      
        	
        passengerDAO.save(passenger);
//        currentUser.setName(user.getName());
//        currentUser.setAge(user.getAge());
//        currentUser.setSalary(user.getSalary());
//
    }
//
//    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public void deleteUser(@PathVariable("id") String id) {

        List<Passenger> passenger = passengerDAO.findByemail(id);
     
        passengerDAO.delete(passenger.get(0));

    }

    //    // ------------------- Update the card amount -----------------------------
//    @RequestMapping(value = "/updateCard", method = RequestMethod.POST)
//    public ResponseEntity<Passenger> updateUser(@PathVariable("id") long id, @RequestBody Passenger passenger) {
//
//        Passenger currentUser = passenger;
//
//        passengerDAO.save(currentUser);
////        currentUser.setName(user.getName());
////        currentUser.setAge(user.getAge());
////        currentUser.setSalary(user.getSalary());
////
////        userService.updateUser(currentUser);
//        return new ResponseEntity<Passenger>(currentUser, HttpStatus.OK);
//    }

//  // -------------------Create a feedback entry-------------------------------------------

  @RequestMapping(value = "/submitFeedback", method = RequestMethod.POST)
  public void submitFeedback( @RequestBody FeedbackBody fbb, UriComponentsBuilder ucBuilder) {
  		Feedback fb = new Feedback(fbb.getEmail(), fbb.getName(), fbb.getFeedback());
      feedbackDAO.save(fb);
      //passengerDAO.save(pass);
//      HttpHeaders headers = new HttpHeaders();
//      headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//      return new ResponseEntity<String>(headers, HttpStatus.CREATED
  }
  
//// -------------------retrieve all related feedback entry-------------------------------------------

@RequestMapping(value = "/getFeedback/{id}", method = RequestMethod.GET)
public ResponseEntity<List<Feedback>> retrieveFeedback( @PathVariable("id") String email) {
	
 
    List<Feedback> fb = feedbackDAO.findByemail(email);
    return new ResponseEntity<List<Feedback>>(fb, HttpStatus.OK);
}


//// -------------------update card amount-------------------------------------------

@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
public void updateCard( @RequestBody card updateCard, UriComponentsBuilder ucBuilder) {
	
  cardD.save(updateCard);
  //passengerDAO.save(pass);
//  HttpHeaders headers = new HttpHeaders();
//  headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//  return new ResponseEntity<String>(headers, HttpStatus.CREATED
}


//// -------------------retreive a card-------------------------------------------

@RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
public ResponseEntity<card> retrieveCard(@PathVariable("id") String cardnum) {

	List<card> card = cardD.findBycardnum(cardnum);
    return new ResponseEntity<card>(card.get(0), HttpStatus.OK);
  //passengerDAO.save(pass);
//  HttpHeaders headers = new HttpHeaders();
//  headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//  return new ResponseEntity<String>(headers, HttpStatus.CREATED
}

////-----------------retrieve a QR code -----------
@RequestMapping(value = "/checkQRPoint/{id}", method = RequestMethod.GET)
public ResponseEntity<QR> retrieveQR(@PathVariable("id") String QRCODE){
	List<QR> qr = qrDAO.findByqrcode(QRCODE);
	return new ResponseEntity<QR>(qr.get(0), HttpStatus.OK);
}

///-----------------Update QR code scanned --------------------------
@RequestMapping(value = "/updateQR", method = RequestMethod.POST)
public void updateQR(@RequestBody QR qr) {
	qrDAO.save(qr);
}

//-------------------retrieve external announcement ------------------
@RequestMapping(value = "/getNotification", method = RequestMethod.GET)
public ResponseEntity<String> retrieveMessage(){
	List<ReceiveMessage> receivedMessage = receiveDAO.findByuserID("allPassenger");
	String status = "";
	if(!receivedMessage.isEmpty()) {
		SendMessage m = receivedMessage.get(receivedMessage.size()- 1).getSendmsg();
		status = m.getDescription();
	}
	
	return new ResponseEntity<String>(status, HttpStatus.OK);

	}


//// -------------------Create a defect entry-------------------------------------------

@RequestMapping(value = "/submitDefect", method = RequestMethod.POST)
public void submitDefect (@RequestBody DefectReport fbb, UriComponentsBuilder ucBuilder) {
	//find the number of defect
	List<DefectReport> all = defectreport.findAll();
	int length = all.size() + 1;
	String size = "D" + length;
	
	DefectReport fb = new DefectReport(size, fbb.getDateReport(), fbb.getDescription(), fbb.getLocation(), fbb.getEmail());
	defectreport.save(fb);
  //passengerDAO.save(pass);
//  HttpHeaders headers = new HttpHeaders();
//  headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//  return new ResponseEntity<String>(headers, HttpStatus.CREATED
}

////-------------------retrieve all related defect entry-------------------------------------------

@RequestMapping(value = "/getDefect/{id}", method = RequestMethod.GET)
public ResponseEntity<List<DefectReport>> retrieveDefect( @PathVariable("id") String email) {

List<DefectReport> fb = defectreport.findByEmail(email);
return new ResponseEntity<List<DefectReport>>(fb, HttpStatus.OK);
}


}