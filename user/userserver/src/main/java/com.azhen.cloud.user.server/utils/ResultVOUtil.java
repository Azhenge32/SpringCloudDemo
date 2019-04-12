package com.azhen.cloud.user.server.utils;


import com.azhen.cloud.user.server.VO.ResultVO;
import com.azhen.cloud.user.server.enums.ResultEnum;

public class ResultVOUtil {
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(ResultEnum object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(object.getCode());
        resultVO.setMsg(object.getMessage());
        return resultVO;
    }
}
