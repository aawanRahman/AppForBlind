package com.example.appforblind;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.appforblind.utils.AudioPlayer;
import com.gauravk.audiovisualizer.visualizer.BlastVisualizer;

public class HomePage extends AppCompatActivity {
  private   BlastVisualizer visualizer;
    //private MediaPlayer mAudioPlayer;
    //MediaPlayer mAudioPlayer;
    private   AudioPlayer mAudioPlayer;

    private LocationIdentification locationIdentifier;
    private VoiceRecognition voiceRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //
        mAudioPlayer = new AudioPlayer();
        //mAudioPlayer.setVolume();
        visualizer = findViewById(R.id.blast);
        runVisualizerThread();
        
        //runVisualizerThread(R.raw.sample);
      //  startPlayingAudio(R.raw.sample);
        locationIdentifier = new LocationIdentification();


    }

    private void startPlayingAudio(int resId  ) {
        Log.d("session id", "audio Session Id");

        mAudioPlayer.play(this, resId, new AudioPlayer.AudioPlayerEvent() {
            @Override
            public void onCompleted() {
                if (visualizer != null)
                    visualizer.hide();
            }
        });

        int audioSessionId = mAudioPlayer.getAudioSessionId();
        Log.d("session id", "audio Session Id"+audioSessionId);
        if (audioSessionId != -1) {
            visualizer.setAudioSessionId(audioSessionId);
        }
    }

    private void runVisualizerThread(){
        new Thread(){
            @Override
            public void run() {
                while (true) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                startPlayingAudio(R.raw.sample);

                                Log.d("send input", "input is sent");


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            //startPlayingAudio(R.raw.sample);

                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();


    }
    public void VoiceRecognition() {


    }

}