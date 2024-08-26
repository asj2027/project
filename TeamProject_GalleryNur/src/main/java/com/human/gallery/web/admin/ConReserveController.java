package com.human.gallery.web.admin;

import com.human.gallery.domain.admin.iconReserve;
import com.human.gallery.domain.admin.conReserveDTO;
import com.human.gallery.domain.paging.pageDTO;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConReserveController {
    private final iconReserve reserve;

    // 리스트 불러오기
    @RequestMapping("listReserve")
    public String ListReserve(@SessionAttribute(name = "user", required = false) Users user, Model model,
                              @ModelAttribute("paging") pageDTO paging,
                              @RequestParam(required = false, defaultValue = "") String keyword) {
        model.addAttribute("user", user);
        model.addAttribute("paging", paging);
        paging.setKeyword(keyword);
        int cnt = reserve.reserveCount(paging);
        paging.setTotalRowCount(cnt);
        paging.pageSetting();
        List<conReserveDTO> listReserve = reserve.listReserve(paging);
        model.addAttribute("listReserve", listReserve);
        return "admin/conReserve";
    }

    @RequestMapping("/listReserve/resdel")
    public String ResDel(@RequestParam String orderId) {
        reserve.payDelete(orderId);
        reserve.reserveDelete(orderId);
        return "redirect:/listReserve";
    }
}
