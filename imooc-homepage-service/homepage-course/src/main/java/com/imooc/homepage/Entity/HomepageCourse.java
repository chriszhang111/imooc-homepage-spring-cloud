package com.imooc.homepage.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="homepage_course")
public class HomepageCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="course_name", nullable = false)
    private String courseName;

    @Column(name="course_type", nullable = false)
    private Integer courseType;

    @Column(name="course_icon", nullable = false)
    private String courseIcon;

    @Column(name="course_intro", nullable = false)
    private String courseIntro;

    @Column(name="create_time", nullable = false)
    @CreatedDate
    private Date CreateTime;

    @Column(name="update_time", nullable = false)
    @LastModifiedDate
    private Date UpdateTime;

    public HomepageCourse(String courseName, Integer courseType,
                          String icon, String intro){
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseIcon = icon;
        this.courseIntro = intro;
    }

    public static HomepageCourse invalid(){
        HomepageCourse invalid = new HomepageCourse(
                "",0,"",""
        );
        invalid.setId(-1L);
        return invalid;
    }



}
