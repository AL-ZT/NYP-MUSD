package a172026h.nyp.movieraterintermediate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_movie_rating.*

class movie_rating : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rating)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_rating, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.submitRatingBtn) {
            var movieClass = intent.getParcelableExtra<Movie>("movieClass")
            movieClass.movieRating = reviewScore.rating.toDouble()
            movieClass.movieReview = movieSummary.text.toString()

            val intent = Intent(this, movie_review::class.java).apply {
                putExtra("movieClass", movieClass)
            }
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
