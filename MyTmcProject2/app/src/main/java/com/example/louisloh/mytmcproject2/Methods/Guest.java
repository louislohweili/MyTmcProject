/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.louisloh.mytmcproject2.Methods;

/**
 * Created by louisloh on 25/4/2017.
 */

public class Guest {
    String Guest_id;
    String Name;
    String Phone;
    String Email;
    String Date_Time;
    String Event_Name;


    public Guest(String guest_id, String name, String phone, String email, String date_Time, String event_Name) {
        Guest_id = guest_id;
        Name = name;
        Phone = phone;
        Email = email;
        Date_Time = date_Time;
        Event_Name = event_Name;
    }

    public Guest(String name, String phone, String email, String date_time, String event_name) {
        Name = name;
        Phone = phone;
        Email = email;
        Date_Time = date_time;
        Event_Name = event_name;
    }

    public Guest() {

    }


    public String getGuest_id() {
        return Guest_id;
    }

    public void setGuest_id(String guest_id) {
        Guest_id = guest_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDate_Time() {
        return Date_Time;
    }

    public void setDate_Time(String date_Time) {
        Date_Time = date_Time;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }
}



