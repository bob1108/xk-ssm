package com.yueqian.xk.service;

import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.CourseDetailInfo;
import com.yueqian.xk.beans.CourseInfo;
import com.yueqian.xk.beans.UserCourseInfo;

import java.util.List;

/**
 * 课程业务接口
 */
public interface CourseService {
    /**
     * 管理员添加课程
     * @param courseInfo
     * @return
     */
    public int addCourse(CourseInfo courseInfo);

    /**
     * 管理员查看已添加的课程--没有分页功能
     * @return
     */
    public List<CourseInfo> findCourseInfos();

    /**
     *
     * @param pageNum:当前页码
     * @param pageSize:每页显示的条数
     * @return
     */
    public PageInfo<CourseInfo> findCourseInfos(Integer pageNum,Integer pageSize);

    /**
     * 学员查询未选课程
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CourseDetailInfo> unselectedCourse(int userId,Integer pageNum,Integer pageSize);


    /**
     * 学员选课
     * @param userCourseInfo
     * @return
     */
    public int selectCourses(UserCourseInfo userCourseInfo);

    /**
     *查看已选课程
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CourseDetailInfo> selectedCourse(int userId,Integer pageNum,Integer pageSize);

    public int deleteCourse(CourseInfo courseInfo);

}




