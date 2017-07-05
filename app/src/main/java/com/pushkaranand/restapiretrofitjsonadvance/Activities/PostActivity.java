package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pushkaranand.restapiretrofitjsonadvance.Interface.OnItemCLickListener;
import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.adapters.PostAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.PostAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    RecyclerView rvPostList;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        rvPostList = (RecyclerView)findViewById(R.id.rvPostList);
        rvPostList.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this, new ArrayList<Post>());
        rvPostList.setAdapter(postAdapter);

        postAdapter.setOnItemClickListener(new OnItemCLickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                Intent i = new Intent(PostActivity.this,
                        CommentActivity.class);

                i.putExtra("userId", itemId);
                startActivity(i);
            }

        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        final PostAPI postAPI = retrofit.create(PostAPI.class);

        PostAPI postsAPI = retrofit.create(PostAPI.class);
        Callback<ArrayList<Post>> postCallback = new Callback<ArrayList<Post>>() {

            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                //Log.d(TAG, "onResponse: ");
                postAdapter.updatePosts(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("userId", -1);
        if (userIdReceived != -1) {
            postsAPI.getPostsByUserId(userIdReceived).enqueue(postCallback);
        } else {
            postsAPI.getPostsByUserId(null).enqueue(postCallback);
        }

    }
}
