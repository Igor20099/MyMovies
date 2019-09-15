package com.joymaker.mymovies.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    private static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavoriteMovie>> favoriteMovies;

    public LiveData<List<FavoriteMovie>> getFavoriteMovies() {
        return favoriteMovies;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(getApplication());
        movies = database.movieDao().getAllMovie();
        favoriteMovies = database.movieDao().getAllFavoriteMovie();
    }
    public Movie getMovieById(int id) {
        try {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FavoriteMovie getFavoriteMovieById(int id) {
        try {
            return new GetFavoriteMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetMovieTask extends AsyncTask<Integer,Void,Movie> {

        @Override
        protected Movie doInBackground(Integer... integers) {
            if(integers != null && integers.length > 0) {
                return database.movieDao().getMovieById(integers[0]);
            }
            return null;
        }
    }

    private static class GetFavoriteMovieTask extends AsyncTask<Integer,Void,FavoriteMovie> {

        @Override
        protected FavoriteMovie doInBackground(Integer... integers) {
            if(integers != null && integers.length > 0) {
                return database.movieDao().getFavoriteMovieById(integers[0]);
            }
            return null;
        }
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void deleteAllMovies() {
        new DeleteAllMoviesTask().execute();
    }
    private static class DeleteAllMoviesTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            database.movieDao().deleteAllMovies();
            return null;
        }
    }

    public void insertMovie(Movie movie) {
        new InsertTask().execute(movie);
    }

    private static class InsertTask extends AsyncTask<Movie,Void,Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            if(movies!= null && movies.length > 0) {
                database.movieDao().insertMovie(movies[0]);
            }
            return null;
        }
    }

    public void deleteMovie(Movie movie) {
        new DeleteTask().execute(movie);
    }

    private static class DeleteTask extends AsyncTask<Movie,Void,Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            if(movies!=null && movies.length > 0) {
                database.movieDao().deleteMovie(movies[0]);
            }
            return null;
        }
    }

    public void insertFavoriteMovie(FavoriteMovie movie) {
        new InsertFavoriteTask().execute(movie);
    }

    private static class InsertFavoriteTask extends AsyncTask<FavoriteMovie,Void,Void> {

        @Override
        protected Void doInBackground(FavoriteMovie... movies) {
            if(movies!= null && movies.length > 0) {
                database.movieDao().insertFavoriteMovie(movies[0]);
            }
            return null;
        }
    }

    public void deleteFavoriteMovie(FavoriteMovie movie) {
        new DeleteFavoriteTask().execute(movie);
    }

    private static class DeleteFavoriteTask extends AsyncTask<FavoriteMovie,Void,Void> {

        @Override
        protected Void doInBackground(FavoriteMovie... movies) {
            if(movies!=null && movies.length > 0) {
                database.movieDao().deleteFavoriteMovie(movies[0]);
            }
            return null;
        }
    }
}
