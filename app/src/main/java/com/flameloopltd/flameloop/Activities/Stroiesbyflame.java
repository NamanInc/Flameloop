package com.flameloopltd.flameloop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.flameloopltd.flameloop.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class Stroiesbyflame extends AppCompatActivity {
    private PlayerView exovideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroiesbyflame);
        getSupportActionBar().hide();

        exovideo = findViewById(R.id.exoplyerview);


        String uri = "https://firebasestorage.googleapis.com/v0/b/flameloop.appspot.com/o/The%20legend%20of%20Annapurna%2C%20Hindu%20goddess%20of%20nourishment%20-%20Antara%20Raychaudhuri%20%26%20Iseult%20Gillespie.mp4?alt=media&token=ae8abe48-6736-4037-82a1-b8d22ffe57c2";

       SimpleExoPlayer simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

        exovideo.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(uri);
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();
        simpleExoPlayer.setPlayWhenReady(true);

    }


    }

