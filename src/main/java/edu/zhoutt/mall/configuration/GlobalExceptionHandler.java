package edu.zhoutt.mall.configuration;

import edu.zhoutt.mall.base.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public HttpResult<Object> handlerException(Exception e) {
        log.error("发生了未知异常", e);
        return HttpResult.error("server error");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public HttpResult<Object> handlerIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法", e);
        return HttpResult.error(e.getMessage());
    }
}
