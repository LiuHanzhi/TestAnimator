package test.lhz.com.testanimator.player;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import test.lhz.com.testanimator.R;
import test.lhz.com.testanimator.UITool;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/12
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class PlayerFragment extends Fragment {

    KrPlayerView playerView;

    void onPlayerViewClick() {
        Log.e("lhz", "onPlayerViewClick");
        playerView.setUseController(true);
        UITool.zoomViewFull(playerView);
        playerView.setPlayControlView((PlayerControlView) UITool.inflate(getActivity(), R.layout.full_player_control_view, null));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        playerView = view.findViewById(R.id.player_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        playerView.setPlayControlView((PlayerControlView) LayoutInflater.from(getActivity()).inflate(R.layout.half_player_control_view, null));
        playerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                onPlayerViewClick();
            }
        }, 8000);
        UITool.zoomViewByWidth(120, 120 * UITool.getScreenHeight(getActivity()) / UITool.getScreenWidth(getActivity()), playerView);
        initPlayer();
    }

    @Override
    public void onDetach() {
        if(playerView != null){
            playerView.getPlayer().stop();
        }
        super.onDetach();
    }

    private void initPlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();

        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(getActivity());
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
        playerView.setPlayer(player);

        play(player);


    }

    private void play(ExoPlayer player) {
        if (getActivity() == null || getActivity().getIntent() == null) {
            return;
        }
        Intent intent = getActivity().getIntent();
        String url = intent.getStringExtra("url");
        if(TextUtils.isEmpty(url)){
            url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        }
        PlayerActivity.MEDIA_TYPE mediaType = PlayerActivity.MEDIA_TYPE.getType(intent.getIntExtra("type", 1));

        Uri uri = Uri.parse(url);
        String userAgent = Util.getUserAgent(getActivity(), getActivity().getPackageName());
        if (mediaType == PlayerActivity.MEDIA_TYPE.DASH) {
            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, new DefaultBandwidthMeter());
            DashChunkSource.Factory chunkSourceFactory = new DefaultDashChunkSource.Factory(defaultHttpDataSourceFactory);
            MediaSource dashSource = new DashMediaSource
                    .Factory(chunkSourceFactory, defaultHttpDataSourceFactory)
//                .setManifestParser(new DashManifestParser())
                    .createMediaSource(uri);
            player.prepare(dashSource);
        } else {
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), userAgent);
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            player.prepare(videoSource);


        }
        player.setPlayWhenReady(true);
    }
}
