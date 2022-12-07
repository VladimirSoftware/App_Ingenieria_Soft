package com.ods.logincrudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ods.logincrudsqlite.Adaptadores.ListaUsersAdapter;
import com.ods.logincrudsqlite.Entidades.Users;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {



    RecyclerView listarContactos;
    ArrayList<Users> listaArrayUsers;

    public FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MainActivityPagos.class);
                startActivity(intent);
            }
        });

        listarContactos = findViewById(R.id.listUsers);
        listarContactos.setLayoutManager(new LinearLayoutManager(this));

        DBUsers dbUsers = new DBUsers(HomeActivity.this);

        listaArrayUsers = new ArrayList<>();

        ListaUsersAdapter adapter = new ListaUsersAdapter(dbUsers.mostrarContactos());
        listarContactos.setAdapter(adapter);

    }


}