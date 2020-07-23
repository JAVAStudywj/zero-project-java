package com.zero.common.result;

/**
 * 返回数据信息结果
 */
public class JsonResult {


    private static final String SUCCESS = "200";
    private static final String ERROR = "400";
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 400;
    private String status;
    private Object data;
    private String message;
    private int code = SUCCESS_CODE;

    public JsonResult() {
        super();
    }

    public JsonResult(int code, String message, Object data) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static JsonResult getSuccess(Object data, String message) {
        JsonResult result = new JsonResult();
        result.setSuccessData(data);
        result.setMessage(message);
        result.setStatus("200");
        return result;
    }

    public static JsonResult getError(Object data, String message) {
        JsonResult result = new JsonResult();
        result.setErrorData(data);
        result.setMessage(message);
        result.setCode(ERROR_CODE);
        result.setStatus(ERROR);
        return result;
    }

    public static JsonResult getSuccess(Object data) {
        JsonResult result = new JsonResult();
        result.setSuccessData(data);
        result.setCode(SUCCESS_CODE);
        result.setStatus(SUCCESS);
        return result;
    }

    public static JsonResult getError(Object data) {
        JsonResult result = new JsonResult();
        result.setErrorData(data);
        result.setCode(ERROR_CODE);
        result.setStatus(ERROR);
        return result;
    }

    public static JsonResult getSuccess(String message) {
        JsonResult result = new JsonResult();
        result.setMessage(message);
        result.setCode(SUCCESS_CODE);
        result.setStatus(SUCCESS);
        return result;
    }

    public static JsonResult getError(String message) {
        JsonResult result = new JsonResult();
        result.setMessage(message);
        result.setCode(ERROR_CODE);
        result.setStatus(ERROR);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccessData(Object data) {
        this.status = JsonResult.SUCCESS;
        this.code = SUCCESS_CODE;
        setData(data);
    }

    public void setErrorData(Object data) {
        this.status = JsonResult.ERROR;
        setData(data);
    }

}