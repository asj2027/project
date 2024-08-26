package com.human.gallery.domain.user;

import com.human.gallery.domain.googleLogin.GoogleSignForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;


	public Users login(String id, String password) throws NoSuchAlgorithmException {
		Users user = userRepository.findById(id);

		if (user == null)
		{
			return null;
		}
		else if (user.getIsDelete().equals("Y")) {
			return null;
		}
		String salt = user.getSalt();
		String tempPassword = EncryptionUtils.getEncrypt(password, salt);
		if (user.getPassword().equals(tempPassword)) {
			return user;
		}
		else {
			return null;
		}
	}
	public void impoupdate(UsersUpdateForm form) throws NoSuchAlgorithmException {

		String salt = EncryptionUtils.getSalt();
		String tempPassword = EncryptionUtils.getEncrypt(form.getPassword(), salt);
		userRepository.pwdupdate(tempPassword,salt, form.getId());
		userRepository.impoupdate(form.getName(), form.getEmail(), form.getPostcode(), form.getAddress(), form.getDtaddress(), form.getMobile(), form.getId());
	}
	public Users checkId(String id, String path) {
		
		Users user = userRepository.findByIdWithPath(id, path);
		
		if (user == null) {
			return null;
		}
		else {
			return user;
		}
	}
	public boolean checkEmail(String email) {
		Integer num = userRepository.findUsersByEmail(email);

		if (num == null || num == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean checkIdAndMail(String email, String userId) {
		String users = userRepository.checkIdAndMail(email, userId);
		if (users == null) {
			return false;
		} else {
			return true;
		}
	}
	public void changeTemp(String userId, String temp) throws NoSuchAlgorithmException {
		String salt = EncryptionUtils.getSalt();
		String encryptPwd = EncryptionUtils.getEncrypt(temp, salt);
		userRepository.changePwdWithTemp(encryptPwd, userId, salt);
	}

	public void addUsers(UsersSignForm user) throws NoSuchAlgorithmException {
		String salt = EncryptionUtils.getSalt();

		String tempPassword = EncryptionUtils.getEncrypt(user.getPassword(), salt);
		log.info("암호화 된 비밀번호 = {}", tempPassword);
		userRepository.addUser(user.getId(), tempPassword, salt);
		Integer number = userRepository.findNumById(user.getId());
		String address = user.getAddress() + " " + user.getRefAddress();
		log.info("무결성 제약조건 확인용 = {}", number);
		userRepository.addDetail(number, user.getName(),user.getMobile(), address,user.getDtaddress(), user.getEmail(), user.getPostcode());
	}

	public void addGoogleUsers(GoogleSignForm googleUser) {
		String tempPassword = UUID.randomUUID().toString();

		userRepository.addGoogleUser(googleUser.getId(), tempPassword,null);
		Integer number = userRepository.findNumByIdWithPath(googleUser.getId(), "GOOGLE");
		log.info("넘어온 번호 -> {}", number);
		String address = googleUser.getAddress() + " " + googleUser.getRefAddress();
		String tempEmail = UUID.randomUUID().toString();
		userRepository.addDetail(number, googleUser.getName(), googleUser.getMobile(), address, googleUser.getDtaddress(), tempEmail, googleUser.getPostcode());
	}

	public String returnUserId(String email) {
		Integer num = userRepository.findUsersByEmail(email);
		String userID = userRepository.findUserIdByNum(num);
		return userID;
	}

	public void addKakaoUser(GoogleSignForm kakaoUser) {
		String tempPassword = UUID.randomUUID().toString();

		userRepository.addKakaoUser(kakaoUser.getId(), tempPassword, null);
		Integer number2 = userRepository.findNumByIdWithPath(kakaoUser.getId(), "KAKAO");
		String address = kakaoUser.getAddress() + " " + kakaoUser.getRefAddress();
		userRepository.addDetail(number2, kakaoUser.getName(), kakaoUser.getMobile(), address, kakaoUser.getAddress(), kakaoUser.getId(), kakaoUser.getPostcode());
	}
	public Users returnUser(String usernum) {
		return userRepository.findAllByNum(usernum);
	}
}
