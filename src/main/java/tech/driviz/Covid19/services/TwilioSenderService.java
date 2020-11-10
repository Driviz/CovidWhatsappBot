package tech.driviz.Covid19.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSenderService {
	
	@Value("${twilio.SID}")
	private String SID;
	
	@Value("${twilio.token}")
	private String token;
	
	@Value("${sender.phone}")
	private String sender;
	
	@Value("${reciever.phone}")
	private String reciever;
	
	public String sendMessage(String messageString) {
		Twilio.init(SID, token); 
        Message message = Message.creator( 
                new PhoneNumber(reciever), 
                new PhoneNumber(sender),  
                messageString)      
            .create(); 
        return message.getSid();
	}
	
}
