package com.joymaker.mymovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joymaker.mymovies.R;
import com.joymaker.mymovies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {
    private List<Movie> movies;
    private OnPosterClickListener onPosterClickListener;
    private OnReachEndListener onReachEndListener;

    public List<Movie> getMovies() {
        return movies;
    }

   public interface OnPosterClickListener {
        void onPosterClick(int position);
    }

   public interface OnReachEndListener {
        void onReachEnd();
    }


    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public MovieAdapter() {
        movies = new ArrayList<>();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MoviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        if ( movies.size()>=20 && position > movies.size() - 4 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
        Movie movie = movies.get(position);
        Picasso.get().load(movie.getPosterPath()).into(holder.imageViewSmallPoster);
    }

    public void clear() {
        this.movies.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MoviewViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewSmallPoster;

        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onPosterClickListener != null) {
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
