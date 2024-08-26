package com.human.gallery.web.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.human.gallery.domain.payment.Payment;
import com.human.gallery.domain.payment.PaymentRepository;
import com.human.gallery.domain.reserve.Reserve;
import com.human.gallery.domain.reserve.ReserveRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final ReserveRepository reserveRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String SECRET_KEY = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R";

    @RequestMapping("/success")
    public String confirmPayment(@SessionAttribute(name = "user", required = false) Users user,
                                @RequestParam("paymentKey") String paymentKey,
                                @RequestParam("orderId") String orderId,
                                @RequestParam("amount") Long amount, Model model) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(SECRET_KEY, ""); // spring framework 5.2 이상 버전에서 지원
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Reserve reserve = reserveRepository.findById(orderId);

        if (reserve.getAmount() != amount) {
            model.addAttribute("결제 과정에서 오류가 발생했습니다. 다시 진행해주세요");
        }

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));
        payloadMap.put("paymentKey", paymentKey);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

//        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
//                "https://api.tosspayments.com/v1/payments/confirm" + paymentKey, request, JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            log.info("successNode = {}", successNode);
            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setPaymentKey(String.valueOf(successNode.get("paymentKey")));
            payment.setPrice(Integer.parseInt(String.valueOf(successNode.get("totalAmount"))));
            payment.setPaymentDate(String.valueOf(successNode.get("approvedAt")));
            payment.setPaymentMethod("토스");
            reserveRepository.updatePaymentById(orderId);
            paymentRepository.addState(payment);
            model.addAttribute("user", user);
            return "payment/success";
        } else {
            JsonNode failNode = responseEntity.getBody();
            model.addAttribute("message", failNode.get("message").asText());
            model.addAttribute("code", failNode.get("code").asText());
            return "payment/fail";
        }
    }
}

