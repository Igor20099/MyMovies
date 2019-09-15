package com.joymaker.mymovies.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovie();

    @Query("SELECT * FROM favorite_movies")
    LiveData<List<FavoriteMovie>> getAllFavoriteMovie();

    @Query("SELECT * FROM movies WHERE id == :movieId")
    Movie getMovieById(int movieId);

    @Query("SELECT * FROM favorite_movies WHERE id == :movieId")
    FavoriteMovie getFavoriteMovieById(int movieId);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Insert
    void insertFavoriteMovie(FavoriteMovie movie);

    @Delete
    void deleteFavoriteMovie(FavoriteMovie movie);
}
