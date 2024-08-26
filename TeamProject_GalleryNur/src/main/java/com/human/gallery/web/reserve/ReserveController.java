package com.human.gallery.web.reserve;

import com.human.gallery.domain.exhibit.Exhibit;
import com.human.gallery.domain.exhibit.ExhibitRepository;
import com.human.gallery.domain.payment.PaymentRepository;
import com.human.gallery.domain.reserve.Reserve;
import com.human.gallery.domain.reserve.ReserveRepository;
import com.human.gallery.domain.user.UserRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReserveController {

    private final PaymentRepository paymentRepository;
    private final ExhibitRepository exhibitRepository;
    private final UserRepository userRepository;
    private final ReserveRepository reserveRepository;
    @GetMapping("/reserve/exhibit/{exhibitId}")
    public String viewcheckPay(@SessionAttribute(name = "user", required = false) Users user,
                               Model model,
                               @PathVariable("exhibitId") String exhibitId) {

        model.addAttribute("user", user);
        model.addAttribute("exhibitId", exhibitId);
        return "payment/checkPay";
    }

    @PostMapping("/reserve/exhibit/{exhibitId}")
    @ResponseBody
    public String returnExhibit(@PathVariable("exhibitId") int id,
                                @RequestParam("userId") String userId) {
        Exhibit exhibit = exhibitRepository.findById(id);
        Users userDetail = userRepository.findDetailById(userId);
        String uuid = UUID.randomUUID().toString();
        log.info("유저 정보 = {}", userDetail);
        JSONObject jO = new JSONObject();
        jO.put("name", exhibit.getName());
        jO.put("price", exhibit.getPrice());
        jO.put("total", exhibit.getTotal());
        jO.put("exhibitId", exhibit.getId());
        jO.put("endDate", exhibit.getEndDate());
        jO.put("startDate", exhibit.getStartDate());
        jO.put("userName", userDetail.getUsername());
        jO.put("address", userDetail.getAddress());
        jO.put("postcode", userDetail.getPostcode());
        jO.put("dtaddress", userDetail.getDtaddress());
        jO.put("orderId", uuid);
        log.info("예약 화면으로 넘어갈 값 = {}", jO);
        return jO.toJSONString();
    }

    @PostMapping("/reserve/add")
    @ResponseBody
    public void doAddReserve(@ModelAttribute Reserve reserve) {

        log.info("받은 값 = {}", reserve);
        reserveRepository.addReserve(reserve);
    }

    @PostMapping("/reserve/cancel")
    @ResponseBody
    public void doDeleteReserve(@RequestParam("orderId") String orderId) {
        reserveRepository.deleteById(orderId);
    }

}
