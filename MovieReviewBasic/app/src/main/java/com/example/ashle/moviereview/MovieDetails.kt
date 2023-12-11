package com.example.ashle.moviereview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android .synthetic.main.activity_movie_details.*
import android.view.View

class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieClass = intent.getParcelableExtra<Movie>("movieClass")


        insertMovieTitle.text = movieClass.movieName
        insertMovieOverview.text = movieClass.movieDesc
        insertMovieLanguage.text = movieClass.movieLang
        insertMovieReleaseDate.text = movieClass.movieReleaseDate
        if (movieClass.movieSuitability == "false") {
            insertMovieSuitability.text = "Yes"
        } else {
            insertMovieSuitability.text = "No"
        }

        val intent = Intent(this, RateMovie::class.java).apply {
            putExtra("movieClass", movieClass)
        }

        android.os.Handler().postDelayed(
            { startActivity(intent) },
            5000
        )
    }
}
