package com.example.kursovaya_rmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ArrayList<Group> groups = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private String currentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView date_view = findViewById(R.id.date);
        date_view.setText(setDate());
    }

    public void add(String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);
        boolean flag = false;
        for (int i = 0; i < groups.size(); i++) {
            if (groupName.equals(groups.get(i).getGroupName())){
                flag = true;
            }
        }
        if (!flag) {
            groups.add(group);
        }

        FileInputStream fis = null;
        try {
            fis = openFileInput(group.getGroupName()+".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String text;

            while ((text = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(text);
                group.appendStudent(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mAdapter = new Adapter(group.getStudent_list());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    public String setDate() {
        String pattern = "d MMM yyyy, EEE";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        return todayAsString;
    }

    public void groupSelect(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.group_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.IKBO0220:
            case R.id.IKBO1320:
            case R.id.IKBO2020:
                add(menuItem.getTitle().toString());
                TextView textView = findViewById(R.id.group);
                textView.setText(menuItem.getTitle().toString());
                currentGroup = menuItem.getTitle().toString();
                return true;
            default:
                return false;
        }
    }

    public void openStudent(View view) {
        TextView textView = (TextView) view;
        String text = textView.getText().toString();
        setContentView(R.layout.student_overlay);
        TextView student_name = findViewById(R.id.student_name);
        student_name.setText(text);
    }

    public void back(View view) {
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        TextView date_view = findViewById(R.id.date);
        date_view.setText(setDate());
        TextView textView = findViewById(R.id.group);
        textView.setText(currentGroup);
    }

    public void confirm_student(View view) {
        boolean isHere;
        Group group = null;
        Student student = null;
        Button btn = findViewById(R.id.Here);
        TextView tw = findViewById(R.id.student_name);
        String name = tw.getText().toString();
        EditText et = findViewById(R.id.mark);
        String mark = et.getText().toString();

        if (btn.getText().toString().equals("Присутствует")) isHere = true;
        else isHere = false;

        for (int i = 0; i < groups.size(); i++) if (currentGroup
                .equals(groups.get(i).getGroupName())) group = groups.get(i);

        for (int i = 0; i < group.getStudent_list().size(); i++)
            if (name.equals(group.getStudent_list().get(i).getName()))
                student = group.getStudent_list().get(i);

        student.isHere(isHere, mark, setDate());
        back(view);
    }

    public void changeTitle(View view) {
        Button btn = findViewById(R.id.Here);
        if (btn.getText().toString().equals("Присутствует")) btn.setText("Отсутствует");
        else if (btn.getText().toString().equals("Отсутствует")) btn.setText("Присутствует");
    }
}