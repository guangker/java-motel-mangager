package com.mycompany.model;


public class ModelMessage {
    boolean success;
    String message;

    public ModelMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
