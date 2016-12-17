package com.cc.dc.rxjavademo;

/**
 * Created by dc on 16/5/1.
 */
public class Course {
    private String courseName;
    private String teacherName;

    public Course(String courseName, String teacherName) {
        this.courseName = courseName;
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
