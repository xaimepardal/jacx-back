package com.apm.jacx.controller;

import com.apm.jacx.email.EmailDetails;
import com.apm.jacx.model.AppUser;
import com.apm.jacx.service.AppUserService;
import com.apm.jacx.service.EmailService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppUserService appUserService;

    // Sending a simple Email
    @PostMapping("/resetPassword/{username}")
    public ResponseEntity<Void> sendMail(@PathVariable("username") String username) {
        AppUser user = appUserService.findByUsername(username);

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        String password = Arrays.toString(bytes).replaceAll(", |\\[|\\]|\\-", "");

        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        user.setPassword(sha256hex);

        EmailDetails details = new EmailDetails();
        details.setRecipient(user.getEmail());
        details.setSubject("JACX APP: Recuperación contraseña");
        details.setMsgBody("Hola " + user.getUsername() + "\nSu nueva contraseña es: " + password + "\nCambiela cuanto antes\nSalu2 ;-)");

        emailService.sendSimpleMail(details);
        appUserService.update(user);

        return ResponseEntity.ok().build();

    }
}
