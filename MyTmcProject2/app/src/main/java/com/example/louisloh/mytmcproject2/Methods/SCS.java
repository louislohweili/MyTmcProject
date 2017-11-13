/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;

/**
 * Created by louisloh on 17/7/2017.
 */

public class SCS {
    String SCS_ID;
    String Name;
    String NRIC;
    String Address;
    String Phone;
    String Email_Address;
    String Course_of_Study;
    String Years_of_Graduation;

    public SCS(String SCS_ID, String name, String NRIC, String address, String phone, String email_Address, String course_of_Study, String years_of_Graduation) {
        this.SCS_ID = SCS_ID;
        Name = name;
        this.NRIC = NRIC;
        Address = address;
        Phone = phone;
        Email_Address = email_Address;
        Course_of_Study = course_of_Study;
        Years_of_Graduation = years_of_Graduation;
    }

    public SCS(String name, String NRIC, String address, String phone, String email_Address, String course_of_Study, String years_of_Graduation) {
        Name = name;
        this.NRIC = NRIC;
        Address = address;
        Phone = phone;
        Email_Address = email_Address;
        Course_of_Study = course_of_Study;
        Years_of_Graduation = years_of_Graduation;
    }

    public SCS() {

    }


    public String getSCS_ID() {
        return SCS_ID;
    }

    public void setSCS_ID(String SCS_ID) {
        this.SCS_ID = SCS_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }

    public String getCourse_of_Study() {
        return Course_of_Study;
    }

    public void setCourse_of_Study(String course_of_Study) {
        Course_of_Study = course_of_Study;
    }

    public String getYears_of_Graduation() {
        return Years_of_Graduation;
    }

    public void setYears_of_Graduation(String years_of_Graduation) {
        Years_of_Graduation = years_of_Graduation;
    }
}
