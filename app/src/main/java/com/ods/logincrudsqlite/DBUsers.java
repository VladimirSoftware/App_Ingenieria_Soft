package com.ods.logincrudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ods.logincrudsqlite.Entidades.Users;

import java.util.ArrayList;

public class DBUsers extends DBHelper {

    Context context;

    public DBUsers(Context context) {
        super(context);
    }


    public ArrayList<Users> mostrarContactos() {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = this.getWritableDatabase();


        ArrayList<Users> listaContactos = new ArrayList<>();
        Users contacto;
        Cursor cursorContactos;

        cursorContactos = MyDB.rawQuery("SELECT * FROM " + TABLEU + " ORDER BY username ASC", null);

        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Users();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setUsername(cursorContactos.getString(1));
                contacto.setPassword(cursorContactos.getString(2));

                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

    public Users verContacto(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Users contacto = null;
        Cursor cursorContactos;

        cursorContactos = MyDB.rawQuery("SELECT * FROM " + TABLEU + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Users();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setUsername(cursorContactos.getString(1));
            contacto.setPassword(cursorContactos.getString(2));

        }

        cursorContactos.close();

        return contacto;
    }


    public boolean editarContacto(int id, String username, String password) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = this.getWritableDatabase();

        try {
            MyDB.execSQL("UPDATE " + TABLEU + " SET username = '" + username + "', password = '" + password + "' WHERE id = '" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            MyDB.close();
        }

        return correcto;
    }
    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = this.getWritableDatabase();

        try {
            MyDB.execSQL("DELETE FROM " + TABLEU + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            MyDB.close();
        }

        return correcto;
    }
}