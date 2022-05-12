package com.example.smdassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputSetActivity extends AppCompatActivity {
    EditText input;
    int currentID;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputset);
        input = (EditText) findViewById(R.id.inputset);
        Intent i = getIntent();
        currentID = i.getIntExtra("id", 0);
    }

    public void clickListener(View v){
        if (v.getId() == R.id.button3) {
            Intent i = new Intent();
            i.putExtra("id", currentID);
            i.putExtra("content", input.getText().toString());
            setResult(RESULT_OK, i);
        }
        finish();
    }


}
