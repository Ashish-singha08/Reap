package com.iiitb.spe.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iiitb.spe.model.entities.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service("mailService")
public class MailService {

    @Autowired
    JavaMailSender mailSender;



    public String sendEmail(MailEntity mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "Reap.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());
            return "Email Sent!!";

        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error!!";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Error!!";
        }
    }

}
