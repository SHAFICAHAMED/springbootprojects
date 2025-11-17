package com.example.EmailSendSb.service;

import com.example.EmailSendSb.Emailserviceinter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class emailservice implements Emailserviceinter {
    private final JavaMailSender mailSender;

    public emailservice(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
//    @Scheduled(fixedRate = 10000)
//    public  void summa()
//    {
//        sendEmail("heenaheena0706@gmail.com","hEEna nan than","hello");
//    }
//@Scheduled(cron = "0 * * * *  ?")//one minute ku oruthadava mail send pannum
//    public  void summa()
//    {
//        sendEmail("harishpadmanaban2005@gmail.com","Testing Mail","hello");
//    }
    @Override
   @Async
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shafickong@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);


        this.mailSender.send(simpleMailMessage);
    }
}
