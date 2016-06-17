package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iappdragon.recruitmenttest.database.DatabaseHelper;
import com.iappdragon.recruitmenttest.model.Contact;

public class HomeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ((Button) findViewById(R.id.buttonBug1)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonBug2)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonBug3)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonBug4)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonBug5)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonNewsfeed)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonCamera)).setOnClickListener(this);

        initializeDatabase();
    }

    private void initializeDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Naval", "0191111123"));
        db.addContact(new Contact("Tarik", "0181111123"));
        db.addContact(new Contact("Tonmoy", "0171111123"));
        db.addContact(new Contact("Fahim", "0161111133"));
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.buttonBug1:
                intent = new Intent(this, FirstBugActivity.class);
                Contact contact = new Contact(1, "Ali Hasan", "01234567890");
                //Uncomment the below line
                intent.putExtra("contact", contact);
                break;
            case R.id.buttonBug2:
                intent = new Intent(this, SecondBugActivity.class);
                break;
            case R.id.buttonBug3:
                intent = new Intent(this, ThirdBugActivity.class);
                break;
            case R.id.buttonBug4:
                intent = new Intent(this, ForthBugActivity.class);
                break;
            case R.id.buttonBug5:
                intent = new Intent(this, FifthBugActivity.class);
                break;
            case R.id.buttonNewsfeed:
//                intent = new Intent(this, CameraActivity.class);
                intent = new Intent(this,WebService_Activity.class);
                //implement your code
                break;
            case R.id.buttonCamera:
                intent = new Intent(this, CameraActivity.class);
//                intent = new Intent(this,WebService_Activity.class);
                //implement your code
                break;
            default:

        }
        startActivity(intent);


    }
}
