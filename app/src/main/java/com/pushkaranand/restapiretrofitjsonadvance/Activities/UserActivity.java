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
import com.pushkaranand.restapiretrofitjsonadvance.adapters.UserAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.UserAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    RecyclerView rvUserList;
    public static final String TAG = "UACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Log.d(TAG, "onCreate: 2222222222222222222222222222222222");

        rvUserList = (RecyclerView) findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, new ArrayList<User>());
        rvUserList.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new OnItemCLickListener() {


            @Override
            public void onItemClick(int itemId, View clickedView) {

                switch (clickedView.getId()) {

                    case R.id.btn_showPosts :
                        Intent postActIntent = new Intent(UserActivity.this,
                                PostActivity.class);

                        postActIntent.putExtra("userId", itemId);
                        startActivity(postActIntent);
                        break;

                    case R.id.btn_showTodos :
                        Intent todoActIntent = new Intent(UserActivity.this,
                                TodoActivity.class);

                        todoActIntent.putExtra("userId", itemId);
                        startActivity(todoActIntent);
                        break;
                }
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        final UserAPI userAPI = retrofit.create(UserAPI.class);

        userAPI.getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                userAdapter.updateUsers(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
