package com.raj.mymusicapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import lombok.Data

@Data
public class MusicList {
    var results: Results? = null
}


class Track {
    var name: String = ""

    var artist: String = ""

    var url: String = ""

    var image: List<Image>? = null

}


class Trackmatches {
    var track: List<Track>? = null

}

class Album {
    var name: String = ""

    var artist: String = ""

    var url: String = ""

    var image: List<Image>? = null

}


class Albummatches {
    var album: List<Album>? = null

}


class Artist {
    var name: String = ""

    var listeners: String = ""

    var mbid: String = ""

    var url: String = ""

    var streamable: String = ""

    var image: List<Image>? = null

}



class Artistmatches {
    var artist: List<Artist>? = null

}

class Attr {
    var `for`: String? = null

}

class Example {
    var results: Results? = null

}

class Image {
    @SerializedName("#text")
    @Expose
    var text: String? = null

    var size: String? = null

}

class OpensearchQuery {
    var text: String? = null

    var role: String? = null

    var searchTerms: String? = null

    var startPage: String? = null

}

 class Results {
    var opensearchQuery: OpensearchQuery? = null

    var opensearchTotalResults: String? = null

    var opensearchStartIndex: String? = null

    var opensearchItemsPerPage: String? = null

    var artistmatches: Artistmatches? = null

    var albummatches: Albummatches? = null

    var trackmatches: Trackmatches? = null

    var attr: Attr? = null

}

