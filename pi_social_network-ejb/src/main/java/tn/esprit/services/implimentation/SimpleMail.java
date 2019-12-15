	package tn.esprit.services.implimentation;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SimpleMail {

	public static void sendEmailWithAttachments(String login,String password, String toAddress,String subject, String message)throws AddressException, MessagingException {
		//allow access before from
		//https://l.facebook.com/l.php?u=https%3A%2F%2Fmyaccount.google.com%2Flesssecureapps%3Ffbclid%3DIwAR2-Dr9KqtIPz7EQEDM1vJ4fgatHCl7yVNrwlE24xbMeUN7P6dQQ3JIDlfw&h=AT0DIe1V2dnvLRVJCD2o5xa_9-AxX3t8ILESecTOQ3CCaZHcy4ASFIe4f1On9-26GDdtfNRHdrmmvwEU1tGRbhcRl-KvWYJX-ekr0um_id3vZ_ZCv0zFilp3e5u61qfGUGWxZw
	      Properties props = System.getProperties();
	              // -- Attaching to default Session, or we could start a new one --
	              props.put("mail.transport.protocol", "smtp" );
	              props.put("mail.smtp.starttls.enable","true" );
	              props.put("mail.imap.ssl.enable", "true");
	              props.put("mail.smtp.socketFactory.port", "587");    
	                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	              props.put("mail.smtp.host","smtp.gmail.com");
	              props.put("mail.smtp.auth", "true" );
	              props.put("mail.user", login);
	              props.put("mail.password", password);
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(login, password);
	            }
	        };
	        Session session = Session.getInstance(props, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(login));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	 
	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	    
	 
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 

	/**
	 * Test sending e-mail with attachments
	 */
	public static void main(String[] args) {
	 
	    
	    // SMTP info
	  //  String host = "smtp.gmail.com";
	   // String port = "587";
	    String mailFrom = 	"ahmed.benmbarek96@gmail.com" ;
	    String password = "22148971";

	    // message info
	    String mailTo = "ahmed.benmbarek@esprit.tn";
	    String subject = "New email with attachments";
	    String message = "félicitation, vous avez payer pour profiter notre plateform\n"
	    		+ "vous trouverez une version numérique de votre facture de paiement en cliquant sur le lien ci-joint\n"
	    		+ "http://localhost:9080/pi_social_network-web/rest/payement/send_bill";

	 
	    try {
	        sendEmailWithAttachments( mailFrom, password, mailTo,subject, message);
	    	GenererFichePDF genererPDF = new GenererFichePDF();
	    	//System.out.println(genererPDF.pdf());
	        System.out.println("Email sent.");
	    } catch (Exception ex) {
	        System.out.println("Could not send email.");
	        ex.printStackTrace();
	    }
	    
		    }
}
