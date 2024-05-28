package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class HttpInterceptor implements HandlerInterceptor {

  private final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    LOGGER.info("[preHandle] preHandle is performed");
//    LOGGER.info("[preHandle] request : {}", request);
//    LOGGER.info("[preHandle] request path info : {}", request.getPathInfo());
//    LOGGER.info("[preHandle] request header names : {}", request.getHeaderNames());
//    LOGGER.info("[preHandle] request request URL : {}", request.getRequestURL());
//    LOGGER.info("[preHandle] request request URI: {}", request.getRequestURI());
//    LOGGER.info("[preHandle] request Requested Session Id : {}", request.getRequestedSessionId());

    
    // 아래 Stream을 사용하게 되면 ServletRequest특성상 다시는 read 할 수 없게끔 설정이 되어 있어
    // request를 사용하기 위해서 Interceptor에서 한번 까보고 하기 위해서는
    // HttpServletRequestWrapper 구현하여 body값을 여러번 확인 할 수 있게 할 수 있다.
    //request.getInputStream();
    // TODO HttpServletRequestWrapper 구현하여 Body 값 확인할 수 있게 코드 추가

    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    LOGGER.info("[postHandle] postHandle is performed");
//    LOGGER.info("[postHandle] request : {}", request);
//    LOGGER.info("[postHandle] response : {}", response);
//    LOGGER.info("[postHandle] response : {}", response.getHeaderNames());
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    LOGGER.info("[afterCompletion] afterCompletion is performed");
  }
}