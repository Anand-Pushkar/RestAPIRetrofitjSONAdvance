package com.pushkaranand.restapiretrofitjsonadvance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pushkaranand.restapiretrofitjsonadvance.Activities.AlbumActivity;
import com.pushkaranand.restapiretrofitjsonadvance.Activities.PostActivity;
import com.pushkaranand.restapiretrofitjsonadvance.Activities.TodoActivity;
import com.pushkaranand.restapiretrofitjsonadvance.Activities.UserActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvUsers, tvPosts, tvAlbums, tvTodos;
    public static final String TAG = "MainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsers = (TextView) findViewById(R.id.tvUsers);
        tvPosts = (TextView) findViewById(R.id.tvPosts);
        tvAlbums = (TextView) findViewById(R.id.tvAlbums);
        tvTodos = (TextView) findViewById(R.id.tvTodos);

        tvUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                startActivity(i);
            }
        });

        tvPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PostActivity.class);
                startActivity(i);
            }
        });

        tvTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(i);
            }
        });

        tvAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: creating intent for AlbumActivity");
                Intent i = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(i);
            }
        });
    }
}
