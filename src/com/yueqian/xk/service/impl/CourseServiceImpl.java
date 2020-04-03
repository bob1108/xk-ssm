package com.yueqian.xk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.CourseDetailInfo;
import com.yueqian.xk.beans.CourseInfo;
import com.yueqian.xk.beans.UserCourseInfo;
import com.yueqian.xk.mapper.CourseMapper;
import com.yueqian.xk.mapper.UserCourseMapper;
import com.yueqian.xk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserCourseMapper userCourseMapper;
    /**
     * 管理员添加课程
     * @param courseInfo
     * @return
     */
    @Override
    public int addCourse(CourseInfo courseInfo) {
        return courseMapper.addCourse(courseInfo);
    }

    /**
     * 管理员查看已添加的课程
     *
     * @return
     */
    @Override
    public List<CourseInfo> findCourseInfos() {
        return courseMapper.findCourses();
    }

    /**
     *
     * @param pageNum:当前页码
     * @param pageSize:每页显示的条数
     * @return
     */
    @Override
    public PageInfo<CourseInfo> findCourseInfos(Integer pageNum, Integer pageSize) {
        //初始化pageHelper
        PageHelper.startPage(pageNum,pageSize);
        //正常数据查询
        List<CourseInfo> list = courseMapper.findCourses();
        //将查询出来的数据进行分页处理
         PageInfo<CourseInfo> pageInfo = new PageInfo<>(list);
         return  pageInfo;
    }

    /**
     * 学员查询未选课程
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CourseDetailInfo> unselectedCourse(int userId, Integer pageNum, Integer pageSize) {
       PageHelper.startPage(pageNum,pageSize);
       List<CourseDetailInfo> list = userCourseMapper.unselectedCourses(userId);
       PageInfo<CourseDetailInfo> pageInfo = new PageInfo<>(list);
       return pageInfo;

    }

    /**
     * 学员选课
     * @param userCourseInfo
     * @return
     */
    @Override
    public int selectCourses(UserCourseInfo userCourseInfo) {
        courseMapper.updateCourse(userCourseInfo.getCourseId());//根据课程编号更新选课人数
        return userCourseMapper.addUserCourse(userCourseInfo);
    }

    /**
     * 查看已选课程
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CourseDetailInfo> selectedCourse(int userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseDetailInfo> list = userCourseMapper.selectedCourses(userId);
        PageInfo<CourseDetailInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deleteCourse(CourseInfo courseInfo) {
        return courseMapper.deleteCourse(courseInfo.getCourseId());
    }
}



