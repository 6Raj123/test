package com.raj.mymusicapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raj.mymusicapp.model.DisplayMusicList
import com.raj.mymusicapp.model.MusicList
import kotlin.collections.ArrayList

class HomeActivityViewModel : ViewModel() {
    val displayMusicLists = MutableLiveData<List<DisplayMusicList>>()

    fun convertToDisplayData(lists : MusicList)
    {
        val musicListsArray = ArrayList<DisplayMusicList>()
        if(lists.results?.artistmatches != null)
        {
            lists.results?.artistmatches?.artist?.forEach()
            {
                val displayMusicList = DisplayMusicList()
                displayMusicList.name =it.name
                displayMusicList.listeners =it.listeners
                displayMusicList.url =it.url
                displayMusicList.image =it.image
                musicListsArray.add(displayMusicList)

            }

        }else if(lists.results?.albummatches != null)
        {
            lists.results?.albummatches?.album?.forEach()
            {
                val displayMusicList = DisplayMusicList()
                displayMusicList.name =it.name
                displayMusicList.listeners =it.artist
                displayMusicList.url =it.url
                displayMusicList.image =it.image
                musicListsArray.add(displayMusicList)

            }

        }else if(lists.results?.trackmatches != null)
        {
            lists.results?.trackmatches?.track?.forEach()
            {
                val displayMusicList = DisplayMusicList()
                displayMusicList.name =it.name
                displayMusicList.listeners =it.artist
                displayMusicList.url =it.url
                displayMusicList.image =it.image
                musicListsArray.add(displayMusicList)

            }

        }

        displayMusicLists.value = musicListsArray
    }

    fun isCategoryNotSelected(selectedCategory : String, defaultLabel : String) : Boolean
    {
        return selectedCategory.equals(defaultLabel)
    }
    fun isSearchItemEmpty(item : String) : Boolean
    {
        return item.isNullOrBlank()
    }
}
