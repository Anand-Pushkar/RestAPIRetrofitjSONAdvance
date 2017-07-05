package com.pushkaranand.restapiretrofitjsonadvance.api;

import com.pushkaranand.restapiretrofitjsonadvance.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pushkar on 03-Jul-17.
 */

public interface AlbumAPI {

    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();
}
