package a172026h.nyp.movieraterintermediate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.*

class add_movie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.addMovieBtn) {
            var name : String = movieName.text.toString()
            var desc : String = movieDesc.text.toString()
            var date : String = movieReleaseDate.text.toString()
            var lang : String = findViewById<RadioButton>(languageRadioGroup.checkedRadioButtonId).text.toString()
            var restrictChkbx : Boolean = restrictChkbx.isChecked
            var reason : String = ""
            if (restrictChkbx) {
                if (findViewById<CheckBox>(R.id.additionalChkbox1).isChecked) {
                    reason = "" + (findViewById<CheckBox>(R.id.additionalChkbox1).text.toString())
                    if (findViewById<CheckBox>(R.id.additionalChkbox2).isChecked) {
                        reason += ", " + (findViewById<CheckBox>(R.id.additionalChkbox2).text.toString())
                    }
                } else if (findViewById<CheckBox>(R.id.additionalChkbox2).isChecked) {
                    reason = "" + (findViewById<CheckBox>(R.id.additionalChkbox2).text.toString())
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
                val movie = Movie(name, desc, date, lang, reason, restrictChkbx.toString(), -1.0, "")
                val intent = Intent(this, movie_review::class.java).apply {
                    putExtra("movieClass", movie)
                }
                startActivity(intent)
            }
        }
        else if (item?.itemId == R.id.clearEntriesBtn) {
            movieName.text.clear()
            movieDesc.text.clear()
            languageRadioGroup.clearCheck()
            languageRadioGroup.check(R.id.defaultRadio)
            movieReleaseDate.text.clear()
            restrictChkbx.isChecked = false
            additionalChkboxes.visibility = View.GONE
            additionalChkbox1.isChecked = false
            additionalChkbox2.isChecked = false
        } else if (item?.itemId == android.R.id.home) {
            Toast.makeText(this, "hello", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }

    fun restrictOptions(v : View) {
        if (restrictChkbx.isChecked == true) {
            additionalChkboxes.setVisibility(View.VISIBLE)
        } else {
            additionalChkboxes.setVisibility(View.GONE)
        }
    }
}
