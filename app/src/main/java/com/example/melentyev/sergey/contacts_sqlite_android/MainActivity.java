package com.example.melentyev.sergey.contacts_sqlite_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //callDataBase();
    }

    void callDataBase() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS contacts;");
        db.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT);");
        db.execSQL("INSERT INTO contacts VALUES('Sergey', 8985, 'seg@mail.ru');");
        db.execSQL("INSERT INTO contacts VALUES('Olga', 8910, 'olga@mail.ru');");

        Cursor query = db.rawQuery("SELECT * FROM contacts;", null);
        if (query.moveToFirst()) {
            do {
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(this, name + " " + phone + " " + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());
        }

        query.close();
        db.close();
    }
}
