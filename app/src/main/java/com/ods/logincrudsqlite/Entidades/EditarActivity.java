package com.ods.logincrudsqlite.Entidades;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ods.logincrudsqlite.DBUsers;
import com.ods.logincrudsqlite.HomeActivity;
import com.ods.logincrudsqlite.LoginActivity;
import com.ods.logincrudsqlite.R;

public class EditarActivity extends AppCompatActivity {

    EditText username, password;
    Button btnsignup, vlista;
    boolean correcto = false;
    FloatingActionButton fabEditar, fabEliminar;

    Users contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnsignup = findViewById(R.id.btnsignup);
        vlista = (Button) findViewById(R.id.btnlista);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBUsers dbUsers = new DBUsers(EditarActivity.this);
        contacto = dbUsers.verContacto(id);
        if (contacto != null) {
            username.setText(contacto.getUsername());
            password.setText(contacto.getPassword());

        }

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    correcto = dbUsers.editarContacto(id, username.getText().toString(), password.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
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

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }


}
