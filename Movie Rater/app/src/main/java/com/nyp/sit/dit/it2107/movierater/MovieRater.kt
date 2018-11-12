package com.nyp.sit.dit.it2107.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.Sampler
import android.view.View;
import android.widget.RadioButton
import android.widget.Toast;
import kotlinx.android.synthetic.main.activity_movie_rater.*
import android.widget.RadioGroup
import com.nyp.sit.dit.it2107.movierater.R.layout.view_movie
import kotlinx.android.synthetic.*

class MovieRater : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rater)
    }

    fun addMovie(v:View?){
        var title:String = "Title = "+name.text
        var overview:String = "Overview = "+desc.text
        var release:String = "Release date = "+relDate.text

        var choice = langChoice.checkedRadioButtonId
        //find selected radio button
        var button = findViewById<RadioButton>(choice)

        var check:String = "";
        var aud:Boolean;
        if(audience.isChecked){
            aud = true;
            if(violence.isChecked){
                check = violence.text.toString()
                if(lanUsed.isChecked){
                    check = violence.text.toString() + "\n" + lanUsed.text.toString()
                }
            }else{
                check = lanUsed.text.toString()
            }
        }else{
            aud = false;
        }
        if(name.text.toString() == ""){
            name.setError("Field empty")
        }
        if(desc.text.toString() == ""){
            desc.setError("Field empty")
        }
        if(relDate.text.toString() == ""){
            relDate.setError("Field empty")
        }else{
            var total:String = title+"\n"+overview+"\n"+release+"\n"+"Language = "+button.text+"\n"+"Suitable for all ages = "+aud.toString()+"\n"+"Reason = "+"\n"+check;
            Toast.makeText(this, total, Toast.LENGTH_LONG).show()
        }

    }

    //checkbox onclick event
    fun suitable(v:View?) {
        fun suitable(v: View?) {
            if (audience.isChecked) {
                violence.visibility = View.VISIBLE;
                lanUsed.visibility = View.VISIBLE;
            } else {
                violence.visibility = View.INVISIBLE;
                lanUsed.visibility = View.INVISIBLE;
            }
        }
    }
}
