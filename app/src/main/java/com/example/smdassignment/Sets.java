package com.example.smdassignment;

import java.util.ArrayList;

public class Sets {
    private String titleSet;
    private ArrayList<Cards> dataset;

    public Sets(String title, ArrayList<Cards> data){
        titleSet = title;
        dataset = data;
    }
    public void setTitle(String title){titleSet = title;}
    public String getTitle(){return titleSet;}
    public void setDataset(ArrayList<Cards> data){dataset=data;}
    public ArrayList<Cards> getDataset(){return dataset;}
    public int getNumOfCards(){
        return dataset.size();
    }
}
