package com.mrntlu.myanimeinfo.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeSearch;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentRelatedAnimes;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeSearchAdapter extends RecyclerView.Adapter<AnimeSearchAdapter.MyViewHolder> {

    private Context context;
    private List<GETAnimeSearch> animeSearchList;

    public AnimeSearchAdapter(Context context, List<GETAnimeSearch> animeSearchList) {
        this.context = context;
        this.animeSearchList = animeSearchList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_anime_showcase,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final GETAnimeSearch searchItem=animeSearchList.get(position);

        holder.titleText.setText(searchItem.getTitle());
        holder.typeText.setText(searchItem.getType());
        holder.scoreText.setText(String.valueOf(searchItem.getScore()));
        holder.episodesText.setText(String.valueOf(searchItem.getEpisodes()));
        if (searchItem.isAiring()) holder.isAiringText.setVisibility(View.VISIBLE);
        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(context).load(searchItem.getImage_url()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
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
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeInfo.newInstance(searchItem.getMal_id())).addToBackStack(null).commit();
                Toast.makeText(context, String.valueOf(searchItem.getMal_id()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeSearchList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleText,typeText,scoreText,episodesText,isAiringText;
        ImageView imageView;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
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
