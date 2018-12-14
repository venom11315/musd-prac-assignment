package com.nyp.sit.dit.it2107.movierater

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.graphics.Movie
import android.media.Image
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.to_add_movie.*
import java.util.zip.Inflater
import android.widget.AdapterView.AdapterContextMenuInfo
import com.nyp.sit.dit.it2107.movierater.R.id.icon
import com.nyp.sit.dit.it2107.movierater.R.id.listView1
import kotlinx.android.synthetic.main.view_movie.*


class AddMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.to_add_movie)
        val movieTitleList = mutableListOf<String>()
        for(titles in movieList){
            movieTitleList.add(titles.title)
        }
        //val adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, movieTitleList)
        listView1.adapter = CustomAdaptor(this, movieTitleList)
        listView1.setOnItemClickListener{parent, view, position, id ->
            val SI = movieList.get(position)
            movEntity = MovieEntity(SI.title, SI.overview, SI.release, SI.choice, SI.suit, SI.comment, SI.rate)
            val viewIntent = Intent(applicationContext, ViewMovie::class.java)
            viewIntent.putExtra("mainact", "main")
            viewIntent.putExtra("itemPos", position.toString())
            startActivity(viewIntent)
        }

        registerForContextMenu(listView1)
    }

    class CustomAdaptor(private val context:Activity, private val myList: MutableList<String>):BaseAdapter(){
        override fun getItem(position: Int): Any {
            return myList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return myList.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.list_view, null)
            val list_imageView = rowView.findViewById<ImageView>(R.id.listIcon)
            val list_textView = rowView.findViewById<TextView>(R.id.listText)
            list_imageView.setImageResource(R.mipmap.icon)
            list_textView.setText(myList[position])
            return rowView
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.start_rate_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.addMov){
            val viewIntent = Intent(this, MovieRater::class.java)
            viewIntent.putExtra("prevAct", "toAddMov")
            startActivity(viewIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    var itemMenuIndex = 0
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v?.id == R.id.listView1){
            //contains position of the option that is selected
            val acmi = menuInfo as AdapterContextMenuInfo
            itemMenuIndex = acmi.position
            var obj = listView1.getItemAtPosition(itemMenuIndex)
            menu?.add(1, 1001, 1, "Edit")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == 1001){
            var indexPos = ""
            for(i in movieList){
                if(movieList.indexOf(i).toString() == itemMenuIndex.toString()){
                    indexPos = itemMenuIndex.toString()
                    movEntity = MovieEntity(i.title, i.overview, i.release, i.choice, i.suit, i.comment, i.rate)
                }
            }
            val viewIntent = Intent(applicationContext, MovieRater::class.java)
            viewIntent.putExtra("prevAct", "toEditMov")
            viewIntent.putExtra("indexPos", indexPos)
            startActivity(viewIntent)
        }
        return super.onContextItemSelected(item)
    }
}
