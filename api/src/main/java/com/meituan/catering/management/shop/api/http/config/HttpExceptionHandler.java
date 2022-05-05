package com.meituan.catering.management.shop.api.http.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 公共Web异常拦截器
 *
 * @author dulinfeng
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildGlobalModelAndView(ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ModelAndView handleIllegalStateException(IllegalStateException ex) {
        return buildGlobalModelAndView(ex);
    }

    private ModelAndView buildGlobalModelAndView(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.getModel().put("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(BindException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ImmutableMap.of(
                        "message", "请求参数验证失败，请根据规则进行修改。",
                        "errors", ex.getAllErrors()
                ));
    }
}
