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
    SUCCESS("11111", "Success"),

    /**
     * 失败
     */
    FAIL("00000", "Fail"),

    /**
     * 参数错误
     */
    PARAM_ERROR("90001", "Invalid parameters"),

    /**
     * 解析错误
     */
    PARSE_ERROR("90002", "Parse failed"),

    /**
     * 权限不足
     */
    INSUFFICIENT_AUTHORITY("90003", "Permissions is not enough!"),

    /**
     * 资源不存在
     */
    NOT_FOUND("90004", "Resource not found"),

    /**
     * 系统错误
     */
    INTERNAL_ERROR("90005", "Internal System Error");

    private String code;
    private String description;

    private RespCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
