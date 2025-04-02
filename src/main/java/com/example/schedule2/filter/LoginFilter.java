package com.example.schedule2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;

//로그작성용
@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHILE_LIST = {"/", "/user/signup","/login","/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //다운 캐스팅
        // 여러기능 사용 용이하게 위함임
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletRequest HttpResponse = (HttpServletRequest) response;

        log.info("로그인 필터 로직 실행");

        //로그인을 체크해야하는 URL인지 확인
        //포함되면 작동하게한다
        if(isWhiteList(requestURI)){
            //세션을 가져온다
            HttpSession session = httpRequest.getSession(false);
            //세션이 없거나 세션키가 없다면?
            if (session == null || session.getAttribute("sessionKey")==null){
                throw new RuntimeException("로그인 해주세요.");
            }
            //로그인 성공시
            log.info("로그인 성공했습니다.");

        }
        //1번경우 WHITE_LiST에 URL 요청시 chain.doFilter() 호출
        //2번경우 WHITE_LIST 아닌 경우 위 필터를 통과 후에 chain.doFilter() 다음 필터나 Servelt 호출
        // 다음 필터가 없으면 Servlet -> controller , 다음 필터 있으면 다음 필터 호출
        chain.doFilter(request,response);
    }

    //로그인 여부 확인 url
    //boolean 타입일떄는 보통is 붙여서 쓴다
    private boolean isWhiteList(String requestURI){
        //requst url가 whiteListurl에 포함되면
        // 포함 되면 true
        // 아닐 시 flase
        return PatternMatchUtils.simpleMatch(WHILE_LIST,requestURI);
    }
}
