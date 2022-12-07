package com.ods.logincrudsqlite.Entidades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ods.logincrudsqlite.DBUsers;
import com.ods.logincrudsqlite.HomeActivity;
import com.ods.logincrudsqlite.R;

public class VerActivity extends AppCompatActivity {

    EditText username, password;
    Button btnsignup, vlista;
    FloatingActionButton fabEditar, fabEliminar;

    Users contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        vlista = (Button) findViewById(R.id.btnlista);
        btnsignup = findViewById(R.id.btnsignup);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBUsers dbUsers = new DBUsers(VerActivity.this);
        contacto = dbUsers.verContacto(id);
        if (contacto != null) {
            username.setText(contacto.getUsername());
            password.setText(contacto.getPassword());
            username.setInputType(InputType.TYPE_NULL);
            password.setInputType(InputType.TYPE_NULL);

        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbUsers.eliminarContacto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        vlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

        });

    }
    private void lista(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}