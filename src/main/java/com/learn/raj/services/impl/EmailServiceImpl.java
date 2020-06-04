package com.learn.raj.services.impl;

import com.learn.raj.requests.EmailRequest;
import com.learn.raj.services.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailServiceImpl implements EmailService {
    @Override
    public Response sendEmail(EmailRequest emailRequest) throws IOException{

        Email from = new Email(emailRequest.getFrom());
        Email to = new Email(emailRequest.getTo());
        Content content = new Content( "text/plain", emailRequest.getContent());

        Mail mail = new Mail(from, emailRequest.getSubject(), to, content);
        SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            return response;
        }
        catch (IOException e){
            throw e;
        }
    }
}
