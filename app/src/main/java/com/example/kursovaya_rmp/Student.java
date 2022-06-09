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

    public void isHere(boolean state, String mark, String date) {
        Day today = new Day();
        if (state) {
            today.setDate(date);
            today.setMark(mark);
            boolean flag = false;
            for (int i = 0; i < day_list.size(); i++) {
                if (date.equals(day_list.get(i).getDate())) {
                    flag = true;
                }
            }
            if (!flag) {
                day_list.add(today);
            }
        }
        else {
            today.setDate(date + " - отсутствует");
            today.setMark(mark);
            day_list.add(today);
        }
    }

}