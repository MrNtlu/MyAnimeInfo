package com.mrntlu.myanimeinfo.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.ui.FragmentAnimeInfo;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentRelatedAnimes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class InnerAnimeRelationsAdapter extends RecyclerView.Adapter<InnerAnimeRelationsAdapter.MyViewHolder> {

    private Context context;
    private GETAnimeByID.RelatedAnimes relatedAnimes;
    private int status;

    public InnerAnimeRelationsAdapter(Context context, GETAnimeByID.RelatedAnimes relatedAnimes, int status) {
        this.context = context;
        this.relatedAnimes = relatedAnimes;
        this.status = status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cell_inner_anime_relations,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (status==0){
            holder.animeTitle.setText(relatedAnimes.getOther().get(position).getName());
        }else if (status==1){
            holder.animeTitle.setText(relatedAnimes.getPrequel().get(position).getName());
        }else{
            holder.animeTitle.setText(relatedAnimes.getSequel().get(position).getName());
        }

        final int lastPosition=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mal_id;
                if (status==0)mal_id=relatedAnimes.getOther().get(lastPosition).getMal_id();
                else if (status==1)mal_id=relatedAnimes.getPrequel().get(lastPosition).getMal_id();
                else mal_id=relatedAnimes.getSequel().get(lastPosition).getMal_id();

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeInfo.newInstance(mal_id)).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        if (status==0){
            return relatedAnimes.getOther().size();
        }else if (status==1){
            return relatedAnimes.getPrequel().size();
        }else if (status==2){
            return relatedAnimes.getSequel().size();
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animeTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            animeTitle=itemView.findViewById(R.id.titleText);
        }
    }
}
