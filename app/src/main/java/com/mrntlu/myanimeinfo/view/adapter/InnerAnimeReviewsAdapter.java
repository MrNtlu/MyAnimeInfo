package com.mrntlu.myanimeinfo.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeReviewByID;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class InnerAnimeReviewsAdapter extends RecyclerView.Adapter<InnerAnimeReviewsAdapter.ReviewsViewHolder> {

    private List<GETAnimeReviewByID> animeReviews;
    private Context context;

    public InnerAnimeReviewsAdapter(List<GETAnimeReviewByID> animeReviews, Context context) {
        this.animeReviews = animeReviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_inner_anime_reviews,parent,false);
        return new ReviewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewsViewHolder holder, int position) {
        GETAnimeReviewByID animeReview=animeReviews.get(position);
        GETAnimeReviewByID.Reviewer reviewer=animeReview.getReviewer();
        Glide.with(context).load(reviewer.getImage_url()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.userImage.setImageResource(R.drawable.ic_no_picture);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(holder.userImage);

        holder.usernameText.setText(reviewer.getUsername());
        holder.reviewText.setText(animeReview.getContent());
        //Scores
        holder.storyScore.setText(String.valueOf(reviewer.getScores().getStory()));
        holder.soundScore.setText(String.valueOf(reviewer.getScores().getSound()));
        holder.enjoymentScore.setText(String.valueOf(reviewer.getScores().getEnjoyment()));
        holder.characterScore.setText(String.valueOf(reviewer.getScores().getCharacter()));
        holder.animationScore.setText(String.valueOf(reviewer.getScores().getAnimation()));
        holder.overallScore.setText(String.valueOf(reviewer.getScores().getOverall()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.reviewText.getVisibility()==View.GONE){
                    holder.reviewText.setVisibility(View.VISIBLE);
                    holder.expandImage.setImageResource(android.R.drawable.arrow_up_float);
                }else{
                    holder.reviewText.setVisibility(View.GONE);
                    holder.expandImage.setImageResource(android.R.drawable.arrow_down_float);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return animeReviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder{

        TextView storyScore,soundScore,enjoymentScore,characterScore,animationScore,overallScore,reviewText,usernameText;
        ImageView userImage,expandImage;

        ReviewsViewHolder(View itemView) {
            super(itemView);
            storyScore=itemView.findViewById(R.id.storyScore);
            usernameText=itemView.findViewById(R.id.usernameText);
            soundScore=itemView.findViewById(R.id.soundScore);
            enjoymentScore=itemView.findViewById(R.id.enjoymentScore);
            characterScore=itemView.findViewById(R.id.characterScore);
            animationScore=itemView.findViewById(R.id.animationScore);
            overallScore=itemView.findViewById(R.id.overallScore);
            reviewText=itemView.findViewById(R.id.reviewText);
            userImage=itemView.findViewById(R.id.userImage);
            expandImage=itemView.findViewById(R.id.expandImage);
        }
    }
}
