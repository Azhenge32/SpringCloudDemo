package com.azhen.cloud.user.server.repository;



import com.azhen.cloud.user.server.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByOpenid(String openid);
}
