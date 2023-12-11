package a172026h.nyp.movieraterintermediate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_add_movie.*

class edit_movie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun restrictOptions(v : View) {
        if (restrictChkbx.isChecked == true) {
            additionalChkboxes.setVisibility(View.VISIBLE)
        } else {
            additionalChkboxes.setVisibility(View.GONE)
        }
    }
}
