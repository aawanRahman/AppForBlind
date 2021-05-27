package com.example.appforblind;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appforblind.utils.AudioPlayer;
import com.gauravk.audiovisualizer.visualizer.BlastVisualizer;

public class HomePage extends AppCompatActivity {
  private   BlastVisualizer visualizer;
    //private MediaPlayer mAudioPlayer;
    //MediaPlayer mAudioPlayer;
    private   AudioPlayer mAudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //
        mAudioPlayer = new AudioPlayer();
        visualizer = findViewById(R.id.blast);
        runVisualizerThread();
        
        //runVisualizerThread(R.raw.sample);
      //  startPlayingAudio(R.raw.sample);


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

}