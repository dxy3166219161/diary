package xyz.dongsir.diaryserver.util.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 返回消息体
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/19 1:44     dongxingyu        2.0.0       To create
 * </p>
 */
@ApiModel(description = "返回响应数据")
public class ResultMsg<T> {

    @ApiModelProperty(value = "编码")
    private int code;

    @ApiModelProperty(value = "信息")
    private String msg;

    @ApiModelProperty(value = "结果")
    private T data;

    public int getCode() {
        return code;
    }

    public ResultMsg<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultMsg<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultMsg<T> setData(T data) {
        this.data = data;
        return this;
    }


}