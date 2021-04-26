package com.raj.mymusicapp.model

import lombok.Data

@Data
class DisplayMusicList {
    var name: String = ""
    var listeners: String = ""
    var url: String = ""
    var image: List<Image>? = null

}