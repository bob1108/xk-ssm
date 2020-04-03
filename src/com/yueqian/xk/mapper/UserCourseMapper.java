package com.yueqian.xk.mapper;


import com.yueqian.xk.beans.CourseDetailInfo;
import com.yueqian.xk.beans.UserCourseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * 用户课程dao
 *
 */

public interface UserCourseMapper {
    /**
     * 学员查看未选课课程
     * @param userId
     * @return
     */
    @Select(value="SELECT * FROM courses WHERE course_id NOT IN (( SELECT c.course_id FROM users u,courses c,usercourse uc"
            + " WHERE u.user_id = uc.user_id AND c.course_id = uc.course_id AND u.user_id = #{userId}))")
    List<CourseDetailInfo> unselectedCourses(int userId);


    /**
     * 学员选课
     * @param userCourseInfo
     * @return
     */
    @Insert(value = "INSERT INTO usercourse(user_id,course_id) VALUES (#{userId},#{courseId})")
    int addUserCourse(UserCourseInfo userCourseInfo);


    //学员查看已选课课程

    @Select(value="SELECT u.user_id,c.course_id,c.coursename,c.teacher,c.number FROM users u,courses c,usercourse uc" +
            "   WHERE u.user_id = uc.user_id AND c.course_id = uc.course_id AND u.user_id = #{userId}")
    List<CourseDetailInfo> selectedCourses(int userId);

}
