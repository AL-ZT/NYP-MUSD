package a172026h.nyp.movieraterintermediate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MovieListAdapter : ArrayAdapter<Movie> {
    var mContext : Context
    var mResource : Int

    constructor(
        context: Context,
        resource: Int,
        objects: ArrayList<Movie>
    ) : super(context, resource, objects) {
        this.mContext = context
        this.mResource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var movieName : String = getItem(position).movieName
        var movieDesc : String = getItem(position).movieDesc
        var movieLang : String = getItem(position).movieLang
        var movieReleaseDate : String = getItem(position).movieReleaseDate
        var moviSuitability : String = getItem(position).movieSuitability
        var movieRating : Double = getItem(position).movieRating
        var movieReview : String = getItem(position).movieReview
        var movieReason : String = getItem(position).reason

        var tempMovie = Movie(movieName, movieDesc, movieReleaseDate, movieLang, movieReason, moviSuitability, movieRating, movieReview)

        var convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false)

        var tvMovieName = convertView.findViewById<TextView>(R.id.movieNameLabel)
        tvMovieName.setText(movieName)

        return convertView
    }
}