/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;

/**
 * Created by louisloh on 9/5/2017.
 */

public class StudentRegister {
    String Student_ID;
    String Student_Name;
    String Student_Email;
    String Student_enrollment_date;
    String Student_Type ;
    String Student_Course;
    String Student_Password;


    public StudentRegister(String student_Name, String student_Email, String student_enrollment_date, String student_Type, String student_Course, String student_Password) {
        Student_Name = student_Name;
        Student_Email = student_Email;
        Student_enrollment_date = student_enrollment_date;
        Student_Type = student_Type;
        Student_Course = student_Course;
        Student_Password = student_Password;
    }

    public StudentRegister() {

    }

    public String getStudent_Email() {
        return Student_Email;
    }

    public void setStudent_Email(String student_Email) {
        Student_Email = student_Email;
    }

    public String getStudent_Type() {
        return Student_Type;
    }

    public void setStudent_Type(String student_Type) {
        Student_Type = student_Type;
    }

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String student_ID) {
        Student_ID = student_ID;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getStudent_enrollment_date() {
        return Student_enrollment_date;
    }

    public void setStudent_enrollment_date(String student_enrollment_date) {
        Student_enrollment_date = student_enrollment_date;
    }

    public String getStudent_Course() {
        return Student_Course;
    }

    public void setStudent_Course(String student_Course) {
        Student_Course = student_Course;
    }

    public String getStudent_Password() {
        return Student_Password;
    }

    public void setStudent_Password(String student_Password) {
        Student_Password = student_Password;
    }
}
