package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.Sampler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_rater.*
import android.widget.RadioGroup
import com.nyp.sit.dit.it2107.movierater.R.layout.view_movie
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.view_movie.*

class MovieRater : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rater)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.clear){
            name.setText("")
            desc.setText("")
            langChoice.check(-1)
            relDate.setText("")
            audience.isChecked = false
            violence.isChecked = false
            lanUsed.isChecked = false
        }
        if(item?.itemId == R.id.addM){
            var title:String = name.text.toString()
            var overview:String = desc.text.toString()
            var release:String = relDate.text.toString()

            var choice = langChoice.checkedRadioButtonId
            //find selected radio button
            var button = findViewById<RadioButton>(choice)

            var check:String = ""
            var checkVioLang:String = ""
            var aud:Boolean
            var valid:Boolean = true
            var suitMr:String

            if(choice < 0){
                tamil.error = "Select a language"
                valid = false
            }
            if(audience.isChecked){
                aud = true
                if(violence.isChecked){
                    check = violence.text.toString()
                    checkVioLang = violence.text.toString()
                    if(lanUsed.isChecked){
                        check = violence.text.toString() + "\n" + lanUsed.text.toString()
                        checkVioLang = violence.text.toString() + ", " + lanUsed.text.toString()
                    }
                }else if(lanUsed.isChecked){
                    check = lanUsed.text.toString()
                    checkVioLang = lanUsed.text.toString()
                }
                else{
                    audience.error = "Please select violence or language used"
                    valid = false
                }
                suitMr = "No"
            }else{
                aud = false
                suitMr = "Yes"
            }
            if(name.text.toString() == ""){
                name.error = "Field empty"
                valid = false
            }
            if(desc.text.toString() == ""){
                desc.error = "Field empty"
                valid = false
            }
            if(relDate.text.toString() == ""){
                relDate.error = "Field empty"
                valid = false
            }
            if(valid == true) {
                var total: String = "Title= " + title + "\n" + "Overview= " + overview + "\n" + "Release Date= " + release + "\n" + "Language = " + button.text + "\n" + "Suitable for all ages = " + aud.toString() + "\n" + "Reason = " + "\n" + check
                Toast.makeText(this, total, Toast.LENGTH_SHORT).show()
                movEntity = MovieEntity(title, overview, release, button.text.toString(), suitMr, "", "")
                var viewIntent = Intent(applicationContext, ViewMovie::class.java)
                startActivity(viewIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //checkbox onclick event
    fun suitable(v:View?) {
        if (audience.isChecked) {
            violence.visibility = View.VISIBLE
            lanUsed.visibility = View.VISIBLE
        } else {
            violence.visibility = View.INVISIBLE
            lanUsed.visibility = View.INVISIBLE
        }
    }

//    fun movieView(v:View){
//        var viewIntent = Intent(applicationContext, ViewMovie::class.java)
//        startActivity(viewIntent)
//    }
}
