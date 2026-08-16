package com.example.hospital.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j   // 📝 লগিং করার জন্য (System.out.println এর smart version)
@Service // 🏷️ Spring-কে বলছি: এটা একটা service bean, memory-তে রেখে দাও
public class EmailNotificationService implements NotificationService {

    @Override  // 🔄 Interface-এর contract পূরণ করছি
    public void send(String to, String message) {
        // 🎭 আসল email পাঠানোর কোড এখনো লিখিনি।
        // আপাতত শুধু simulate করছি (console-এ print করছি)।
        log.info("📧 EMAIL SENT to {}: {}", to, message);
        
        // ভবিষ্যতে এখানে JavaMailSender দিয়ে real email পাঠানোর কোড বসবে
    }
}