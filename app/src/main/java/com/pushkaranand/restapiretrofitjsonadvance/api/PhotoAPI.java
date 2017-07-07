package com.pushkaranand.restapiretrofitjsonadvance.api;

import com.pushkaranand.restapiretrofitjsonadvance.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pushkar on 03-Jul-17.
 */

public interface PhotoAPI {

    @GET("/photos")
    Call<ArrayList<Photo>> getPhotosByAlbumId(
            @Query("albumId") int albumId);
            
}
