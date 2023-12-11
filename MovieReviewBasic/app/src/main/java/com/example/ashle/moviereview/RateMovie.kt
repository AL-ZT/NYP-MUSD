package com.example.ashle.moviereview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rate_movie.*
import java.io.Serializable

class RateMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movie)

        val movieClass = intent.getParcelableExtra<Movie>("movieClass")

        reviewscore.text =  reviewscore.text.toString() + " ${movieClass.movieName}"
    }
}
