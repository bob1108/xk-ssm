package com.yueqian.xk.mapper;

import com.yueqian.xk.beans.CourseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 课程dao操作数据库
 */
@Mapper
public interface CourseMapper {
    /**
     * 添加课程
     * @param courseInfo
     * @return
     */
    @Insert(value="INSERT INTO courses(coursename,teacher,number) VALUES(#{coursename},#{teacher},0)")
    int addCourse(CourseInfo courseInfo);

    /**
     * 查看所有已添加的课程
     * @return
     */
    @Select(value = "SELECT * FROM courses")
    List<CourseInfo> findCourses();

    //根据课程编号,修改选课人数(获取数据库原本的选课数量+1)
   @Update(value = "UPDATE courses SET number = number+1 WHERE course_id = #{courseId};")
    int updateCourse(int courseId);

   //删除课程信息
    @Delete(value = "DELETE FROM courses WHERE course_id = #{courseId}")
    int deleteCourse(int courseId);

    //更新课程信息,根据课程编号修改课程信息
    @Update(value = "")
    int updatedCourse(int courseId);


}
