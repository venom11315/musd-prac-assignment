package com.nyp.sit.dit.it2107.movierater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.View
import android.content.Intent
import android.view.MenuItem
import kotlinx.android.synthetic.main.to_add_movie.*

class AddMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.to_add_movie)
        registerForContextMenu(clickMe)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v?.id == R.id.clickMe){
            menu?.add(1, 1001, 1, "Add")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001){
            var viewIntent = Intent(this, MovieRater::class.java)
            startActivity(viewIntent)
        }
        return super.onContextItemSelected(item)
    }
}