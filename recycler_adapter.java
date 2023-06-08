package com.example.volley_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.Userholder>{

    public recycler_adapter(Class<model_class> model_classClass) {
    }
    MainActivity mainActivity;
    ArrayList<model_class> detalist;
    public recycler_adapter(MainActivity mainActivity, ArrayList<model_class> detalist) {
    this.mainActivity = mainActivity;
    this.detalist=detalist;
    }

    @NonNull
    @Override
    public recycler_adapter.Userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.recycle_item,parent,false);
        Userholder userholder = new Userholder(view);
        return userholder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_adapter.Userholder holder, int position) {
        holder.namet.setText(detalist.get(position).getName());
        holder.emailt.setText(detalist.get(position).getEmail());
        holder.bodyt.setText(detalist.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return detalist.size();
    }

    public class Userholder extends RecyclerView.ViewHolder {
        TextView namet,emailt,bodyt;
        public Userholder(@NonNull View itemView) {
            super(itemView);
            namet=itemView.findViewById(R.id.name);
            emailt=itemView.findViewById(R.id.email);
            bodyt=itemView.findViewById(R.id.body);

        }
    }
}
