package com.example.wishBirthDaySB.service;


import com.example.wishBirthDaySB.model.User;
import com.example.wishBirthDaySB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BirthdayScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Runs daily at 12:00 AM
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 * * * * *") // Runs every minute (for testing purposes)
    public void sendBirthdayWishes() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("Today's Date: " + today);

        // Fetch users with birthdays matching today
        List<User> birthdayUsers = userRepository.findByBirthdayMonthAndDay(month, day);

        if (birthdayUsers.isEmpty()) {
            System.out.println("No birthdays found for today.");
            return;
        }

        for (User user : birthdayUsers) {
            String subject = "Happy Birthday " + user.getName() + "❤";
            String body = "Dear " + user.getName() + ",\n\nWishing you a wonderful birthday filled with happiness and joy!\n\nippadiku,\nunudaya uyir thozhan \uD83E\uDEC2";
            emailService.sendEmail(user.getEmail(), subject, body);
            System.out.println("Birthday email sent to: " + user.getEmail());
        }
    }
}