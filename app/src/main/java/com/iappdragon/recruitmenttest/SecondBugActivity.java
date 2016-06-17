package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iappdragon.recruitmenttest.database.DatabaseHelper;
import com.iappdragon.recruitmenttest.model.Contact;

import java.util.List;

public class SecondBugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_bug);
        TextView textView = (TextView) findViewById(R.id.textViewAllContacts);
        DatabaseHelper db = new DatabaseHelper(this);
        String data = "";
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            data = data + "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber() + "\n";
        }

        textView.setText(data);
    }
}
