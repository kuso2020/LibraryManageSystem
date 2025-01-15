package org.librarySystem.exception;

public class CustomException extends RuntimeException {
    private String code;
    private String message;

    public CustomException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
