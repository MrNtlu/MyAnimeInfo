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
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import com.mrntlu.myanimeinfo.view.ui.FragmentCharacterInfo;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InnerAnimeCharactersAdapter extends RecyclerView.Adapter<InnerAnimeCharactersAdapter.CharacterViewHolder> {

    private Context context;
    private List<GETAnimeCharacters> getAnimeCharacters;
    private boolean isCharacterInfo=false;

    public InnerAnimeCharactersAdapter(Context context, List<GETAnimeCharacters> getAnimeCharacters) {
        this.context = context;
        this.getAnimeCharacters = getAnimeCharacters;
    }

    public InnerAnimeCharactersAdapter(Context context, List<GETAnimeCharacters> getAnimeCharacters,boolean isCharacterInfo) {
        this.context = context;
        this.getAnimeCharacters = getAnimeCharacters;
        this.isCharacterInfo=true;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_inner_anime_characters,parent,false);
        return new CharacterViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final CharacterViewHolder holder, int position) {
        GETAnimeCharacters animeCharacter=getAnimeCharacters.get(position);

        holder.characterNameText.setText(animeCharacter.getName());
        holder.characterRoleText.setText(animeCharacter.getRole());
        Glide.with(context).load(animeCharacter.getImage_url()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.characterImage.setImageResource(R.drawable.ic_no_picture);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(holder.characterImage);

        final int characterID=animeCharacter.getMal_id();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment=isCharacterInfo? FragmentAnimeInfo.newInstance(characterID):FragmentCharacterInfo.newInstance(characterID);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAnimeCharacters.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder{

        TextView characterNameText,characterRoleText;
        ImageView characterImage;
        //ProgressBar progressBar;

        CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterNameText=itemView.findViewById(R.id.characterNameText);
            characterRoleText=itemView.findViewById(R.id.characterRoleText);
            characterImage=itemView.findViewById(R.id.characterImage);
            //progressBar=itemView.findViewById(R.id.animeProgress);
        }
    }
}
