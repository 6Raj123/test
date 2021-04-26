package com.raj.mymusicapp.network

import com.raj.mymusicapp.model.MusicList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ccb7e548939c08442591771fb63b8de4"

interface ApiService {
    @GET("2.0/?method=artist.search&api_key=$API_KEY&format=json")
    fun getArtistData(@Query("artist")searchItem : String): Call<MusicList>

    @GET("2.0/?method=album.search&api_key=$API_KEY&format=json")
    fun getAlbumData(@Query("album")searchItem : String): Call<MusicList>

    @GET("2.0/?method=track.search&api_key=$API_KEY&format=json")
    fun getTrackData(@Query("track")searchItem : String): Call<MusicList>

}