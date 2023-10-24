package com.example.twitter.Service;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.services.gmail.Gmail;
import com.sun.mail.iap.ByteArray;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class MailService {
    private final Gmail gmail;
    @Autowired
    public MailService(Gmail gmail){
        this.gmail = gmail;
    }

    public void sendGmail(String toAddress, String subject, String content){
        Properties props=new Properties();
        Session session=Session.getInstance(props,null);
        MimeMessage email=new MimeMessage(session);
        try{
            email.setFrom(new InternetAddress("rohitguttula7@gmail.com"));
            email.setRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));
            email.setText(content);
            email.setSubject(subject);
            ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
            email.writeTo(byteArray);
            byte[] rawContent=byteArray.toByteArray();
            String encodedEmail= Base64.encodeBase64URLSafeString(rawContent);
            com.google.api.services.gmail.model.Message message=new com.google.api.services.gmail.model.Message();
            message.setRaw(encodedEmail);
            message=gmail.users().messages().send("me",message).execute();
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
