package com.azhen.cloud.user.server.service;

import com.azhen.cloud.user.server.dataobject.UserInfo;

public interface UserService {
    UserInfo findByOpenid(String openid);
}
