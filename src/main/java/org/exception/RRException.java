package org.exception;

import cn.hutool.http.HttpStatus;

/**
 *@description 自定义异常
 *@author tangxl
 *@date 2020/6/17
 *@time 15:01
 */
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = HttpStatus.HTTP_INTERNAL_ERROR;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}