/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;

/**
 * Created by louisloh on 12/5/2017.
 */

public class Timetable {
    String Timetable_ID;
    String Course_Name;
    String Date_Time;
    String Classroom;
      String Lecture_Name;

    public Timetable(String timetable_ID, String course_Name, String date_Time, String classroom, String lecture_Name) {
        Timetable_ID = timetable_ID;
        Course_Name = course_Name;
        Date_Time = date_Time;
        Classroom = classroom;
        Lecture_Name = lecture_Name;
    }

    public Timetable(String course_Name, String date_Time, String classroom, String lecture_Name) {
        Course_Name = course_Name;
        Date_Time = date_Time;
        Classroom = classroom;
        Lecture_Name = lecture_Name;
    }

    public Timetable() {

    }


    public String getTimetable_ID() {
        return Timetable_ID;
    }

    public void setTimetable_ID(String timetable_ID) {
        Timetable_ID = timetable_ID;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getDate_Time() {
        return Date_Time;
    }

    public void setDate_Time(String date_Time) {
        Date_Time = date_Time;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public String getLecture_Name() {
        return Lecture_Name;
    }

    public void setLecture_Name(String lecture_Name) {
        Lecture_Name = lecture_Name;
    }
}