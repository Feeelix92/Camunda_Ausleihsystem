package softwarearchitektur;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.*;



public class MailService {

    private static final String HOST = "smtp.googlemail.com";
    private static final String USER = "email.camunda@gmail.com";
	private static final String PWD = "camunda123";
    private static final Integer PORT = 587;

    public void sendEmail(String to, String subject, String content) throws EmailException {
        
        try {
        	HtmlEmail email = new HtmlEmail();
			email.setHostName(HOST);
			email.setAuthentication(USER, PWD);
	        email.setSmtpPort(PORT);
	        email.setDebug(true);
	        email.setSSLOnConnect(true);
	        email.setHtmlMsg(content);
	        email.setCharset("utf-8");

	        // Just in case HTML is unsupported
	        email.setTextMsg("Dein E-Mail-Client unterstützt kein HTML.");
	        email.addTo(to);
	        email.setFrom(USER);
	        email.setSubject(subject);
	        email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
        
    }
}
