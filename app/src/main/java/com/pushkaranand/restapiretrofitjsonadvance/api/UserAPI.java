package com.pushkaranand.restapiretrofitjsonadvance.api;

import com.pushkaranand.restapiretrofitjsonadvance.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pushkar on 01-Jul-17.
 */

public interface UserAPI {

    @GET("/users")
    Call<ArrayList<User>> getUsers ();
}

