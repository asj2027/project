package com.human.gallery.domain.mypage;

import com.human.gallery.domain.user.EncryptionUtils;
import com.human.gallery.domain.user.UserRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;
//
//    public static Users mypage(String id, String password) throws NoSuchAlgorithmException {
//        Users users = userRepository.findById(id);
//        String salt = users.getSalt();
//        String tempPassword = EncryptionUtils.getEncrypt(password,salt);
//
//        if (users == null)
//        {
//            return null;
//        }
//        else if (users.getPassword().equals(tempPassword)) {
//            return users;
//        }
//        else {
//            return null;
//        }
//    }
}
