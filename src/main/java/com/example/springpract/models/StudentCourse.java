package com.example.springpract.models;

import java.util.List;

public class StudentCourse {
    String studentName;
    String courseName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    String teacherName;
    List<Integer> grades;

    public StudentCourse(String studentName, String courseName, List<Integer> grades) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.grades = grades;
    }

    public StudentCourse(){}
}
