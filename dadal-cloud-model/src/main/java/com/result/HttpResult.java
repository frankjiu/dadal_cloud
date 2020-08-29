package com.result;

import java.util.Collection;

import lombok.Data;

/**
 * @Description: 响应结果集
 * @author: Frankjiu
 * @date: 2020年6月3日
 */
@Data
@SuppressWarnings("rawtypes")
public class HttpResult<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    public HttpResult() {
    }

    public HttpResult(boolean success, RespCode respCode) {
        this.success = success;
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
    }

    public HttpResult(boolean success, RespCode respCode, T data) {
        this.success = success;
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
        this.data = data;
    }

    public HttpResult(RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
    }

    public HttpResult(RespCode respCode, String specifiedMessage) {
        this.code = respCode.getCode();
        this.message = specifiedMessage;
    }

    /**
     * 返回成功
     */
    public static HttpResult success() {
        return new HttpResult(true, RespCode.SUCCESS);
    }

    /**
     * 返回成功(数据)
     */
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<>(true, RespCode.SUCCESS, data);
    }

    /**
     * 返回成功(分页数据)
     */
    public static <T> HttpResult<PageModel> success(Collection<T> list, Number totalCount) {
        PageModel<T> pageList = new PageModel<T>();
        pageList.setData(list);
        pageList.setTotalCount(totalCount.longValue());
        return success(pageList);
    }

    /**
     * 返回失败
     */
    public static HttpResult fail() {
        return new HttpResult(RespCode.FAIL);
    }

    /**
     * 返回失败(错误码)
     */
    public static HttpResult fail(RespCode chooseCode) {
        return new HttpResult(chooseCode);
    }

    /**
     * 返回失败(指定信息)
     */
    public static HttpResult fail(String specifiedMessage) {
        return new HttpResult(RespCode.FAIL, specifiedMessage);
    }

    /**
     * 返回失败(异常信息)
     */
    public static HttpResult fail(Exception e) {
        return new HttpResult(RespCode.FAIL, e.getMessage());
    }

    /**
     * 返回失败(错误码和指定信息)
     */
    public static HttpResult fail(RespCode respCode, String specifiedMessage) {
        return new HttpResult(respCode, specifiedMessage);
    }

}
