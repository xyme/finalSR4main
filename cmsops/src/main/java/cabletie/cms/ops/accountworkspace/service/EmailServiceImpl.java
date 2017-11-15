package cabletie.cms.ops.accountworkspace.service;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class EmailServiceImpl{
    
    @Autowired
    JavaMailSender sender;

    public void sendEmail(String recipient, String subject, String text) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo(recipient);
        helper.setText(text);
        helper.setSubject(subject);
        
        sender.send(message);
    }
}