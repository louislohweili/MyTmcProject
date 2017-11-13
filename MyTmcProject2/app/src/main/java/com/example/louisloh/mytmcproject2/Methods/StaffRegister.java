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

public class StaffRegister  {
    String Staff_id;
    String Staff_Name;
    String Staff_Email;
    String Staff_Password;

    public StaffRegister(String staff_id, String staff_Name, String staff_Email, String staff_Password) {
        Staff_id = staff_id;
        Staff_Name = staff_Name;
        Staff_Email = staff_Email;
        Staff_Password = staff_Password;
    }

    public StaffRegister(String staff_Name, String staff_Email, String staff_Password) {
        Staff_Name = staff_Name;
        Staff_Email = staff_Email;
        Staff_Password = staff_Password;
    }

    public StaffRegister() {

    }


    public String getStaff_id() {
        return Staff_id;
    }

    public void setStaff_id(String staff_id) {
        Staff_id = staff_id;
    }

    public String getStaff_Name() {
        return Staff_Name;
    }

    public void setStaff_Name(String staff_Name) {
        Staff_Name = staff_Name;
    }

    public String getStaff_Email() {
        return Staff_Email;
    }

    public void setStaff_Email(String staff_Email) {
        Staff_Email = staff_Email;
    }

    public String getStaff_Password() {
        return Staff_Password;
    }

    public void setStaff_Password(String staff_Password) {
        Staff_Password = staff_Password;
    }



}
