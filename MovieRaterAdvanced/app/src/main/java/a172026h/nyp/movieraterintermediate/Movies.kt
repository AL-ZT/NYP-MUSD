package a172026h.nyp.movieraterintermediate

class Movies {
    companion object MovieEntities {
        var movies = arrayListOf<Movie>()
        var count : Int = 0

        fun create() : Movies = Movies()

        @JvmStatic
        fun addMovie (movie: Movie) {
            movies.add(movie)
            count += 1
        }

        @JvmStatic
        fun returnMovies() : ArrayList<Movie> {
            return movies
        }

        @JvmStatic
        fun setMovie(index : Int, movieEntity : Movie) {
            movies.set(index, movieEntity)
        }
    }
}