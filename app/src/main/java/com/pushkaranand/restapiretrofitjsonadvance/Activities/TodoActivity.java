package com.pushkaranand.restapiretrofitjsonadvance.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pushkaranand.restapiretrofitjsonadvance.R;
import com.pushkaranand.restapiretrofitjsonadvance.adapters.TodoAdapter;
import com.pushkaranand.restapiretrofitjsonadvance.api.TodoAPI;
import com.pushkaranand.restapiretrofitjsonadvance.models.Todos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoActivity extends AppCompatActivity {

    RecyclerView rvTodoList;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        rvTodoList = (RecyclerView)findViewById(R.id.rvTodoList);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(this, new ArrayList<Todos>());
        rvTodoList.setAdapter(todoAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        final TodoAPI todoAPI = retrofit.create(TodoAPI.class);

        Callback<ArrayList<Todos>> todoCallback = new Callback<ArrayList<Todos>>() {
            @Override
            public void onResponse(Call<ArrayList<Todos>> call, Response<ArrayList<Todos>> response) {

                todoAdapter.updateTodos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Todos>> call, Throwable t) {

            }
        };

        int todoIdReceived = getIntent().getIntExtra("userId", -1);

        if (todoIdReceived != -1) {
            todoAPI.getTodosByUserId(todoIdReceived).enqueue(todoCallback);
        } else {
            todoAPI.getTodosByUserId(null).enqueue(todoCallback);
        }
    }
}
