package a172026h.nyp.movieraterintermediate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_edit_movie.*

class edit_movie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val movieClass = intent.getParcelableExtra<Movie>("movieClass")

        findViewById<EditText>(R.id.edit_movieName).setText(movieClass.movieName)
        findViewById<EditText>(R.id.edit_movieDesc).setText(movieClass.movieDesc)
        if (movieClass.movieLang == "English") {
            findViewById<RadioGroup>(R.id.edit_languageRadioGroup).check(R.id.radioEnglish)
        } else if (movieClass.movieLang == "Chinese") {
            findViewById<RadioGroup>(R.id.edit_languageRadioGroup).check(R.id.radioChinese)
        } else if (movieClass.movieLang == "Tamil") {
            findViewById<RadioGroup>(R.id.edit_languageRadioGroup).check(R.id.radioTamil)
        } else if (movieClass.movieLang == "Malay") {
            findViewById<RadioGroup>(R.id.edit_languageRadioGroup).check(R.id.radioMalay)
        }
        findViewById<EditText>(R.id.edit_movieReleaseDate).setText(movieClass.movieReleaseDate)
        findViewById<CheckBox>(R.id.edit_restrictChkbx).setChecked(movieClass.movieSuitability.toBoolean())
        if (edit_restrictChkbx.isChecked == true) {
            edit_additionalChkboxes.setVisibility(View.VISIBLE)
        } else {
            edit_additionalChkboxes.setVisibility(View.GONE)
        }
    }

    fun restrictOptions(v : View) {
        if (edit_restrictChkbx.isChecked == true) {
            edit_additionalChkboxes.setVisibility(View.VISIBLE)
        } else {
            edit_additionalChkboxes.setVisibility(View.GONE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_movie, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.saveBtn) {
            var movieName : String = edit_movieName.text.toString()
            var movieDesc : String = edit_movieDesc.text.toString()
            var lang : String = findViewById<RadioButton>(edit_languageRadioGroup.checkedRadioButtonId).text.toString()
            var releaseDate : String = edit_movieReleaseDate.text.toString()
            var suitability : Boolean = edit_restrictChkbx.isChecked
            var reason : String = ""
            if (suitability) {
                if (findViewById<CheckBox>(R.id.edit_additionalChkbox1).isChecked) {
                    reason = "" + (findViewById<CheckBox>(R.id.edit_additionalChkbox1).text.toString())
                    if (findViewById<CheckBox>(R.id.edit_additionalChkbox2).isChecked) {
                        reason += ", " + (findViewById<CheckBox>(R.id.edit_additionalChkbox2).text.toString())
                    }
                } else if (findViewById<CheckBox>(R.id.edit_additionalChkbox2).isChecked) {
                    reason = "" + (findViewById<CheckBox>(R.id.edit_additionalChkbox2).text.toString())
                }
            }
            var valid : Boolean = true

            if (movieName.isEmpty()) {
                edit_movieName.setError("Field Empty")
                valid = false
            }
            if (movieDesc.isEmpty()) {
                edit_movieDesc.setError("Field Empty")
                valid = false
            }
            if (releaseDate.isEmpty()) {
                edit_movieReleaseDate.setError("Field Empty")
                valid = false
            }
            if (valid) {
                var movieClass = intent.getParcelableExtra<Movie>("movieClass")
                var movieIndex = intent.getIntExtra("moviesIndex", -1)
                val movie = Movie(movieName, movieDesc, releaseDate, lang, reason, suitability.toString(), movieClass.movieRating, movieClass.movieReview)
                val intent = Intent(this, movie_review::class.java).apply {
                    putExtra("movieClass", movie)
                    putExtra("moviesIndex", movieIndex)
                }
                startActivity(intent)
            }
        }
        else if (item?.itemId == R.id.cancelBtn) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
