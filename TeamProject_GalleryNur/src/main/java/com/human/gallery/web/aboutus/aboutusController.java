package com.human.gallery.web.aboutus;

import com.human.gallery.domain.user.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class aboutusController {
    @GetMapping("/visit")
    public String viewVisit(@SessionAttribute(name = "user", required = false) Users user, Model model)
    {
        model.addAttribute("user", user);
        return "aboutus/visit";
    }

    @GetMapping("/letter")
    public String viewLetter(@SessionAttribute(name = "user", required = false) Users user, Model model)
    {
        model.addAttribute("user", user);
        return "aboutus/letter";
    }
}
