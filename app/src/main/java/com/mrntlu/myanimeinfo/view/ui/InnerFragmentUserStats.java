package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;

public class InnerFragmentUserStats extends Fragment {

    private UserProfileResponseBody profileBody;

    public InnerFragmentUserStats() {
        // Required empty public constructor
    }

    public static InnerFragmentUserStats newInstance(UserProfileResponseBody profileBody) {
        InnerFragmentUserStats fragment = new InnerFragmentUserStats();
        fragment.profileBody=profileBody;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inner_user_stats, container, false);
        TextView watchingText = v.findViewById(R.id.watchingText);
        TextView droppedText = v.findViewById(R.id.droppedText);
        TextView episodesWatchedText = v.findViewById(R.id.episodesWatchedText);
        TextView daysWatchedText = v.findViewById(R.id.daysWatchedText);
        TextView planToWatchText = v.findViewById(R.id.planToWatchText);
        TextView meanScoreText = v.findViewById(R.id.meanScoreText);
        TextView completedText = v.findViewById(R.id.completedText);
        TextView onHoldText = v.findViewById(R.id.onHoldText);

        UserProfileResponseBody.AnimeStats stats=profileBody.getAnime_stats();
        completedText.setText(String.valueOf(stats.getCompleted()));
        watchingText.setText(String.valueOf(stats.getWatching()));
        droppedText.setText(String.valueOf(stats.getDropped()));
        onHoldText.setText(String.valueOf(stats.getOn_hold()));
        episodesWatchedText.setText(String.valueOf(stats.getEpisodes_watched()));
        daysWatchedText.setText(String.valueOf(stats.getDays_watched()));
        planToWatchText.setText(String.valueOf(stats.getPlan_to_watch()));
        meanScoreText.setText(String.valueOf(stats.getMean_score()));
        return v;
    }

}
