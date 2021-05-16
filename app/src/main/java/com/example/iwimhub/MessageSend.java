package com.example.iwimhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwimhub.Models.Message;
import com.example.iwimhub.Models.Professeur;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessageSend extends AppCompatActivity {
    FirebaseAuth fAuth;
    private EditText message;
    private Button send;
    private FirebaseUser fUser;
    private  String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("Users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot keyId : dataSnapshot.getChildren()) {
                    if (keyId.child("id").getValue(String.class).equals(fUser.getUid())) {
                        fullname = keyId.child("fullname").getValue(String.class);
                        break;
                    }
                    else{
                        fullname = "else";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        message = findViewById(R.id.messagetoprof);
        send = findViewById(R.id.msgsend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String idTo = intent.getStringExtra("idTo");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                LinkedList<Message> msg = new LinkedList<Message>();

                CollectionReference msgs = db.collection("messages");
                List<Task<Void>> futures = new ArrayList<>();
                futures.add(msgs.document().set(new Message(fullname , idTo, message.getText().toString())));
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}





