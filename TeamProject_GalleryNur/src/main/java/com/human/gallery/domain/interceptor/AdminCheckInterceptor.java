package com.human.gallery.domain.interceptor;

import com.human.gallery.domain.user.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("관리자 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        if (session == null || !user.getRole().equals("관리자")) {
            log.info("관리자가 아닌 사용자의 요청");
            response.sendRedirect("/?access=1");
            return false;
        }
        return true;
    }
}
