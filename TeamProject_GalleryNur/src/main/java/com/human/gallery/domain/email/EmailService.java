package com.human.gallery.domain.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    public static final String ePw = createKey();

    private MimeMessage createMessage(String toSend) throws Exception {
        log.info("보내는 대상 - > {}", toSend);
        log.info("내가 보낸 인증 번호 -> {}", ePw);
        MimeMessage message = javaMailSender.createMimeMessage();

        String code = createCode(ePw); // 코드 만들기.
        message.addRecipients(Message.RecipientType.TO, toSend); // 대상에게 메세지를 보낸다.
        message.setSubject("갤러리 누르의 인증번호 : " + code); // 메일의 제목을 적어줌.

        String msg = ""; // 여기서부터 메세지
        msg += "아이디 또는 비밀번호를 찾는데 필요한 인증번호를 입력해주세요 <br>" +code;
        message.setText(msg, "utf-8", "html"); // 메세지의 내용.
        message.setFrom(new InternetAddress("tempnurgallery1@gmail.com", "갤러리 누르"));
        return message;
    }
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public void sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{//예외처리
            javaMailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String createCode(String ePw){
        return ePw.substring(0, 3) + "-" + ePw.substring(3, 6);
    }
}
