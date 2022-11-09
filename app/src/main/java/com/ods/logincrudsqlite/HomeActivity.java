package com.ods.logincrudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ods.logincrudsqlite.Adaptadores.ListaUsersAdapter;
import com.ods.logincrudsqlite.Entidades.Users;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView listarContactos;
    ArrayList<Users> listaArrayUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listarContactos = findViewById(R.id.listUsers);
        listarContactos.setLayoutManager(new LinearLayoutManager(this));

        DBUsers dbUsers = new DBUsers(HomeActivity.this);

        listaArrayUsers = new ArrayList<>();

        ListaUsersAdapter adapter = new ListaUsersAdapter(dbUsers.mostrarContactos());
        listarContactos.setAdapter(adapter);
    }
}