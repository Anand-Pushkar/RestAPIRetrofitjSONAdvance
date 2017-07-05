package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnPhotoClickListener;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.adapters.PhotoAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.PhotoAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView rvPhotoList;
    PhotoAdapter photoAdapter;
    int albumIdReceived;
    Photo photo;

    public  static final String TAG = "PhotoAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);

        Intent i = getIntent();
        albumIdReceived = i.getIntExtra("albumId",-1);

        Log.d(TAG, "onCreate: " + albumIdReceived);
        Log.d(TAG, "onCreate: Photo Activity started");

        rvPhotoList = (RecyclerView)findViewById(R.id.rvAlbumList);
        rvPhotoList.setLayoutManager(new LinearLayoutManager(this));
        photoAdapter = new PhotoAdapter(this, new ArrayList<Photo>());
        rvPhotoList.setAdapter(photoAdapter);

        photoAdapter.setOnPhotoClickListener(new OnPhotoClickListener() {
            @Override
            public void onPhotoClick(int itemId, String url) {

                Log.d(TAG, "onPhotoClick: OnPhotoClickListener");

                Intent i = new Intent(PhotoActivity.this, PhotoEnlargeActivity.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });

        Log.d(TAG, "onCreate: Adapter has been set");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        PhotoAPI photoAPI = retrofit.create(PhotoAPI.class);

        Log.d(TAG, "onCreate: Object of PhotoAPI created");



        photoAPI.getPhotosByAlbumId(albumIdReceived).enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {

                photoAdapter.updatePhoto(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });
    }
}
