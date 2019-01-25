package com.mrntlu.myanimeinfo.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserAnimelist;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class UserAnimeListAdapter extends RecyclerView.Adapter<UserAnimeListAdapter.AnimeListViewHolder> {

    private Context context;
    private List<GETUserAnimelist> userAnimelist;

    public UserAnimeListAdapter(Context context, List<GETUserAnimelist> userAnimelist) {
        this.context = context;
        this.userAnimelist = userAnimelist;
    }

    @NonNull
    @Override
    public AnimeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_user_anime_list,parent,false);
        return new AnimeListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnimeListViewHolder holder, int position) {
        final GETUserAnimelist userAnime=userAnimelist.get(position);
        holder.progressBar.setVisibility(View.VISIBLE);
        int status=userAnime.getWatching_status();
        String mStatus="";
        if (status==1){ mStatus="Watching"; holder.animeStatusImage.setImageResource(R.color.greenflat); }
        else if (status==2){ mStatus="Completed"; holder.animeStatusImage.setImageResource(R.color.midnightblue);}
        else if (status==3){ mStatus="On-Hold"; holder.animeStatusImage.setImageResource(R.color.lightyellow);}
        else if (status==4){ mStatus="Dropped"; holder.animeStatusImage.setImageResource(android.R.color.holo_red_dark);}
        else if (status==6){ mStatus="Plan to Watch"; holder.animeStatusImage.setImageResource(android.R.color.darker_gray);}

        holder.titleText.setText(userAnime.getTitle());
        holder.watchingStatusText.setText(mStatus);
        Glide.with(context).load(userAnime.getImage_url()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                holder.animeImage.setImageResource(R.drawable.ic_no_picture);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.animeImage);

        String watchedEpisodes=userAnime.getWatched_episodes()+"/"+userAnime.getTotal_episodes();
        holder.watchedEpisodesText.setText(watchedEpisodes);
        holder.givenScoreText.setText(String.valueOf(userAnime.getScore()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeInfo.newInstance(userAnime.getMal_id())).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userAnimelist.size();
    }

    static class AnimeListViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,watchingStatusText,givenScoreText,watchedEpisodesText;
        ImageView animeStatusImage,animeImage;
        ProgressBar progressBar;

        AnimeListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.animeTitleText);
            watchingStatusText=itemView.findViewById(R.id.watchingStatusText);
            givenScoreText=itemView.findViewById(R.id.givenScoreText);
            watchedEpisodesText=itemView.findViewById(R.id.watchedEpisodesText);
            animeStatusImage=itemView.findViewById(R.id.animeStatusImage);
            animeImage=itemView.findViewById(R.id.animeImage);
            progressBar=itemView.findViewById(R.id.imageProgress);
        }
    }
}
