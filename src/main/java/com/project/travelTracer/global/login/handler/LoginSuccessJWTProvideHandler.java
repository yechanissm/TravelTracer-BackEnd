package com.project.travelTracer.global.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.travelTracer.global.jwt.service.JwtService;
import com.project.travelTracer.global.login.response.LoginResponse;
import com.project.travelTracer.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessJWTProvideHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    // Token 값을 외부로 넘겨주기 위해서 작성
    private String accessToken;
    private String refreshToken;

    //getter 메소드
    public String getAccessToken(){
        return accessToken;
    }
    public String getRefreshToken(){
        return refreshToken;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userID = extractUserId(authentication);
        accessToken = jwtService.createAccessToken(userID);
        refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);

        memberRepository.findByUserId(userID).ifPresent(
                member -> member.updateRefreshToken(refreshToken)
        );


        response.setStatus(HttpServletResponse.SC_OK);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCode(200);
        loginResponse.setMessage("성공");

        String result = objectMapper.writeValueAsString(loginResponse);
        response.getWriter().write(result);


        log.info( "로그인에 성공합니다. userID: {}" ,userID);
        log.info( "AccessToken 을 발급합니다. AccessToken: {}" ,accessToken);
        log.info( "RefreshToken 을 발급합니다. RefreshToken: {}" ,refreshToken);


    }

    private String extractUserId(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
