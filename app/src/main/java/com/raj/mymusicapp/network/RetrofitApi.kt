package com.raj.mymusicapp.network

import android.util.Log
import com.raj.mymusicapp.model.MusicList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitApi {

    companion object {
        fun getMusicListDataFromServer(retrofit: Retrofit?, callBackListener: CallBackMainActivity, searchCategory : String,searchItem : String) {
            val service = retrofit?.create(ApiService::class.java)
            val call : Call<MusicList>
            if(searchCategory.equals("Artist")) {
                call = service!!.getArtistData(searchItem)
            }
            else if(searchCategory.equals("Album")){
                call = service!!.getAlbumData(searchItem)
            }else
            {
                call = service!!.getTrackData(searchItem)
            }

            call.enqueue(object : Callback<MusicList> {
                override fun onResponse(
                    call: Call<MusicList>,
                    response: Response<MusicList>
                ) {
                    if (response.code() == 200 && response.body() != null) {
                        Log.d("TAG", "Success")
                        callBackListener.onSuccessResponse(response.body())
                    }else
                    callBackListener.onFailureResponse()
                }

                override fun onFailure(call: Call<MusicList>, t: Throwable) {
                    Log.d("TAG", "Failed")
                    callBackListener.onFailureResponse()
                }
            })
        }

    }
}