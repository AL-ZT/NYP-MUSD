package a172026h.nyp.movieraterintermediate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    val movies = Movies.returnMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(landingPage_NoMovie)

        if (movies.isNotEmpty()) {
            list_view.visibility = View.VISIBLE
            landingPage_NoMovie.visibility = View.GONE

            var listView : ListView = findViewById(R.id.list_view)
            var adapter = MovieListAdapter(this, R.layout.list_view, movies)
            listView.adapter = adapter

            registerForContextMenu(list_view)
        }

        list_view.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, movie_review::class.java).apply {
                putExtra("movieClass", movies.get(position))
                putExtra("moviesIndex", position)
            }
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.landingPage_NoMovie) {
            menu?.add(1, 1001, 1, "Add")
        }
        else if (v?.id == R.id.list_view) {
            menu?.add(1, 1002, 1, "Edit")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001) {
            val intent = Intent(this, add_movie::class.java)
            startActivity(intent)
        } else if (item?.itemId == 1002) {
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            var index : Int = info.position
            val intent = Intent(this, edit_movie::class.java).apply {
                putExtra("movieClass", movies.get(index))
                putExtra("moviesIndex", index)
            }
            startActivity(intent)
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landing_page, menu)

        if (movies.isNotEmpty()) {
            menu?.findItem(R.id.forListView)?.setVisible(true)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.forListView) {
            val intent = Intent(this, add_movie::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
