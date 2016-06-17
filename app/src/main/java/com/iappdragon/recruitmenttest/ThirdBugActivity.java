package com.iappdragon.recruitmenttest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ThirdBugActivity extends Activity {
    Button buttonCapture;
    ImageView imageViewProfile;
    Bitmap bp;
    Bundle newBundy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdbug);
        newBundy = new Bundle();
        buttonCapture = (Button) findViewById(R.id.buttonCapture);
        imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);

        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }
    Intent databundle;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        newBundy.putAll(data.getExtras());
        imageViewProfile.setImageBitmap(bp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onSaveInstanceState(newBundy);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            onSaveInstanceState(newBundy);
        }
    }
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("newBundy", newBundy);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);        ;
        bp = (Bitmap) savedInstanceState.getBundle("newBundy").get("data");
        if(bp!=null) {
            imageViewProfile.setImageBitmap(bp);
        }


    }
}
