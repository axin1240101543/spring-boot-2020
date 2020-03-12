package com.darren.center.springboot.utils;

import com.darren.center.springboot.common.ResponseHelper;
import com.darren.center.springboot.common.WebStatusEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 响应工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtils {

    /**
     * 返回结果集（分页）
     *
     * @param code      返回码
     * @param msg       返回消息
     * @param data      数据列表
     * @param pageCount 总记录数（分页）
     * @return
     */
    public static ResponseHelper response(String code, String msg, List<?> data, int pageCount) {
        ResponseHelper response = ResponseHelper.getInstanse();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        response.setCount(pageCount);
        return response;
    }

    public static ResponseHelper responseWrap(int flag) {
        if (flag > 0) {
            return response(WebStatusEnum.SUCCESS.getCode(), WebStatusEnum.SUCCESS.getMsg());
        }
        return response(WebStatusEnum.FAILURE.getCode(), WebStatusEnum.FAILURE.getMsg());
    }

    /**
     * 返回处理结果
     *
     * @param code 返回码
     * @param msg  返回消息
     * @return
     */
    public static ResponseHelper response(String code, String msg) {
        ResponseHelper response = ResponseHelper.getInstanse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

}
