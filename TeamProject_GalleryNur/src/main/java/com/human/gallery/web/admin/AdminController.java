package com.human.gallery.web.admin;

import com.human.gallery.domain.admin.AdminRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminRepository adminRepository;

    @GetMapping("/reason")
    public String viewReason(@SessionAttribute(name = "user", required = false) Users user, Model model) {
        model.addAttribute("user",user);
        return "admin/reason";
    }
    @PostMapping("/reason")
    @ResponseBody
    public Object returnReason() {
        return adminRepository.withDrawalReason();
    }

    @GetMapping("/management")
    public String viewManage(@SessionAttribute(name = "user", required = false) Users user, Model model) {
        model.addAttribute("user",user);
        return "admin/manageGallery";
    }

    @PostMapping("/management/audience")
    @ResponseBody
    public Object returnAudience() {
        return adminRepository.findPeopleForNow();
    }
    @PostMapping("/management/nowsale")
    @ResponseBody
    public Object returnSale() {
        return adminRepository.findSalesForNow();
    }
    @PostMapping("/management/saleforyear")
    @ResponseBody
    public Object returnSaleForYear() {
        return adminRepository.findSalesForYear();
    }
}
