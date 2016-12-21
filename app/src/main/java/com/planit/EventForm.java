package com.planit;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andrew on 7/27/2016.
 */
public class EventForm extends Activity {

    private final int SPEECH_RECOGNITION_CODE = 1;
    ArrayList<String> addEvents = new ArrayList<>();
    private EditText whoEdit;
    private EditText whatEdit;
    private EditText whenEdit;
    private EditText whereEdit;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventformlayout);

//        EventBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvent);
        toolbar.setTitle("Add Event");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        //TODO: Add Event


//        ButtonHide
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.hide();


        callText();
        //Who
        TextView whoText = (TextView) findViewById(R.id.whoText);
        whoText.setText(R.string.Who);


        //What
        TextView whatText = (TextView)findViewById(R.id.whatText);

        //When
        TextView whenText = (TextView)findViewById(R.id.whenText);

        //Where
        TextView whereText = (TextView)findViewById(R.id.whereText);

        //Add: Adds onto ManageEvents
        Button addButton = (Button)findViewById(R.id.addButton);

        //Cancel: Moves Back to Activity
        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton voiceBtn = (ImageButton)findViewById(R.id.Voice_btn);
        voiceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //TODO: Voice Recognition
                startSpeechToText();
            }
        });
    }

    private void callText(){
        whoEdit = (EditText)findViewById(R.id.whoedit);
        whatEdit = (EditText)findViewById(R.id.whatedit);
        whenEdit = (EditText)findViewById(R.id.whenedit);
        whereEdit = (EditText)findViewById(R.id.whereedit);
    }

    private void startSpeechToText(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Who, What, When, Where");

        try{
            startActivityForResult(intent,SPEECH_RECOGNITION_CODE);
        }catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Not Supported On your Device", Toast.LENGTH_SHORT).show();
        }
    }
//TODO: Fix input mechanism
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case SPEECH_RECOGNITION_CODE:
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    Log.d("Speech Result",text);
                    String[] voiceInput = text.split("(who)|(what)|(when)|(where)");

                    String who = "who";
                    String what = "what";
                    String when = "when";
                    String where = "where";

                    if(text.toLowerCase().contains(who.toLowerCase())) {
                        String whoTexts = voiceInput[1].trim();
                        whoEdit.setText(whoTexts);
                    }

                    if(text.toLowerCase().contains(what.toLowerCase())) {
                        String whatTexts = voiceInput[2].trim();
                        whatEdit.setText(whatTexts);
                    }

                    if(text.toLowerCase().contains(when.toLowerCase())) {
                        String whenTexts = voiceInput[3].trim();
                        whenEdit.setText(whenTexts);
                    }

                    if(text.toLowerCase().contains(where.toLowerCase())) {
                        String whereTexts = voiceInput[4].trim();
                        whereEdit.setText(whereTexts);
                    }
                }
                break;
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
    }
}
