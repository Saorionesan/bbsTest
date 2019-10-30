package com.epoint.bbs.model.entity;

/**
 * 消息接收实体类
 * @param <T>
 */
public class ResponseResult<T> {
    // 状态码
    private Integer state=200;
    private String message;
    //保存数据
    private T data;
    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state, Exception e) {
        super();
        this.state = state;
        this.message = e.getMessage();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
