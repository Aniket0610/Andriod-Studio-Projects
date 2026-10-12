package com.example.animationact;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;

import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgCar;
    Button btnAnimate, btnStop, btnPlay, btnPause;
    Button btnVideo, btnRewind, btnForward, btnStopVideo;
    VideoView videoView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect UI components
        imgCar = findViewById(R.id.imgCar);
        btnAnimate = findViewById(R.id.btnAnimate);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnVideo = findViewById(R.id.btnVideo);
        videoView = findViewById(R.id.videoView);
        btnStop = findViewById(R.id.btnStop);
        btnRewind = findViewById(R.id.btnRewind);
        btnForward = findViewById(R.id.btnForward);
        btnStopVideo = findViewById(R.id.btnStopVideo);

        // ---------------- ANIMATION ----------------

        btnAnimate.setOnClickListener(v -> {

            TranslateAnimation animation =
                    new TranslateAnimation(
                            -800, 1000,
                            0, 0
                    );

            animation.setDuration(500);

            animation.setRepeatCount(Animation.INFINITE);

            imgCar.startAnimation(animation);
        });

        // ---------------- AUDIO ----------------

        mediaPlayer = MediaPlayer.create(
                this,
                R.raw.sample_audio
        );

        btnPlay.setOnClickListener(v -> {

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        btnStop.setOnClickListener(v -> {
            imgCar.clearAnimation();
        });

        btnPause.setOnClickListener(v -> {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        // ---------------- VIDEO ----------------

        Uri videoUri = Uri.parse(
                "android.resource://" + getPackageName() + "/" +
                        R.raw.sample_video
        );

        videoView.setVideoURI(videoUri);

        MediaController mediaController =
                new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        btnVideo.setOnClickListener(v -> {
            videoView.start();
        });
        btnRewind.setOnClickListener(v -> {

            int currentPosition = videoView.getCurrentPosition();

            int newPosition = currentPosition - 10000;

            if (newPosition < 0) {
                newPosition = 0;
            }

            videoView.seekTo(newPosition);
        });

        btnForward.setOnClickListener(v -> {

            int currentPosition = videoView.getCurrentPosition();

            int newPosition = currentPosition + 5000;

            if (newPosition > videoView.getDuration()) {
                newPosition = videoView.getDuration();
            }

            videoView.seekTo(newPosition);
        });

        btnStopVideo.setOnClickListener(v -> {
            videoView.pause();
            videoView.seekTo(0);
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}