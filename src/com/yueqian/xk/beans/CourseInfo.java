package com.yueqian.xk.beans;

/**
 * 课程实体类
 */
public class CourseInfo {
    private Integer courseId;
    private String coursename;//课程名称
    private String teacher;//授课教师
    private Integer number;//选课人数

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseId=" + courseId +
                ", coursename='" + coursename + '\'' +
                ", teacher='" + teacher + '\'' +
                ", number=" + number +
                '}';
    }
}
