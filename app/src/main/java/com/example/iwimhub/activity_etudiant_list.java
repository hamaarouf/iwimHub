package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iwimhub.Adapters.EtudiantAdapter;
import com.example.iwimhub.Models.etudiantModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class activity_etudiant_list extends AppCompatActivity {

    RecyclerView etudiantlist;
    ArrayList<etudiantModel> datalist;
    FirebaseFirestore db;
    EtudiantAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etudiantlist=(RecyclerView)findViewById(R.id.etudiantlist);
        etudiantlist.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adapter=new EtudiantAdapter(datalist);
        etudiantlist.setAdapter(adapter);

        db=FirebaseFirestore.getInstance();
        db.collection("etudiants").orderBy("nom").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            etudiantModel obj=d.toObject(etudiantModel.class);
                            datalist.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}