package com.example.kursovaya_rmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private TextView textViewId;
    private TextView textViewName;
    private ArrayList<Group> groups = new ArrayList<>();
    Group group = new Group();


    private final RecyclerView.Adapter mAdapter = new Adapter(group.getStudent_list());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group.setGroupName("ИКБО-13-20");

        setDate();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

    }

    public void add(View view) {
        group.appendStudent("Шувалов-Лужин Матвей ");
        mAdapter.notifyItemInserted(group.getStudent_list().size()-1);
    }

    public void setDate() {
        String pattern = "d MMM yyyy, EEE";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        TextView date_view = findViewById(R.id.date);
        date_view.setText(todayAsString);
    }
}