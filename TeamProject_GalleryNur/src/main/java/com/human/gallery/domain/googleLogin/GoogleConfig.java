package com.human.gallery.domain.googleLogin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@ToString
@Slf4j
public class GoogleConfig {

    @Value("${google.auth.url}")
private String googleAuthUrl;
    @Value("${google.login.url}")
    private String googleLoginUrl;
    @Value("${google.redirect.uri}")
    private String googleRedirectUri;
    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.secret}")
    private String googleSecret;
    @Value("${google.auth.scope}")
    private String scopes;

    public String googleInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", getGoogleClientId());
        params.put("redirect_uri", getGoogleRedirectUri());
        params.put("response_type", "code");
        params.put("scope", getScopeUrl());

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));
        // &을 기준으로 쪼개면서 출력..
        log.info("구글 초기화 = {}", paramStr);
        return getGoogleLoginUrl() + "/o/oauth2/v2/auth" + "?" + paramStr;
    }

    public String getScopeUrl() {
        return scopes.replaceAll(",", "%20");
    }
}
