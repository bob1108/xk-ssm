package com.yueqian.xk.controller;

import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.*;
import com.yueqian.xk.service.CourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 课程控制层代码
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    //导入日志
    public static Logger logger = Logger.getLogger(CourseController.class);

    /**
     * 管理员添加课程
     * @param courseInfo：接收前端json数据，并将数据封装进courseInfo
     * @param model：给前端界面传递数据
     * @param session：session
     * @return
     */
    @PostMapping("/ajax/input")
    @ResponseBody
    public AjaxResponseInfo inputData(@RequestBody CourseInfo courseInfo, Model model, HttpSession session){
        logger.debug(courseInfo);
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //从session中取得当前登录系统的用户信息
        UserInfo user = (UserInfo)session.getAttribute("loginedUser");
        if(user==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("你还没有登录！");
        }else{
            //将数据保存到数据库
            int row = courseService.addCourse(courseInfo);
            if(row>0){
                responseInfo.setMsg("课程添加成功！");
            }else{
                responseInfo.setCode(-1);
                responseInfo.setMsg("课程添加失败！");
            }
        }
        return responseInfo;
    }

    /**
     * 管理员查看已添加的课程
     * @return
     */
//    @GetMapping("/ajax/findCourses")
//    @ResponseBody
//    public AjaxResponseInfo findCourses(){
//        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
//        List<CourseInfo> list = courseService.findCourseInfos();
//        if(list==null || list.size()==0){
//            responseInfo.setCode(-1);
//            responseInfo.setMsg("没有获取到数据！");
//        }else{
//            responseInfo.setMsg("课程查询成功！");
//            responseInfo.setData(list);
//        }
//        return responseInfo;
//    }

    /**
     * 管理员查看已添加的课程--使用了分页
     * @param pageNum:这是前台传过来的点前页码
     * @return
     */
    @GetMapping("/ajax/findCourses")
    @ResponseBody
    public AjaxResponseInfo pagefindCourses(@RequestParam(defaultValue = "1",required = true,value = "pageNum") Integer pageNum) {


        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        PageInfo<CourseInfo> list = courseService.findCourseInfos(pageNum,5);//每页显示5条

        if(list == null && list.getSize() ==0){
         responseInfo.setCode(-1);
         responseInfo.setMsg("没有获取到相关数据!");
        }else{
           responseInfo.setData(list);
        }
        return responseInfo;
    }

    /**
     * 学员查询未选课程
     * @param pageNum
     * @param userId
     * @return
     */

    @GetMapping("/ajax/unselectedCourses")
    @ResponseBody
    public AjaxResponseInfo unselectedCourses(@RequestParam(defaultValue = "1",required = true,value = "pageNum") Integer pageNum,
                                              @RequestParam(defaultValue = "null",required = true,value = "userId")  Integer userId) {


        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        PageInfo<CourseDetailInfo> list = courseService.unselectedCourse(userId, pageNum, 5);

        if (list == null && list.getSize() == 0) {
            responseInfo.setCode(-1);
            responseInfo.setMsg("没有获取到提示!");
        } else {
            responseInfo.setData(list);
        }

        return  responseInfo;

    }

    /**
     *学员选课
     * @param userCourseInfo
     * @param session
     * @return
     */
    @PostMapping("/ajax/selectCourses")
    @ResponseBody
    public AjaxResponseInfo selectCourses(@RequestBody UserCourseInfo userCourseInfo,HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
         //从session中获取当前登录的用户
        UserInfo user = (UserInfo)session.getAttribute("loginedUser");

        if(user == null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("您还未登录,请先登录!");
        }else{
           //保存选课数据
            int row = courseService.selectCourses(userCourseInfo);
            if(row <= 0){
                responseInfo.setCode(-1);
                responseInfo.setMsg("选课失败");
            }else{
               responseInfo.setMsg("恭喜:选课成功");
            }
        }
        return  responseInfo;
    }

    /**
     * 查看已选课程
     * @param pageNum
     * @param userId
     * @return
     */
    @GetMapping("/ajax/selectedCourses")
    @ResponseBody
   public  AjaxResponseInfo selectedCourse(@RequestParam(defaultValue = "1",required = true,value = "pageNum") Integer pageNum,
                                        @RequestParam(defaultValue = "null",required = true,value = "userId")  Integer userId){


        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        PageInfo<CourseDetailInfo> list = courseService.selectedCourse(userId,pageNum,5);
        if(list == null && list.getSize() == 0){

            responseInfo.setCode(-1);
            responseInfo.setMsg("没有获取到数据!");
        }else {
            responseInfo.setData(list);
        }
     return  responseInfo;
  }


    @PostMapping("/ajax/deleteCourse")
    @ResponseBody
    public AjaxResponseInfo deleteCourse(@RequestBody CourseInfo courseInfo) {
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        int row = courseService.deleteCourse(courseInfo);
        if(row>0){
            responseInfo.setMsg("课程删除成功！");
        }else{
            responseInfo.setCode(-1);
            responseInfo.setMsg("课程删除失败！");
        }
        return responseInfo;
    }


}
