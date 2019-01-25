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
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeTopList;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeToplistAdapter extends RecyclerView.Adapter<AnimeToplistAdapter.ToplistViewHolder> {

    private Context context;
    private List<GETAnimeTopList> topList;

    public AnimeToplistAdapter(Context context, List<GETAnimeTopList> topList) {
        this.context = context;
        this.topList = topList;
    }

    @NonNull
    @Override
    public ToplistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_anime_showcase,parent,false);
        return new ToplistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ToplistViewHolder holder, int position) {
        final GETAnimeTopList animeItem=topList.get(position);

        holder.titleText.setText(animeItem.getTitle());
        holder.typeText.setText(animeItem.getType());
        holder.episodesText.setText(String.valueOf(animeItem.getEpisodes()));
        holder.scoreText.setText(String.valueOf(animeItem.getScore()));
        holder.progressBar.setVisibility(View.VISIBLE);

        Glide.with(context).load(animeItem.getImage_url()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.imageView.setImageResource(R.drawable.ic_no_picture);
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeInfo.newInstance(animeItem.getMal_id())).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ToplistViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,typeText,scoreText,episodesText,isAiringText;
        ImageView imageView;
        ProgressBar progressBar;

        ToplistViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleText);
            typeText=itemView.findViewById(R.id.typeText);
            scoreText=itemView.findViewById(R.id.scoreText);
            episodesText=itemView.findViewById(R.id.episodesText);
            isAiringText=itemView.findViewById(R.id.isAiringText);
            imageView=itemView.findViewById(R.id.imageView);
            progressBar=itemView.findViewById(R.id.animeProgress);
        }
    }
}
