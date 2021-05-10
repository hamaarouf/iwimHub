package com.example.iwimhub.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwimhub.DialogProf;
import com.example.iwimhub.Models.Professeur;
import com.example.iwimhub.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;

public class ProfesseurAdapter  extends RecyclerView.Adapter<ProfesseurAdapter.MyViewHolder>{

    private LinkedList<Professeur> professeurs;
    private static Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProfesseurAdapter(LinkedList<Professeur> professeurs, Context context) {
        this.professeurs = new LinkedList<Professeur>() ;
        this.professeurs  = professeurs;
        this.context=context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.professeur_item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.nomProfesseur.setText(professeurs.get(position).getNom());
        holder.prenomProfesseur.setText(professeurs.get(position).getPrenom());
        holder.departement.setText(professeurs.get(position).getDepartement());
    }

    @Override
    public int getItemCount() {
        return professeurs.size();
    }

  public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nomProfesseur;
    public TextView prenomProfesseur;
    public TextView departement;
    public ImageView photo;

    public MyViewHolder(View itemLayoutView) {

        super(itemLayoutView);
        //this.context=context;
        nomProfesseur =itemLayoutView.findViewById(R.id.nomprof);
        prenomProfesseur =itemLayoutView.findViewById(R.id.prenomprof);
        departement =itemLayoutView.findViewById(R.id.departementprof);
        photo= itemLayoutView.findViewById(R.id.photoprof);

        itemLayoutView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        FragmentActivity activity = (FragmentActivity)(context);
        FragmentManager fm = activity.getSupportFragmentManager();
        DialogProf f= DialogProf.newInstance("détails ", "détails du professeur", "envoyer ");
        f.show(fm, null);
    }
}
}
