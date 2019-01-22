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
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserFavs;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class UserFavsAdapter extends RecyclerView.Adapter<UserFavsAdapter.UserFavsViewHolder> {

    private int status;
    private Context context;
    private UserProfileResponseBody.Favorites favorites;

    public UserFavsAdapter(int status, Context context, UserProfileResponseBody.Favorites favorites) {
        this.status = status;
        this.context = context;
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public UserFavsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_inner_user_favs,parent,false);
        return new UserFavsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserFavsViewHolder holder, int position) {

        final GETUserFavs favs=status==0?favorites.getAnime().get(position):favorites.getCharacters().get(position);

        holder.titleText.setText(favs.getName());
        Glide.with(context).load(favs.getImage_url()).addListener(new RequestListener<Drawable>() {
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
                if(status==0){
                    AppCompatActivity activity=(AppCompatActivity)view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeInfo.newInstance(favs.getMal_id())).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return status==0?favorites.getAnime().size():favorites.getCharacters().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class UserFavsViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        ImageView imageView;
        ProgressBar progressBar;

        public UserFavsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleText);
            imageView=itemView.findViewById(R.id.imageView);
            progressBar=itemView.findViewById(R.id.animeProgress);
        }
    }
}
