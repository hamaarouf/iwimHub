package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_etudiant_details extends AppCompatActivity {

    EditText item1,item2,item3,item4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_details);

        item1=(EditText)findViewById(R.id.nometud);
        item2=(EditText)findViewById(R.id.prenometud);
        item3=(EditText)findViewById(R.id.filiereetud);
        item4=(EditText)findViewById(R.id.anneeetud);
        btn=(Button)findViewById(R.id.buttonback);

        item1.setText(getIntent().getStringExtra("unom").toString());
        item2.setText(getIntent().getStringExtra("uprenom").toString());
        item3.setText(getIntent().getStringExtra("ufiliere").toString());
        item4.setText(getIntent().getStringExtra("uannee").toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }
}