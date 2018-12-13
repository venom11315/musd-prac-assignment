package com.nyp.sit.dit.it2107.movierater
import com.nyp.sit.dit.it2107.movierater.R.id.name
import java.util.*

class MovieEntity(title:String, overview:String, release:String, choice:String, suit:String, comment:String, rate:String){
    var title:String
    var overview:String
    var release:String
    var choice:String
    var suit:String
    var comment:String
    var rate:String

    init{
        this.title = title
        this.overview = overview
        this.release = release
        this.choice = choice
        this.suit = suit
        this.comment = comment
        this.rate = rate
    }
}

var movEntity = MovieEntity("", "", "", "", "", "", "")
var movieList = mutableListOf<MovieEntity>()