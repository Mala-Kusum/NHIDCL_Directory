package com.example.nhidcldirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nhidcldirectory.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder> {
    Context context;
    ArrayList<Employee> list;

    String Type;
    String email;

    public MAdapter(Context context, ArrayList<Employee> list) {
        this.context = context;
        this.list = list;
    }
    public void filterList(ArrayList<Employee> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MAdapter.MyViewHolder holder, int position) {
        Employee user = list.get(position);
        holder.name.setText(user.getName());
        holder.designation.setText("(" + user.getDesignation() + ")");
        holder.department.setText(user.getDepartment());
        holder.address.setText(user.getAddress());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getLandline());
        holder.mobile.setText(user.getMobile());
        /*holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                MainActivity.EMAIL = holder.email.getText().toString();
                SharedPreferences sharedPref = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
                Query query = noteRef.whereEqualTo(MainActivity.Email, sharedPref.getString("email", ""));
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            // QuerySnapshot doc=task.getResult();
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                DocID= doc.getId();
                                Toast.makeText(context,DocID, Toast.LENGTH_SHORT).show();
                                Type=doc.getData().get("TYPE").toString();
                                email=doc.getData().get("EMAIL").toString();
                                Toast.makeText(context,Type, Toast.LENGTH_SHORT).show();
                                Log.e("Type",Type);
                                if(Type.equals(new String("admin"))){
                                    Intent intent=new Intent(context,admin_profile_edit.class);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }
                });
//              if(Type.equals("admin")){
//                  Intent intent=new Intent(context,admin_profile_edit.class);
//                context.startActivity(intent);
//               }
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,designation,department,address,phone,mobile,email;
        LinearLayout card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            name=itemView.findViewById(R.id.name);
            designation=itemView.findViewById(R.id.designation);
            department=itemView.findViewById(R.id.department);
            address=itemView.findViewById(R.id.address);
            phone=itemView.findViewById(R.id.phone);
            mobile=itemView.findViewById(R.id.mobile);
            email=itemView.findViewById(R.id.email);
        }
    }
}
