package com.azhen.cloud.user.server.controller;

import com.azhen.cloud.user.server.VO.ResultVO;
import com.azhen.cloud.user.server.constant.CookieConstant;
import com.azhen.cloud.user.server.constant.RedisConstant;
import com.azhen.cloud.user.server.dataobject.UserInfo;
import com.azhen.cloud.user.server.enums.ResultEnum;
import com.azhen.cloud.user.server.enums.RoleEnum;
import com.azhen.cloud.user.server.service.UserService;
import com.azhen.cloud.user.server.utils.CookieUtil;
import com.azhen.cloud.user.server.utils.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 往cookie里写openid
     * @param openid
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.MAX_AGE);
        return ResultVOUtil.success();
    }

    /**
     * cookie返回uuid，并写redis
     * @param openid
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid, CookieConstant.MAX_AGE, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.MAX_AGE);
        return ResultVOUtil.success();
    }
}
