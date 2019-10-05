package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.DAO.HomepageCourseDao;
import com.imooc.homepage.Entity.HomepageCourse;
import com.imooc.homepage.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final HomepageCourseDao homepageCourseDao;

    public CourseServiceImpl(HomepageCourseDao homepageCourseDao) {
        this.homepageCourseDao = homepageCourseDao;
    }

    @Override
    public CourseInfo getCourseInfo(Long id) {

        Optional<HomepageCourse> course = homepageCourseDao.findById(id);
        return buildCourseInfo(course.orElse(HomepageCourse.invalid()));
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        if(CollectionUtils.isEmpty(request.getIds())){
            return Collections.emptyList();
        }

        List<HomepageCourse> courses = homepageCourseDao.findAllById(
                request.getIds()
        );

        return courses.stream().map(this::buildCourseInfo).collect(Collectors.toList());
    }

    private CourseInfo buildCourseInfo(HomepageCourse course){
        return CourseInfo.builder().id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0?"免费":"实战")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }
}
