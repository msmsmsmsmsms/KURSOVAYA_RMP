package com.example.kursovaya_rmp;



import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Group {
    private String groupName;
    private ArrayList<Student> student_list = new ArrayList<>();

    public Group() {
        this.setGroupName("ИКБО-13-20");
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Student> getStudent_list() {
        return student_list;
    }

    public void appendStudent(String name){
        Student student = new Student();
        student.setName(name);
        this.student_list.add(student);
    }

    public String getStudentsName() {
        return student_list.get(0).getName();
    }
}
