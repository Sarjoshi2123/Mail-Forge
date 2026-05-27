package com.email.email_writer.controller;

import com.email.email_writer.Model.Email;
import com.email.email_writer.Service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = {"${cors.allowed-origins:*}", "http://localhost:5173", "http://localhost:3000"})
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody Email email)
    {
        String response = emailService.generateEmailReply(email);
        return ResponseEntity.ok(response);
    }

}
