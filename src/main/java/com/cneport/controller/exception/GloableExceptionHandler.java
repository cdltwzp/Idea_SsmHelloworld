package com.cneport.controller.exception;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GloableExceptionHandler implements HandlerExceptionResolver {

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o 出现异常的对象
     * @param e 异常对象
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        if (e instanceof NullPointerException){
            //mv.setViewName("error5");
            try {
                httpServletResponse.sendRedirect("error/405.html");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return mv;
    }
}