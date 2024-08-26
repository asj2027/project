package com.human.gallery.web.admin;

import com.human.gallery.domain.admin.AdminRepository;
import com.human.gallery.domain.admin.conUserDTO;
import com.human.gallery.domain.admin.iconUser;
import com.human.gallery.domain.paging.pageDTO;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConUserController {
    private final iconUser users;
    private final AdminRepository adminRepository;

    // 리스트 불러오기
    @RequestMapping("listuser")
    public String Listuser(@SessionAttribute(name = "user", required = false) Users user, Model model,
                           @ModelAttribute("paging") pageDTO paging) {
        model.addAttribute("user",user);
        model.addAttribute("paging", paging);
        int cnt= users.userCount(paging);
        paging.setTotalRowCount(cnt);
        paging.pageSetting();
        List<conUserDTO> listuser=users.listuser(paging);
        model.addAttribute("listuser", listuser);
        return "admin/conUser";
    }
}
