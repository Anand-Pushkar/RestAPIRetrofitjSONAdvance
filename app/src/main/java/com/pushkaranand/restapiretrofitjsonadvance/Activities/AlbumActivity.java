package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.MainActivity;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.adapters.AlbumAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.AlbumAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {

    RecyclerView rvAlbumList;
    AlbumAdapter albumAdapter;
    ArrayList<Album> albums = new ArrayList<Album>();
    /**
     *  if we don't create arraylist using new ArrayList<Album>() then there will be no arraylist in existence,
     *  what we will have would be a reference to an arraylist that doesn't exist.
     *  Arraylist is a class whose object is albums and in java we create object with new keyword  else thy point to null.
     *
     */

    public static final String TAG = "AlbumAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Log.d(TAG, "onCreate: AlbumActivity started");

        rvAlbumList = (RecyclerView)findViewById(R.id.rvAlbumList);
        rvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        albumAdapter = new AlbumAdapter(this, albums);
        rvAlbumList.setAdapter(albumAdapter);

        Log.d(TAG, "onCreate: adapter has been set");


        albumAdapter.setOnItemClickListener(new OnItemCLickListener() {

            @Override
            public void onItemClick(int itemId, View clickedView) {

                Log.d(TAG, "onItemClickAlbums: Intent for PhotoActivity is being created");
                Intent i = new Intent(AlbumActivity.this, PhotoActivity.class);
                i.putExtra("albumId",itemId);
                startActivity(i);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        AlbumAPI albumAPI = retrofit.create(AlbumAPI.class);
        Log.d(TAG, "onCreate: object of albumAPI created");

        albumAPI.getAlbums().enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {

                albumAdapter.updateAlbums(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }
}
