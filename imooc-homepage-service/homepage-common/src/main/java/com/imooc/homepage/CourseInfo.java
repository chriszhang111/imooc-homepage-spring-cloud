package com.imooc.homepage;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfo {

    private Long id;
    private String courseName;
    private String courseType;
    private String courseIcon;
    private String courseIntro;

    public static CourseInfo invaild(){
        return new CourseInfo(-1L,"","","","");
    }

}
