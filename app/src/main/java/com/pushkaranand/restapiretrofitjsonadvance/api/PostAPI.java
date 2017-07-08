package com.pushkaranand.restapiretrofitjsonadvance.api;

import android.support.annotation.Nullable;

import com.pushkaranand.restapiretrofitjsonadvance.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pushkar on 02-Jul-17.
 */

public interface PostAPI {

//    @GET("/posts")
//    Call<ArrayList<Post>> getPosts ();

//    @GET("/posts/{id}")
//    Call<Post> getPostById (
//            @Path("id") int id
//    );

    @GET("/posts")
    Call<ArrayList<Post>> getPostsByUserId (
            @Nullable
            @Query("userId") Integer userId
    );
}
