package com.raj.mymusicapp.network

import com.raj.mymusicapp.model.MusicList


interface CallBackMainActivity {
       fun onSuccessResponse(musicList :  MusicList)
       fun onFailureResponse()
}