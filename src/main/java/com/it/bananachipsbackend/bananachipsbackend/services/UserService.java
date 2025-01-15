package com.it.bananachipsbackend.bananachipsbackend.services;

import java.util.HashMap;
import java.util.Map;

import com.it.bananachipsbackend.bananachipsbackend.entity.UserEntity;
import com.it.bananachipsbackend.bananachipsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(7);

    public UserEntity registerUser(UserEntity userEntity) {
        if (userEntity.getName() == null || userEntity.getName().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty!");
        }
        if (userEntity.getPhoneNumber() == null || userEntity.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number can't be empty!");
        }
        if (userEntity.getEmail() == null || userEntity.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email can't be empty!");
        }
        if (userEntity.getPassword() == null || userEntity.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password can't be empty!");
        }

        String subject = "Welcome to Banana Chips 🍌🥔!";
        String body = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "h1 { color: #333; }"
                + "p { font-size: 16px; color: #555; }"
                + "b { color: #FFA500; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h1>Dear " + userEntity.getName() + ",</h1>"
                + "<p>Thank you for registering with us.</p>"
                + "<p>We're thrilled to welcome you to the Banana Chips community!</p>"
                + "<p><b>Enjoy our amazing services and benefits.</b></p>"
                + "</body>"
                + "</html>";

        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        String email = userEntity.getEmail();
        emailService.sendEmail(email, subject, body);
        return userRepository.save(userEntity);
    }

    public Map<String, String> loginUser(UserEntity userEntity) {
        UserEntity existUser = userRepository.findByEmail(userEntity.getEmail());

        if (existUser == null) {
            throw new IllegalArgumentException("Email does not exist!");
        }

        boolean checkPassword = encoder.matches(userEntity.getPassword(), existUser.getPassword());
        if (!checkPassword) {
            throw new IllegalArgumentException("Invalid password!");
        }

        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("data", "1");
        loginResponse.put("message", "Login successfully.");

        return loginResponse;
    }

}
