package com.joymaker.mymovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joymaker.mymovies.R;
import com.joymaker.mymovies.data.Review;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    ArrayList<Review> reviews;

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewCOntent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAuthor;
        private TextView textViewCOntent;

        public ReviewViewHolder(@NonNull View itemView) {


            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.TextViewAuthor);
            textViewCOntent = itemView.findViewById(R.id.TextViewContent);
        }
    }
}
