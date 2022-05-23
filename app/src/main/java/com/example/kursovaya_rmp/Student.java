package com.example.kursovaya_rmp;

import java.util.ArrayList;

public class Student extends Group{
    private String name;
    private ArrayList<Day> day_list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Day> getDay_list() {
        return day_list;
    }

    public void setDay() {
        Day today = new Day();
        today.setDate("23.05.2022");
        today.setMark(5);
        day_list.add(today);
    }
}
