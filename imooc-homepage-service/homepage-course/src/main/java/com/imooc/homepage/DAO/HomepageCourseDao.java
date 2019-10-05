package com.imooc.homepage.DAO;

import com.imooc.homepage.Entity.HomepageCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomepageCourseDao extends JpaRepository<HomepageCourse, Long> {
}
