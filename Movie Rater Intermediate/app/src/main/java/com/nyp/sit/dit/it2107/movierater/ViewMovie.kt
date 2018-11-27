package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.view.View;
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

        titleText.text = intent.getStringExtra("title")
        OverviewText.text = intent.getStringExtra("overview")
        typelang.text = intent.getStringExtra("lang")
        datedText.text = intent.getStringExtra("rel")
        suitable.text = intent.getStringExtra("suit")
        if(intent.getStringExtra("activity") == "RateMovie"){
            reviewHere.text = intent.getStringExtra("comment")
            rateHere.visibility = View.VISIBLE
            rateHere.rating = intent.getStringExtra("rating").toFloat()
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
            viewIntent.putExtra("title", intent.getStringExtra("title"))
            viewIntent.putExtra("overview", intent.getStringExtra("overview"))
            viewIntent.putExtra("lang", intent.getStringExtra("lang"))
            viewIntent.putExtra("rel", intent.getStringExtra("rel"))
            viewIntent.putExtra("suit", intent.getStringExtra("suit"))
            startActivity(viewIntent)
        }
        return super.onContextItemSelected(item)
    }
}