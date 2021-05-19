package com.example.iwimhub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwimhub.MessageList;
import com.example.iwimhub.Models.Message;
import com.example.iwimhub.R;

import java.util.LinkedList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{
    private LinkedList<Message> messages;
    private static Context context;

    public MessageAdapter(LinkedList<Message> messages, Context context) {
        this.messages= new LinkedList<Message>() ;
        this.messages = messages;
        this.context=context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageAdapter.MyViewHolder vh = new MessageAdapter.MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        holder.from.setText("From : "+messages.get(position).getFromFullname());
        holder.messageBody.setText("Message : "+messages.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView from;
        public TextView messageBody;

        public MyViewHolder(View itemLayoutView) {

            super(itemLayoutView);
            from = itemLayoutView.findViewById(R.id.from);
            messageBody = itemLayoutView.findViewById(R.id.messageBody);

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
