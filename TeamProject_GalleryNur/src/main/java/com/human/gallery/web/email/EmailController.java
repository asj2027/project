package com.human.gallery.web.email;

import com.human.gallery.domain.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/findMyId/Email")
    public String sendEmail(@RequestParam("email") String email) throws Exception {
        emailService.sendSimpleMessage(email);

        return "true";
    }

    @PostMapping("/findMyId/Email/code")
    public String checkCode(@RequestParam("code") String code) {
        if (EmailService.ePw.equals(code)) {
            return "true";
        } else {
            return "false";
        }
    }
}
