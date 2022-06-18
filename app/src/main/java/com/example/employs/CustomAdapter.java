package com.example.employs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, firstName, lastName, email, phone;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList firstName, ArrayList lastName,  ArrayList email, ArrayList phone){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.firstName.setText(String.valueOf(firstName.get(position)));
        holder.lastName.setText(String.valueOf(lastName.get(position)));
        holder.email.setText(String.valueOf(email.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));
        //passer une appel
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0"+phone));
                activity.startActivityForResult(i, 1);
            }
        });
        //envoyer un mail
        holder.sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                activity.startActivityForResult(intent, 1);
            }
        });
        //envoyer un sms
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:0"+phone));
                activity.startActivityForResult(intent, 1);
            }
        });
        //modifier les informations
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("firstName", String.valueOf(firstName.get(position)));
                intent.putExtra("lastName", String.valueOf(lastName.get(position)));
                intent.putExtra("email", String.valueOf(email.get(position)));
                intent.putExtra("phone", String.valueOf(phone.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView firstName;
        TextView lastName;
        TextView email;
        TextView phone;
        LinearLayout mainLayout;
        ImageButton call, sendEmail, message, edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.empId);
            firstName = itemView.findViewById(R.id.prénom);
            lastName = itemView.findViewById(R.id.nom);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.numtel);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            call = itemView.findViewById(R.id.call);
            sendEmail = itemView.findViewById(R.id.sendEmail);
            message = itemView.findViewById(R.id.message);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
