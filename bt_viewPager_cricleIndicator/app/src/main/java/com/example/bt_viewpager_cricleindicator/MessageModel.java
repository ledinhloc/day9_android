package com.example.bt_viewpager_cricleindicator;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {
    private boolean success;
    private String message;
    private List<ImagesSlider> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ImagesSlider> getResult() {
        return result;
    }

    public void setResult(List<ImagesSlider> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
