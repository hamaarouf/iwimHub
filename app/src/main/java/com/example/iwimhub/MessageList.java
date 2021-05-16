package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iwimhub.Adapters.MessageAdapter;
import com.example.iwimhub.Adapters.ProfesseurAdapter;
import com.example.iwimhub.Models.Message;
import com.example.iwimhub.Models.Professeur;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;
import java.util.List;

public class MessageList extends AppCompatActivity {
    private RecyclerView recyclerViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        recyclerViewMsg = findViewById(R.id.rv_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        LinkedList<Message> messages = new LinkedList<Message>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        MessageAdapter myAdapter = new MessageAdapter(messages,this);

        recyclerViewMsg.setHasFixedSize(true);
        recyclerViewMsg.setLayoutManager(layoutManager);
        recyclerViewMsg.setAdapter(myAdapter);

        Task<QuerySnapshot> msgRef = db.collection("messgaes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d : list){
                        Message p = d.toObject(Message.class);
                        messages.add(p);
                    }
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}