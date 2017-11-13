/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;

/**
 * Created by louisloh on 7/8/2017.
 */

public class Lecture {
    String Lecture_ID;
    String Lecture_Name;
    String Lecture_Email;
    String Lecture_State;
    String Lecture_Course;
    String Lecture_Password;

    public Lecture(String lecture_ID, String lecture_Name, String lecture_Email, String lecture_State, String lecture_Course, String lecture_Password) {
        Lecture_ID = lecture_ID;
        Lecture_Name = lecture_Name;
        Lecture_Email = lecture_Email;
        Lecture_State = lecture_State;
        Lecture_Course = lecture_Course;
        Lecture_Password = lecture_Password;
    }

    public Lecture(String lecture_Name, String lecture_Email, String lecture_State, String lecture_Course, String lecture_Password) {
        Lecture_Name = lecture_Name;
        Lecture_Email = lecture_Email;
        Lecture_State = lecture_State;
        Lecture_Course = lecture_Course;
        Lecture_Password = lecture_Password;
    }

    public Lecture() {

    }

    public String getLecture_ID() {
        return Lecture_ID;
    }

    public void setLecture_ID(String lecture_ID) {
        Lecture_ID = lecture_ID;
    }

    public String getLecture_Name() {
        return Lecture_Name;
    }

    public void setLecture_Name(String lecture_Name) {
        Lecture_Name = lecture_Name;
    }

    public String getLecture_Email() {
        return Lecture_Email;
    }

    public void setLecture_Email(String lecture_Email) {
        Lecture_Email = lecture_Email;
    }

    public String getLecture_State() {
        return Lecture_State;
    }

    public void setLecture_State(String lecture_State) {
        Lecture_State = lecture_State;
    }

    public String getLecture_Course() {
        return Lecture_Course;
    }

    public void setLecture_Course(String lecture_Course) {
        Lecture_Course = lecture_Course;
    }

    public String getLecture_Password() {
        return Lecture_Password;
    }

    public void setLecture_Password(String lecture_Password) {
        Lecture_Password = lecture_Password;
    }
}
