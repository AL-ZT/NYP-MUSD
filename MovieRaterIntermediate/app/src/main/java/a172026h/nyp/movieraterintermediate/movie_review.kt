package a172026h.nyp.movieraterintermediate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_review.*
import java.lang.Exception

class movie_review : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_review)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        registerForContextMenu(movieReview)
        try {
            val movieClass = intent.getParcelableExtra<Movie>("movieClass")

            insertMovieTitle.text = movieClass.movieName
            insertMovieOverview.text = movieClass.movieDesc
            insertMovieLanguage.text = movieClass.movieLang
            insertMovieReleaseDate.text = movieClass.movieReleaseDate
            if (movieClass.movieSuitability == "false") {
                insertMovieSuitability.text = "Yes"
            } else {
                insertMovieSuitability.text = "No" + "(${movieClass.reason})"
            }

            if (movieClass.movieRating > 0) {
                movieReview.visibility = View.GONE
                movieReviewRatingBar.visibility = View.VISIBLE
                movieReviewSummary.visibility = View.VISIBLE
                movieReviewRatingBar.rating = movieClass.movieRating.toFloat()
                movieReviewSummary.text = movieClass.movieReview
            }
        } catch (e : Exception) {

        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.movieReview) {
            menu?.add(2, 1002, 1, "Add Review")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1002) {
            val movieClass = intent.getParcelableExtra<Movie>("movieClass")
            val intent = Intent(this, movie_rating::class.java).apply {
                putExtra("movieClass", movieClass)
            }
            startActivity(intent)
        }

        return super.onContextItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            val movieClass = intent.getParcelableExtra<Movie>("movieClass")
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("movieClass", movieClass)
            }
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
