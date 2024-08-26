package com.human.gallery.web.googleLogin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.human.gallery.domain.googleLogin.*;
import com.human.gallery.domain.user.UserService;
import com.human.gallery.domain.user.Users;
import com.human.gallery.domain.user.UsersSignForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GoogleLoginController {

    private final GoogleConfig googleConfig;
    private final UserService userService;
    @GetMapping("/google/login")
    public ResponseEntity<Object> moveGoogle() {
        String authUrl = googleConfig.googleInitUrl();
        URI redirectUri = null;
        try {
            redirectUri = new URI(authUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/google/login/redirect")
    public String redirectGoogleLogin(@RequestParam("code") String code,
                                                           Model model,
                                      HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        GoogleLoginRequest request = GoogleLoginRequest.builder()
                .clientId(googleConfig.getGoogleClientId())
                .clientSecret(googleConfig.getGoogleSecret())
                .code(code)
                .redirectUri(googleConfig.getGoogleRedirectUri())
                .grantType("authorization_code")
                .build();

        try {
            // Http의 Header 설정하는 부분.
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<GoogleLoginRequest> httpRequestEntity = new HttpEntity<>(request, httpHeaders);
            ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(googleConfig.getGoogleAuthUrl() + "/token", httpRequestEntity, String.class);

            //ObjectMapper를 이용해서 String to Object로 변환시키기

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)
            log.info("Object  Mapper를 통해서 읽어들인 값 = {}", objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<GoogleLoginResponse>() {}));
            GoogleLoginResponse googleLoginResponse = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<GoogleLoginResponse>() {});
            // 사용자의 정보는 JWT TOKEN으로 저장되어 있어서 ID_TOKEN에 값을 저장한다고 한다.
            String jwtToken = googleLoginResponse.getId_token();

            log.info("jwtToken -> {}", jwtToken);
            String requestUrl = UriComponentsBuilder.fromHttpUrl(googleConfig.getGoogleAuthUrl() + "/tokeninfo").queryParam("id_token", jwtToken).toUriString();

            String resultJson = restTemplate.getForObject(requestUrl, String.class);
            log.info("resultJson -> {}", resultJson);
            // 체크
            if (resultJson != null) {
                GoogleLogin googleLogin = objectMapper.readValue(resultJson, new TypeReference<GoogleLogin>() {});
                log.info("구글 로그인 - > {}", googleLogin);
                Users userCheck = userService.checkId(googleLogin.getEmail(), "GOOGLE");
                UsersSignForm user = new UsersSignForm();
                if (userCheck == null) {
                    user.setId(googleLogin.getEmail());
                    user.setName(googleLogin.getName());
                    model.addAttribute("userSign", user);
                    return "users/googleSignin";

                } else {
                    session.setAttribute("user", userCheck);
                    return "redirect:/";
                }
            }
            else {
                throw new Exception("구글 로그인 실패!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/google/signin")
    public String getGoogleInfo(@Validated @ModelAttribute("userSign") GoogleSignForm form, BindingResult bindingResult,
                                Model model,
                                @SessionAttribute(name = "user", required = false) Users usera) {
        if (bindingResult.hasErrors())
        {
            log.info("발생된 에러 {} = ", bindingResult.getFieldErrors());
            model.addAttribute("user", usera);
            return "users/googleSignin";
        }
        log.info("구글 회원가입 - > {}", form);
        userService.addGoogleUsers(form);
        return "redirect:/login";
    }
}
