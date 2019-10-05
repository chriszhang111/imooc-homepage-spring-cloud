package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.DAO.HomepageUserCourseDao;
import com.imooc.homepage.DAO.HomepageUserDao;
import com.imooc.homepage.Entity.HomepageUser;
import com.imooc.homepage.Entity.HomepageUserCourse;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.VO.CreateUserRequest;
import com.imooc.homepage.VO.UserCourseInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final HomepageUserDao userDao;
    private final HomepageUserCourseDao userCourseDao;
    private final CourseClient courseClient;

    @Autowired
    public UserServiceImpl(HomepageUserDao userDao, HomepageUserCourseDao userCourseDao, CourseClient courseClient) {
        this.userDao = userDao;
        this.userCourseDao = userCourseDao;
        this.courseClient = courseClient;
    }

    @Override
    public UserInfo createUser(CreateUserRequest request) {

        if(!request.validate())
            return UserInfo.invalid();
        HomepageUser user = userDao.findByUsername(request.getUsername());
        if(user != null){
            return UserInfo.invalid();
        }
        HomepageUser newUser = userDao.save(new HomepageUser(request.getUsername(), request.getEmail()));
        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {

        Optional<HomepageUser> user = userDao.findById(id);
        if(!user.isPresent())
            return UserInfo.invalid();
        HomepageUser curr = user.get();

        return new UserInfo(curr.getId(), curr.getUsername(), curr.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {
        Optional<HomepageUser> user = userDao.findById(id);
        if(!user.isPresent())
            return UserCourseInfo.invalid();
        HomepageUser curr = user.get();
        UserInfo userInfo = new UserInfo(curr.getId(), curr.getUsername(), curr.getEmail());
        List<HomepageUserCourse> courses = userCourseDao.findAllByUserId(id);
        if(CollectionUtils.isEmpty(courses))
            return new UserCourseInfo(userInfo, Collections.emptyList());
        List<CourseInfo> infos = courseClient.getCourseInfos(new CourseInfosRequest(courses.stream().map(HomepageUserCourse::getCourseId).collect(Collectors.toList())));
        return new UserCourseInfo(userInfo, infos);
    }
}
