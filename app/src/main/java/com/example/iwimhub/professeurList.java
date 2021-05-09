package com.example.iwimhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iwimhub.Adapters.ProfesseurAdapter;
import com.example.iwimhub.Models.Professeur;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class professeurList extends AppCompatActivity {
    private RecyclerView recyclerViewProfs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professeur_list);
        recyclerViewProfs = findViewById(R.id.rv_main);

        FirebaseFirestore  db = FirebaseFirestore.getInstance();
        LinkedList<Professeur>  professeurs = new LinkedList<Professeur>();
        //professeurs.add(new Professeur("hakima","maarouf","IWIM"));


        //fill data in the collection
        CollectionReference profs = db.collection("professeurs");
        List<Task<Void>> futures = new ArrayList<>();
        futures.add(profs.document("PR1").set(new Professeur("hakima","maarouf","IWIM")));
        futures.add(profs.document("PR2").set(new Professeur("asmae","lahlou","IWIM")));
        futures.add(profs.document("PR3").set(new Professeur("ahmed","alaoui","BI")));
        futures.add(profs.document("PR4").set(new Professeur("Said","alami","SSI")));


        //read data from the collection

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ProfesseurAdapter myAdapter = new ProfesseurAdapter(professeurs,this);

        recyclerViewProfs.setHasFixedSize(true);
        recyclerViewProfs.setLayoutManager(layoutManager);
        recyclerViewProfs.setAdapter(myAdapter);

        Task<QuerySnapshot> docRef = db.collection("professeurs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d : list){
                        Professeur p = d.toObject(Professeur.class);
                        professeurs.add(p);
                    }
                    myAdapter.notifyDataSetChanged();
                }
            }
        });



    }
}