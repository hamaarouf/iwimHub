package com.example.iwimhub.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwimhub.Models.etudiantModel;
import com.example.iwimhub.R;
import com.example.iwimhub.activity_etudiant_details;

import java.util.ArrayList;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.myviewholder>
{
    ArrayList<etudiantModel> datalist;

    public EtudiantAdapter(ArrayList<etudiantModel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.etudiant_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.item1.setText(datalist.get(position).getNom());
        holder.item2.setText(datalist.get(position).getPrenom());
        holder.item3.setText(datalist.get(position).getFiliere());
        holder.item4.setText(datalist.get(position).getAnnee());
        holder.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.item1.getContext(), activity_etudiant_details.class);
                intent.putExtra("unom", datalist.get(position).getNom());
                intent.putExtra("uprenom", datalist.get(position).getPrenom());
                intent.putExtra("ufiliere", datalist.get(position).getFiliere());
                intent.putExtra("uannee", datalist.get(position).getAnnee());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.item1.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView item1,item2,item3,item4;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            item1=itemView.findViewById(R.id.item1);
            item2=itemView.findViewById(R.id.item2);
            item3=itemView.findViewById(R.id.item3);
            item4=itemView.findViewById(R.id.item4);
        }
    }
}
