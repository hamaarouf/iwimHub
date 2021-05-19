package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button proflist;
    private Button studentslist;
    private Button messageslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proflist = findViewById(R.id.listprof);
        studentslist = findViewById(R.id.studentlist);
        messageslist = findViewById(R.id.messageslist);

        proflist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), professeurList.class));
            }
        });

        studentslist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),activity_etudiant_list.class));
            }
        });

        messageslist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MessageList.class));
            }
        });
    }
}