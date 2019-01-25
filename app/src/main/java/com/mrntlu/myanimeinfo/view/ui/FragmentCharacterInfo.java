package com.mrntlu.myanimeinfo.view.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.CharacterInfoBody;
import com.mrntlu.myanimeinfo.view.OnCharacterInfoLoaded;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeCharactersAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;

public class FragmentCharacterInfo extends Fragment implements OnCharacterInfoLoaded {

    private int characterID;

    private WeakReference<RecyclerView> recyclerViewWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<TextView> weakCharacterName,weakCharacterNickname,weakCharacterAbout;
    private WeakReference<ImageView> imageViewWeakReference;
    private WeakReference<ConstraintLayout> constraintLayoutWeakReference;

    public FragmentCharacterInfo() {
        // Required empty public constructor
    }

    public static FragmentCharacterInfo newInstance(int characterID) {
        FragmentCharacterInfo fragment = new FragmentCharacterInfo();
        fragment.characterID=characterID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_character_info, container, false);
        RecyclerView recyclerView=v.findViewById(R.id.characterAnimesRV);
        TextView characterName=v.findViewById(R.id.characterName);
        TextView characterNickname=v.findViewById(R.id.characterNickNames);
        TextView characterAbout=v.findViewById(R.id.aboutText);
        ImageView characterImage=v.findViewById(R.id.characterImage);
        ProgressBar progressBar=v.findViewById(R.id.characterImageProgress);
        ConstraintLayout constraintLayout=v.findViewById(R.id.loadingScreen);

        progressBarWeakReference=new WeakReference<>(progressBar);
        recyclerViewWeakReference=new WeakReference<>(recyclerView);
        imageViewWeakReference=new WeakReference<>(characterImage);
        weakCharacterName=new WeakReference<>(characterName);
        weakCharacterNickname=new WeakReference<>(characterNickname);
        weakCharacterAbout=new WeakReference<>(characterAbout);
        constraintLayoutWeakReference=new WeakReference<>(constraintLayout);


        AnimeViewModel viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getCharacterByID(characterID,this);

        return v;
    }

    @Override
    public void onCharacterInfoLoaded(CharacterInfoBody body) {
        if (FragmentCharacterInfo.this.isVisible() && (FragmentCharacterInfo.this.getView()!=null)) {
            RecyclerView weakRV = recyclerViewWeakReference.get();
            final ProgressBar weakProgress = progressBarWeakReference.get();
            final ImageView weakImageView = imageViewWeakReference.get();
            ConstraintLayout weakConstraint=constraintLayoutWeakReference.get();
            if (weakRV!=null && weakProgress!=null && weakImageView!=null && weakConstraint!=null) {

                TextView charName = weakCharacterName.get();
                TextView charNick = weakCharacterNickname.get();
                TextView charAbout = weakCharacterAbout.get();

                InnerAnimeCharactersAdapter adapter = new InnerAnimeCharactersAdapter(getContext(), body.getAnimeography(),true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                weakRV.setLayoutManager(linearLayoutManager);
                weakRV.setAdapter(adapter);

                if (charName!=null) charName.setText(body.getName());
                if (charAbout!=null) charAbout.setText(body.getAbout());
                String nicknames = "";
                for (String nick : body.getNicknames()) {
                    nicknames = nick + " ";
                }
                if (charNick!=null) charNick.setText(nicknames);

                Glide.with(getContext()).load(body.getImage_url()).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        weakImageView.setImageResource(R.drawable.ic_no_picture);
                        weakProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        weakProgress.setVisibility(View.GONE);
                        return false;
                    }
                }).into(weakImageView);
                weakConstraint.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onFailedToLoad() {
        Toast.makeText(getContext().getApplicationContext(), "Error! Too many requests.", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
