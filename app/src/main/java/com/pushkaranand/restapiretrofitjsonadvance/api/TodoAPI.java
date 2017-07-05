package com.pushkaranand.restapiretrofitjsonadvance.api;

import android.support.annotation.Nullable;

import com.pushkaranand.restapiretrofitjsonadvance.models.Todos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pushkar on 02-Jul-17.
 */

public interface TodoAPI {

    @GET("/todos")
    Call<ArrayList<Todos>> getTodosByUserId(
            @Nullable
            @Query("userId") Integer userId
    );
}
