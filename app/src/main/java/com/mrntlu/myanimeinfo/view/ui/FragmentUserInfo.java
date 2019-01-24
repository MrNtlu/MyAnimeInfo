package com.mrntlu.myanimeinfo.view.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.OnUserInfoLoaded;
import com.mrntlu.myanimeinfo.view.adapter.UserInfoPagerAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;

public class FragmentUserInfo extends Fragment implements OnUserInfoLoaded {

    private String username;

    private WeakReference<ViewPager> viewPagerWeakReference;
    private WeakReference<TabLayout> tabLayoutWeakReference;
    private WeakReference<ConstraintLayout> constraintLayoutWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<ImageView> imageViewWeakReference;
    private WeakReference<TextView> weakAnimeTitle;

    public FragmentUserInfo() {
        // Required empty public constructor
    }

    public static FragmentUserInfo newInstance(String username) {
        FragmentUserInfo fragment = new FragmentUserInfo();
        fragment.username=username;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_info, container, false);
        TextView usernameText= v.findViewById(R.id.usernameText);
        ProgressBar userImageProgress= v.findViewById(R.id.userImageProgress);
        Button animelistButton = v.findViewById(R.id.animelistButton);
        ImageView userImage= v.findViewById(R.id.userImage);
        ConstraintLayout loadingScreen= v.findViewById(R.id.loadingScreen);
        ViewPager viewPager= v.findViewById(R.id.userViewPager);
        TabLayout tabLayout= v.findViewById(R.id.userTabLayout);

        weakAnimeTitle=new WeakReference<>(usernameText);
        viewPagerWeakReference=new WeakReference<>(viewPager);
        tabLayoutWeakReference=new WeakReference<>(tabLayout);
        constraintLayoutWeakReference=new WeakReference<>(loadingScreen);
        progressBarWeakReference=new WeakReference<>(userImageProgress);
        imageViewWeakReference=new WeakReference<>(userImage);

        animelistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)getContext();
                if (activity != null) {
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentUserAnimeList.newInstance(username)).addToBackStack(null).commit();
                }
            }
        });

        AnimeViewModel viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getUserProfile(username,this);
        return v;
    }


    @Override
    public void onUserInfoLoaded(UserProfileResponseBody profileBody) {
        if (FragmentUserInfo.this.isVisible()) {
            ViewPager weakViewPager = viewPagerWeakReference.get();
            TabLayout weakTabLayout = tabLayoutWeakReference.get();
            ImageView weakImageView = imageViewWeakReference.get();
            final ConstraintLayout weakConstraint = constraintLayoutWeakReference.get();
            final ProgressBar weakProgress=progressBarWeakReference.get();

            TextView animeTitle=weakAnimeTitle.get();

            if (weakConstraint != null && weakTabLayout != null && weakViewPager != null && weakProgress!=null && weakImageView!=null) {

                UserInfoPagerAdapter pagerAdapter = new UserInfoPagerAdapter(getChildFragmentManager(), profileBody);
                weakViewPager.setAdapter(pagerAdapter);
                weakTabLayout.setupWithViewPager(weakViewPager);

                if (weakAnimeTitle!=null) animeTitle.setText(profileBody.getUsername());

                Glide.with(getContext()).load(profileBody.getImage_url()).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //TODO NoPicture
                        weakProgress.setVisibility(View.GONE);
                        weakConstraint.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        weakProgress.setVisibility(View.GONE);
                        weakConstraint.setVisibility(View.GONE);
                        return false;
                    }
                }).into(weakImageView);
            }
        }
    }

    @Override
    public void onUserNotFound() {
        Toast.makeText(getContext().getApplicationContext(), "User Not Found", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
