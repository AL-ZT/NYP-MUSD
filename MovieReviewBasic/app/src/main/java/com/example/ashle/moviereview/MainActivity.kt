package com.example.ashle.moviereview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun restrictoptions(v : View) {
        if (restrictchkbx.isChecked == true) {
            additionalchkboxes.setVisibility(View.VISIBLE)
        } else {
            additionalchkboxes.setVisibility(View.GONE)
        }
    }

    fun addMovie(v : View) {
        var name : String = movieName.text.toString()
        var desc : String = movieDesc.text.toString()
        var date : String = movieReleaseDate.text.toString()
        var lang : String = findViewById<RadioButton>(radiogroup.checkedRadioButtonId).text.toString()
        var chkbx : Boolean = restrictchkbx.isChecked
        var reason : String = ""
        if (chkbx) {
            if (findViewById<CheckBox>(R.id.additionalchkbox1).isChecked) {
                reason = "\r\n" + findViewById<CheckBox>(R.id.additionalchkbox1).text.toString()
            }
            if (findViewById<CheckBox>(R.id.additionalchkbox2).isChecked) {
                reason = "${reason}\r\n" + findViewById<CheckBox>(R.id.additionalchkbox2).text.toString()
            }
        }

        var valid : Boolean = true

        if (name.isEmpty()) {
            movieName.setError("Field Empty")
            valid = false
        }
        if (desc.isEmpty()) {
            movieDesc.setError("Field Empty")
            valid = false
        }
        if (date.isEmpty()) {
            movieReleaseDate.setError("Field Empty")
            valid = false
        }

        if (valid) {
            Toast.makeText(applicationContext, "Title = ${name}\r\nOverview = ${desc}\r\nRelease Date = ${date}\r\nLanguage = ${lang}\r\nSuitable for all ages = ${!chkbx}\r\nReason : ${reason}", Toast.LENGTH_LONG).show()
            val movie = Movie(name, desc, date, lang, reason, chkbx.toString())
            val intent = Intent(this, MovieDetails::class.java).apply {
                putExtra("movieClass", movie)
            }
            startActivity(intent)
        }
    }
}
