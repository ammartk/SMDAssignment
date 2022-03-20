package com.example.smdassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class CardActivity extends AppCompatActivity {
    EditText front;
    EditText back;
    int currentCardID;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        front = (EditText) findViewById(R.id.FrontCardData);
        back = (EditText) findViewById(R.id.BackCardData);
        Intent intent = getIntent();
        currentCardID = intent.getIntExtra("id", 0);
        Cards card = (Cards) intent.getSerializableExtra("card");
        if(card != null) {
            front.setText(card.getTitle());
            back.setText(card.getBackData());
        }
    }
    public void buttonClick(View v){

        if(v.getId() == R.id.button2){
            saveNote();
        }

        if(v.getId() == R.id.button){
            cancelNote();
        }

    }
    private void saveNote(){
        Intent result = new Intent();
        Cards card = new Cards(front.getText().toString(), back.getText().toString());
        result.putExtra("content",(Serializable) card);
        result.putExtra("id",currentCardID);
        setResult(RESULT_OK,result);
        finish();
    }

    private void cancelNote(){
        Intent result = new Intent();
        setResult(RESULT_CANCELED,result);
        finish();
    }

}
