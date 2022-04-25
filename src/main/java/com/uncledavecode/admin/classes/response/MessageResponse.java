package com.uncledavecode.admin.classes.response;

import lombok.Data;

@Data
public class MessageResponse  {
    private String code;
    private String message;

  public MessageResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
