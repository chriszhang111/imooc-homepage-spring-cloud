package com.imooc.homepage.service;

import com.imooc.homepage.UserInfo;
import com.imooc.homepage.VO.CreateUserRequest;
import com.imooc.homepage.VO.UserCourseInfo;

public interface UserService {

    UserInfo createUser(CreateUserRequest request);

    UserInfo getUserInfo(Long id);

    UserCourseInfo getUserCourseInfo(Long id);

}
