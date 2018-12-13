package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_rater.*
import kotlinx.android.synthetic.main.rate_movie.*
import kotlinx.android.synthetic.main.view_movie.*

class ViewMovie : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        titleText.text = movEntity.title
        OverviewText.text = movEntity.overview
        typelang.text = movEntity.choice
        datedText.text = movEntity.release
        suitable.text = movEntity.suit
        if(movEntity.rate != ""){
            reviewHere.text = movEntity.comment
            rateHere.visibility = View.VISIBLE
            rateHere.rating = movEntity.rate.toFloat()
        }

        registerForContextMenu(reviewHere)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v?.id == R.id.reviewHere){
            menu?.add(1, 1001, 1, "Add Review")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == 1001){
            var viewIntent = Intent(this, RateMovie::class.java)
            startActivity(viewIntent)
        }
        return super.onContextItemSelected(item)
    }
}