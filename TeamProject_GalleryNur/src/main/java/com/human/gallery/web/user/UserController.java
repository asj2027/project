package com.human.gallery.web.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.human.gallery.domain.exhibit.Exhibit;
import com.human.gallery.domain.exhibit.ExhibitRepository;
import com.human.gallery.domain.googleLogin.GoogleSignForm;
import com.human.gallery.domain.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {


	private final UserService userService;
	private final ExhibitRepository exhibitRepository;
	String redirect = "/";
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@SessionAttribute(name = "user", required = false) Users user, Model model,
						@RequestParam(required = false) String access) {
		log.info("호출 여부");
		model.addAttribute("user", user);
		ArrayList<Exhibit> exhibits = exhibitRepository.findAll();
		model.addAttribute("exhibits", exhibits);
		if (access != null && access.equals("1")){
			model.addAttribute("error", "관리자만 접근할 수 있습니다.");
		}
		log.info("여기도 넘어옴");
		return "home";
	}
	@GetMapping("/robots.txt")
	@ResponseBody
	public String returnRobot() {
		return "User-agent: * \nAllow: /\n";
	}
	@GetMapping("/login")
	public String viewLogin(@ModelAttribute("user") Users user,
							@RequestParam(defaultValue = "/") String redirectURL,
							Model model) {
		redirect = redirectURL;
		if (!redirect.equals("/"))
		{
			model.addAttribute("error", "로그인 후 이용이 가능합니다.");
		}
		return "users/login";
	}
	@RequestMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/login")
	public String doLogin(@Validated @ModelAttribute("user") UsersLoginForm Form, BindingResult bindingResult,
							HttpSession session, Model model) throws NoSuchAlgorithmException {

		if (bindingResult.hasErrors())
		{
			log.info("발생된 에러 = {}", bindingResult.getFieldError());
			return "users/login";
		}
		Users user = userService.login(Form.getId(), Form.getPassword());
		if (user == null)
		{
			model.addAttribute("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "users/login";
		}


		session.setAttribute("user",user);
		return "redirect:" + redirect;
	}

	@RequestMapping("/signin")
	public String viewSignin(@ModelAttribute("userSign") UsersSignForm user,
							 @SessionAttribute(name = "user", required = false) Users usera,
							 Model model) {
		model.addAttribute("user", usera);
		return "users/signin";
	}

	@PostMapping("/signin")
	public String doSignin(@Validated @ModelAttribute("userSign") UsersSignForm form, BindingResult bindingResult,
							Model model,
						   @SessionAttribute(name = "user", required = false) Users usera) throws NoSuchAlgorithmException {

		if (bindingResult.hasErrors())
		{
			log.info("발생된 에러 {} = ", bindingResult.getFieldErrors());
			model.addAttribute("user", usera);
			return "users/signin";
		}
		Users checkId = userService.checkId(form.getId(), "NORMAL");
		if (checkId != null)
		{
			model.addAttribute("user", usera);
			model.addAttribute("overlap", "중복된 아이디입니다.");
			return "users/signin";
		}
		boolean checkEmail = userService.checkEmail(form.getEmail());
		if (checkEmail) {
			model.addAttribute("user", usera);
			model.addAttribute("overlapEmail", "중복된 이메일입니다.");
			return "users/signin";
		}
		if (!form.getPassword().equals(form.getPasswordCheck()))
		{
			model.addAttribute("user", usera);
			model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
			return "users/signin";
		}
		userService.addUsers(form);
		model.addAttribute("user", usera);
		model.addAttribute("success", "회원가입이 완료되었습니다");
		return "users/signin";
	}
	@GetMapping("/findMyId")
	public String viewFindJSP() {
		return "users/findUserId";
	}
	@PostMapping("/findMyId")
	@ResponseBody
	public String checkMail(@RequestParam("email") String email) {
		boolean checkedMail = userService.checkEmail(email);
		if (checkedMail) {
			return "true";
		} else {
			return "false";
		}
	}
	@PostMapping("/findMyId/userId")
	@ResponseBody
	public String returnUserId(@RequestParam("email") String email) {
		String userId = userService.returnUserId(email);
		return userId;
	}
	@GetMapping("/findMyPwd")
	public String viewFindPwd() {
		return "users/findUserPwd";
	}
	@PostMapping("/findMyPwd")
	@ResponseBody
	public String checkIdAndMail(@RequestParam("email") String email, @RequestParam("userId") String id) {
		boolean checked = userService.checkIdAndMail(email, id);
		if (checked) {
			return "true";
		} else {
			return "false";
		}
	}
	@PostMapping("/findMyPwd/temp")
	@ResponseBody
	public String tempPassword(@RequestParam("userId") String id, @RequestParam("password") String temp) throws NoSuchAlgorithmException {
		userService.changeTemp(id, temp);
		return "true";
	}

	@GetMapping("/history")
	public String viewAccount(@SessionAttribute ("user") Users user, Model model) {

		model.addAttribute("user", user);
		log.info("여기도 넘어옴");
		return "account";
	}
	// kakaoLogin
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code, Model model, HttpSession session) {    // Data를 리턴해주는 컨트롤러 함수
		// POST방식으로 key=value 데이터를 요청 (카카오측으로)
		// Retrofit2(안드로이드에서 주로사용)
		RestTemplate rt = new RestTemplate();

		// HttpHeaders 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "11afdf6f295b3f272c88971d1ea73cdd");
		params.add("redirect_uri", "http://nurgallery.shop/auth/kakao/callback");
		params.add("code", code);
		params.add("client_secret", "cYyzjY4ee1qMSBmJOJyYQf1RbwPJ9W5L");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 Response변수의 응답을 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();

		// HttpHeaders 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식으로 - 그리고 Response변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		System.out.println("카카오 아이디(번호) :" + kakaoProfile.getId());
		System.out.println("카카오 이메일 :" + kakaoProfile.getKakao_account().getEmail());

		System.out.println("갤러리 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
		System.out.println("갤러리 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		UUID tempPassword = UUID.randomUUID();
		System.out.println("갤러리 패스워드 : " + tempPassword);

		Users userCheck = userService.checkId(String.valueOf(kakaoProfile.getId()), "KAKAO");
		UsersSignForm user = new UsersSignForm();
		if (userCheck == null) {
			user.setId(String.valueOf(kakaoProfile.getId()));
			user.setName(kakaoProfile.getKakao_account().getProfile().getNickname());
			model.addAttribute("userSign", user);
			return "users/kakaoSignin";
		} else {
			session.setAttribute("user", userCheck);
			return "redirect:/";
		}
	}
	@PostMapping("/kakao/signin")
	public String getKakaoInfo(@Validated @ModelAttribute("userSign")GoogleSignForm form, BindingResult bindingResult,
							   Model model, @SessionAttribute(name = "user", required = false) Users usera) {
		if(bindingResult.hasErrors()) {
			log.info("발생된 에러 {} = ", bindingResult.getFieldErrors());
			model.addAttribute("user", usera);
			return "users/kakaoSignin";
		}
		log.info("카카오 회원가입 - > {}", form);
		userService.addKakaoUser(form);
		return "redirect:/login";
	}
}