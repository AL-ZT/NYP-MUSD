package a172026h.nyp.movieraterintermediate;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {

    private String movieName;
    private String movieDesc;
    private String movieReleaseDate;
    private String movieLang;
    private String reason;
    private String movieSuitability;
    private Double movieRating;
    private String movieReview;

    public Movie(String movieName, String movieDesc, String movieReleaseDate, String movieLang, String reason, String movieSuitability, Double movieRating, String movieReview) {
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.movieReleaseDate = movieReleaseDate;
        this.movieLang = movieLang;
        this.reason = reason;
        this.movieSuitability = movieSuitability;
        this.movieRating = movieRating;
        this.movieReview = movieReview;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieLang() {
        return movieLang;
    }

    public void setMovieLang(String movieLang) {
        this.movieLang = movieLang;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMovieSuitability() {
        return movieSuitability;
    }

    public void setMovieSuitability(String movieSuitability) {
        this.movieSuitability = movieSuitability;
    }

    public Double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(String movieReview) {
        this.movieReview = movieReview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieName);
        dest.writeString(this.movieDesc);
        dest.writeString(this.movieReleaseDate);
        dest.writeString(this.movieLang);
        dest.writeString(this.reason);
        dest.writeString(this.movieSuitability);
        dest.writeDouble(this.movieRating);
        dest.writeString(this.movieReview);
    }

    protected Movie(Parcel in) {
        this.movieName = in.readString();
        this.movieDesc = in.readString();
        this.movieReleaseDate = in.readString();
        this.movieLang = in.readString();
        this.reason = in.readString();
        this.movieSuitability = in.readString();
        this.movieRating = in.readDouble();
        this.movieReview = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
