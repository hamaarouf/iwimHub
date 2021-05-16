package com.example.iwimhub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DialogProf extends DialogFragment {
    private static final String arg_nom = "nom";
    private static final String arg_prenom = "prenom";
    private static final String arg_departement = "departement";
    private static final String arg_id = "id";


    private String nom;
    private String prenom;
    private String departement;
    private String id;

    int mNum;

    public static DialogProf newInstance(String nom, String prenom, String departement, String id) {
        Bundle args = new Bundle();
        args.putString(arg_nom, nom);
        args.putString(arg_prenom, prenom);
        args.putString(arg_departement, departement);
        args.putString(arg_id, id);

        DialogProf f = new DialogProf();

        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            nom = args.getString(arg_nom);
            prenom = args.getString(arg_prenom);
            departement = args.getString(arg_departement);
            id = args.getString(arg_id);
        }
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.professeur_item_detail_layout, container, false);
        TextView tv_nom = v.findViewById(R.id.nomprof1);
        TextView tv_prenom = v.findViewById(R.id.prenomprof1);
        TextView tv_departement = v.findViewById(R.id.departementprof1);

       Button btEnvoyer = v.findViewById(R.id.profmsg);

       tv_nom.setText(nom);
       tv_prenom.setText(prenom);
       tv_departement.setText(departement);

       btEnvoyer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), MessageSend.class);
               intent.putExtra("idTo", id);
               intent.putExtra("nomTo", nom);
               intent.putExtra("prenomTo", prenom);
               startActivity(intent);
           }
       });

        return v;
    }
}
