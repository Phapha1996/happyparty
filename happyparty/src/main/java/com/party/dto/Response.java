package com.party.dto;


public class Response {

    private int code;
    private String msg;
    private Object result;

    public Response(Status status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public Response(Status status, Object result) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.result = result;
    }

    public Response(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static Response success() {
        return new Response(Status.OK);
    }

    public static Response success(Object result) {
        return new Response(Status.OK, result);
    }

    public static Response failure() {
        return new Response(Status.SERVER_ERROR);
    }

    public static Response failure(String errorMsg) {
        return new Response(Status.SERVER_ERROR.getCode(), errorMsg, null);
    }

    public Response(Object result) {
        this(Status.OK, result);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
