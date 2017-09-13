package com.tinhcao.respone;

import java.io.Serializable;
import java.util.HashMap;

public class Response implements Serializable {
    private static final long serialVersionUID = 8507770580717323935L;
    private ResultCode result;
    private Object data;
    private String message;
    public enum ResultCode {
        SUCCESS, ERROR;

        ResultCode() {
        }
    }

    public Response(ResultCode result, Object data, String message){
        this.result = result;
        this.data = data == null ? new HashMap<>() : data;
        this.message = message == null ? "" : message;
    }

    public Response(ResultCode result) {
        this.result = result;
        this.data = new HashMap();
        this.message = "";
    }

    public Response(ResultCode result, Object data) {
        this.result = result;
        this.data = data == null ? new HashMap() : data;
        this.message = "";
    }

    public ResultCode getResult() {
        return result;
    }

    public void setResult(ResultCode result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
