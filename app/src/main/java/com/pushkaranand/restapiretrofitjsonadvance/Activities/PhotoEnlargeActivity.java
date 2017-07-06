package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoEnlargeActivity extends AppCompatActivity {

    private static final String TAG = "PEActivity";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_enlarge);

        imageView = (ImageView)findViewById(R.id.ivPhoto);


        Intent i = getIntent();
        String url = i.getStringExtra("url");
        Log.d(TAG, "onCreate: Intent Received");

        if(url != null) {
            Picasso.with(this).load(url).into(imageView);
            Log.d(TAG, "onCreate: Photo Set");
        }
        else {
            Picasso.with(this).load("https://www.google.co.in/imgres?imgurl=http%3A%2F%2Fwonkville.net%2Fwp-content%2Fuploads%2F2016%2F04%2FNo-image-available.jpg&imgrefurl=http%3A%2F%2Fwonkville.net%2F2016%2F04%2F18%2Fshetlands-man-pulls-hammer-from-pants%2F&docid=_qjOHkUZTI84IM&tbnid=c9HhSMkB_LemLM%3A&vet=10ahUKEwjc9ozShvLUAhWLto8KHeXlCMYQMwhHKAwwDA..i&w=300&h=300&bih=735&biw=1536&q=no%20photo%20available&ved=0ahUKEwjc9ozShvLUAhWLto8KHeXlCMYQMwhHKAwwDA&iact=mrc&uact=8").into(imageView);
            // a default photo.
            Log.d(TAG, "onCreate: Photo Set");

        }

    }
}
