package com.it.bananachipsbackend.bananachipsbackend.controller;

import java.util.HashMap;
import java.util.Map;

import com.it.bananachipsbackend.bananachipsbackend.entity.UserEntity;
import com.it.bananachipsbackend.bananachipsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://webbanana-eight.vercel.app", "http://localhost:3000"}, allowCredentials = "true")
@RequestMapping(value = "/banana/app")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> registerUser(
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        UserEntity userEntity = new UserEntity(name, phoneNumber, email, password);
        userService.registerUser(userEntity);

        Map<String, String> signUpResponse = new HashMap<>();
        signUpResponse.put("message", "User registered successfully.");
        signUpResponse.put("data", "1");

        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        UserEntity userEntity = new UserEntity(email, password);
        Map<String, String> loginResponse = userService.loginUser(userEntity);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
