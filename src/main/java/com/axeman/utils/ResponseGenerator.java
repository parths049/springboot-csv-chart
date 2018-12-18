package com.axeman.utils;

import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {

  public static Object generateResponse(String status, String message,Object data) {
    JSONResponse<Object> resObj = new JSONResponse<Object>();
    resObj.setStatus(status);
    resObj.setMessage(message);
    resObj.setData(data);
    return resObj;
  }

}
