package com.pushkaranand.restapiretrofitjsonadvance.api;

import com.pushkaranand.restapiretrofitjsonadvance.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pushkar on 02-Jul-17.
 */

public interface CommentAPI {

    @GET("/comments")
    Call<ArrayList<Comment>> getComments();

    @GET("/comments")
    Call<ArrayList<Comment>> getCommentsByPostId (
            @Query("postId") int postId
    );

}
