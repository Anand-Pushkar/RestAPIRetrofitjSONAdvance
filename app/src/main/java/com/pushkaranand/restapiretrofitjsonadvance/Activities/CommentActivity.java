package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.adapters.CommentAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.CommentAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    RecyclerView rvCommentsList;
    CommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rvCommentsList = (RecyclerView)findViewById(R.id.rvCommentsList);
        rvCommentsList.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this, new ArrayList<Comment>());
        rvCommentsList.setAdapter(commentAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        CommentAPI commentsAPI = retrofit.create(CommentAPI.class);

        int userIdReceived = getIntent().getIntExtra("userId",0);

        commentsAPI.getCommentsByPostId(userIdReceived).enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                commentAdapter.updateComments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });

    }
}
