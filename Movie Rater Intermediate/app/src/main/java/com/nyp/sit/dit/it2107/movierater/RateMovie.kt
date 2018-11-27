package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.rate_movie.*

class RateMovie : AppCompatActivity(){
    var rate:Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rate_movie)
        //get rating changed value method must be placed in onCreate function
        ratingBar.onRatingBarChangeListener = object: RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                rate = rating
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rate_star, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.submit){
            var viewIntent = Intent(this, ViewMovie::class.java)
            viewIntent.putExtra("rating", rate.toString())
            viewIntent.putExtra("comment", shareView.text.toString())
            viewIntent.putExtra("activity", "RateMovie")
            viewIntent.putExtra("title", intent.getStringExtra("title"))
            viewIntent.putExtra("overview", intent.getStringExtra("overview"))
            viewIntent.putExtra("lang", intent.getStringExtra("lang"))
            viewIntent.putExtra("rel", intent.getStringExtra("rel"))
            viewIntent.putExtra("suit", intent.getStringExtra("suit"))
            startActivity(viewIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}