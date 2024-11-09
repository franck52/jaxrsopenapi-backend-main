package fr.istic.taa.jaxrs.rest;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.istic.jpa.Email;

@Path("/send-email")
public class EmailResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(Email email) {

        String smtpHost = "smtp.sendgrid.net";
        int smtpPort = 587;
        String smtpUsername = "apikey";
        String smtpPassword = "YOUR_SENDGRID_API_KEY";

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props);
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpUsername, smtpPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return Response.ok().build();

        } catch (MessagingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

