package com.example.appforblind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static com.google.android.gms.location.LocationServices.*;

public class MainActivity extends AppCompatActivity {
    private Button launch_button;
    LocationProvider location;
    private FusedLocationProviderClient fusedLocationClient;
    private SpeechRecognizer speechRecognizer, destinationRecognizer;
    private TextToSpeech textToSpeech;
    private  Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        destinationRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // finding destination........................................................
        destinationRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> Destination = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String string ="";




            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });



        // Main Brunch............................
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> stringtext = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String stringval ="";
                if(stringtext!= null) {
                    stringval = stringtext.get(0);

                    if(stringval.equals("Destination")) {

                        textToSpeech.speak("Say your destination", TextToSpeech.QUEUE_FLUSH, null, null);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stringtext.clear();
                        speechRecognizer.startListening(intent);


                       // speechRecognizer.stopListening();
                        //intent = new Intent(Intent.ACTION_VIEW);
                        //intent.setData(Uri.parse("geo:47.4925, 19.0513"));

/*
                        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {

                            if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                fusedLocationClient.getLastLocation()
                                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(Location location) {
                                                if (location!=null) {
                                                    Double lat = location.getLatitude();
                                                    Double longt = location.getLongitude();
                                                    String value  = "google.streetview:cbll="+String.valueOf(lat)+", "+String.valueOf(longt);
                                                    Uri ggmmIntentUri = Uri.parse(value);
                                                    Uri gmmIntentUri = Uri.parse("google.navigation:q=Banani+Model+Town,+Dhaka");
                                                    intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                                    intent.setPackage("com.google.android.apps.maps");
                                                    startActivity(intent);
                                                    //intent.setData(Uri.parse(value));
                                                    Log.d("location.", String.valueOf(lat));

                                                }

                                            }
                                        });



                            } else{
                                Log.d("location.", "my name is awan");
                                requestPermissions(new  String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                            }
                            // requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},validateRequestPermissionsRequestCode(););
                        }
                        //Intent chooser = Intent.createChooser(intent, "launch map");
                        // Intent chooser = Intent.createChooser(intent, title:"launch map");
                        //startActivity(chooser);


                       // OpenGoogleMap();

 */

                    }
                    else {
                        textToSpeech.speak("Finding the best route for your travel", TextToSpeech.QUEUE_FLUSH, null, null);


                        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {

                            if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                fusedLocationClient.getLastLocation()
                                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(Location location) {
                                                if (location!=null) {
                                                    Double lat = location.getLatitude();
                                                    Double longt = location.getLongitude();
                                                    String value  = "google.navigation:q=" + stringtext.get(0) ;
                                                    Uri ggmmIntentUri = Uri.parse(value);
                                                    Uri gmmIntentUri = Uri.parse(value);
                                                    intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                                    intent.setPackage("com.google.android.apps.maps");
                                                    startActivity(intent);
                                                    //intent.setData(Uri.parse(value));
                                                    Log.d("location.", String.valueOf(lat));

                                                }

                                            }
                                        });



                            } else{
                                Log.d("location.", "my name is awan");
                                requestPermissions(new  String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                            }
                            // requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},validateRequestPermissionsRequestCode(););
                        }



                        //textToSpeech.speak("Speech not recognized", TextToSpeech.QUEUE_FLUSH, null, null);
                    }



                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });




        launch_button = findViewById(R.id.button1);
        launch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak("How can I help you", TextToSpeech.QUEUE_FLUSH, null, null);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speechRecognizer.startListening(intent);
/*

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("geo:47.4925, 19.0513"));


                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {

                    if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fusedLocationClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        if (location!=null) {
                                            Double lat = location.getLatitude();
                                            Double longt = location.getLongitude();
                                            String value  = "geo"+String.valueOf(lat)+", "+String.valueOf(longt);
                                            intent.setData(Uri.parse(value));
                                            Log.d("location.", String.valueOf(lat));

                                        }

                                    }
                                });



                    } else{
                        Log.d("location.", "my name is awan");
                        requestPermissions(new  String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                        // requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},validateRequestPermissionsRequestCode(););
                }

                Intent chooser = Intent.createChooser(intent, "launch map");
               // Intent chooser = Intent.createChooser(intent, title:"launch map");
                startActivity(chooser);

 */

            }


        });




    }


}