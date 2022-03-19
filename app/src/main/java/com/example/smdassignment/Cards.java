package com.example.smdassignment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class Cards implements Serializable {
    private String frontData;
    private String BackData;
    public Cards(String fdata, String bdata){
        frontData = fdata;
        BackData = bdata;
    }

    public String getTitle(){
        return frontData;
    }
    public String getBackData(){
        return BackData;
    }
    public void setTitle(String title){
        frontData = title;
    }
    public void setBackData(String bData){
        BackData = bData;
    }


}
