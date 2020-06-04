package com.learn.raj.services;

import com.learn.raj.requests.EmailRequest;
import com.sendgrid.Response;

import java.io.IOException;

public interface EmailService {
    Response sendEmail(EmailRequest emailRequest) throws IOException;
}
