package com.fushun.pay.app.dto.enumeration;

import com.fushun.framework.util.exception.base.BaseEnum;

/**
 * 退款 通知状态
 *
 * @author zhoup
 */
public enum ERefundNotityStatus implements BaseEnum<Integer> {
    Yes(1, "已通知 "),
    No(2, "未通知");

    private Integer code;

    private String text;

    ERefundNotityStatus(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
