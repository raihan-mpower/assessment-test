package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.iappdragon.recruitmenttest.model.Contact;

public class FirstBugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_bug);
        Contact contact=null;
        //Uncomment the below line
        contact= (Contact)getIntent().getExtras().get("contact");
        Toast.makeText(FirstBugActivity.this, contact.getName()+" ; "+contact.getPhoneNumber(), Toast.LENGTH_SHORT).show();
    }
}
