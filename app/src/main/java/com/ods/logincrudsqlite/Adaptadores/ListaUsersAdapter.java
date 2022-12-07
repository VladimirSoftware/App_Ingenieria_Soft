package com.ods.logincrudsqlite.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ods.logincrudsqlite.Entidades.Users;
import com.ods.logincrudsqlite.Entidades.VerActivity;
import com.ods.logincrudsqlite.R;

import java.util.ArrayList;

public class ListaUsersAdapter extends RecyclerView.Adapter<ListaUsersAdapter.ContactoViewHolder> {

    ArrayList<Users> listaContatos;

    public ListaUsersAdapter (ArrayList<Users> listaContatos){
        this.listaContatos = listaContatos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users_item, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewName.setText(listaContatos.get(position).getUsername());
        holder.viewPass.setText(listaContatos.get(position).getPassword());


    }

    @Override
    public int getItemCount() {
       return listaContatos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewName, viewPass;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.viewName);
            viewPass = itemView.findViewById(R.id.viewPass);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaContatos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
