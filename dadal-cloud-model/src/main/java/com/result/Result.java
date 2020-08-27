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
public class Result<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(boolean success, RespCode respCode) {
        this.success = success;
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
    }

    public Result(boolean success, RespCode respCode, T data) {
        this.success = success;
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
        this.data = data;
    }

    public Result(RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getDescription();
    }

    public Result(RespCode respCode, String specifiedMessage) {
        this.code = respCode.getCode();
        this.message = specifiedMessage;
    }

    /**
     * 返回成功
     */
    public static Result success() {
        return new Result(true, RespCode.SUCCESS);
    }

    /**
     * 返回成功(数据)
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(true, RespCode.SUCCESS, data);
    }

    /**
     * 返回成功(分页数据)
     */
    public static <T> Result<PageModel> success(Collection<T> list, Number totalCount) {
        PageModel<T> pageList = new PageModel<T>();
        pageList.setData(list);
        pageList.setTotalCount(totalCount.longValue());
        return success(pageList);
    }

    /**
     * 返回失败
     */
    public static Result fail() {
        return new Result(RespCode.FAIL);
    }

    /**
     * 返回失败(错误码)
     */
    public static Result fail(RespCode chooseCode) {
        return new Result(chooseCode);
    }

    /**
     * 返回失败(指定信息)
     */
    public static Result fail(String specifiedMessage) {
        return new Result(RespCode.FAIL, specifiedMessage);
    }

    /**
     * 返回失败(异常信息)
     */
    public static Result fail(Exception e) {
        return new Result(RespCode.FAIL, e.getMessage());
    }

    /**
     * 返回失败(错误码和指定信息)
     */
    public static Result fail(RespCode respCode, String specifiedMessage) {
        return new Result(respCode, specifiedMessage);
    }

}
