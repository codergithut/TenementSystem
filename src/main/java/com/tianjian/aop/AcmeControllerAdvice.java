package com.tianjian.aop;

import com.tianjian.model.ServiceMessage;
import com.tianjian.model.view.ResponseData;
import com.tianjian.service.ServiceEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianjian on 2019/1/13.
 */
@ControllerAdvice("com.tianjian.rest")
public class AcmeControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<ResponseData<ServiceMessage>> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        ServiceMessage serviceMessage = new ServiceMessage(ServiceEnum.FAIL, null);
        ResponseData<ServiceMessage> responseData = new ResponseData<>();
        return new ResponseEntity<ResponseData<ServiceMessage>>(responseData.buildResponseDataByCode(serviceMessage),status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
