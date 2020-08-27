package com.result;

import lombok.Getter;

/**
 * @Description: 响应码
 * @author: Frankjiu
 * @date: 2020年6月3日
 */
@Getter
public enum RespCode {

    /**
     * 成功
     */
    SUCCESS("111111", "Success"),

    /**
     * 失败
     */
    FAIL("000000", "Fail"),

    /**
     * 参数错误
     */
    PARAM_ERROR("900001", "Invalid parameters"),

    /**
     * 解析错误
     */
    PARSE_ERROR("900002", "Parse failed"),

    /**
     * 权限不足
     */
    INSUFFICIENT_AUTHORITY("900003", "Permissions is not enough!"),

    /**
     * 资源不存在
     */
    NOT_FOUND("900004", "Resource not found"),

    /**
     * 系统错误
     */
    INTERNAL_ERROR("900005", "Internal System Error");

    private String code;
    private String description;

    private RespCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
