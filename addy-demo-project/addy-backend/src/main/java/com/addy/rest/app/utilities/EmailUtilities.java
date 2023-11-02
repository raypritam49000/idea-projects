package com.addy.rest.app.utilities;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
;
import java.util.Random;

@Service
public class EmailUtilities {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailUtilities(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    public void sendOTPEmail(String to, String subject, String otpCode, String templateName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        // Create the Thymeleaf context and set variables
        Context context = new Context();
        context.setVariable("otpCode", otpCode);

        // Process the template
        String htmlContent = templateEngine.process(templateName, context);
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    public String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }


}
