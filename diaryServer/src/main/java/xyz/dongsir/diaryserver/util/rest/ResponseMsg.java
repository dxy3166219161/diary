package xyz.dongsir.diaryserver.util.rest;

/**
 * Description:
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/19 1:47     dongxingyu        2.0.0       To create
 * </p>
 */
public class ResponseMsg {
    public static <T> ResultMsg<T> setErrorResult() {
        return new ResultMsg<T>().setCode(CodeConstant.FAIL).setMsg(CodeConstant.ERROR_MSG);
    }

    public static <T> ResultMsg<T> setErrorResult(String message) {
        return new ResultMsg<T>().setCode(CodeConstant.FAIL).setMsg(message);
    }

    public static <T> ResultMsg<T> setResult(int code, String msg) {
        return new ResultMsg<T>().setCode(code).setMsg(msg);
    }

    public static <T> ResultMsg<T> setResult(int code, String msg,T data) {
        return new ResultMsg<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static <T> ResultMsg<T> setSuccessResult() {
        return new ResultMsg<T>().setCode(CodeConstant.SUCCESS).setMsg(CodeConstant.SUCESS_MSG);
    }

    public static <T> ResultMsg<T> setSuccessResult(T data) {
        return new ResultMsg<T>().setCode(CodeConstant.SUCCESS).setMsg(CodeConstant.SUCESS_MSG).setData(data);
    }

    public static <T> ResultMsg<T> setSuccessResult(T data, String msg) {
        return new ResultMsg<T>().setCode(CodeConstant.SUCCESS).setMsg(msg).setData(data);
    }
}