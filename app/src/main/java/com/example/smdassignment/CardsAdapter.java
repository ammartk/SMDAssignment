package com.example.smdassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{

    ArrayList<Cards> data;


    public class CardsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public CardsViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.FrontCardData);

        }
    }

    public CardsAdapter(ArrayList<Cards> myDataset) {
        data = myDataset;
    }
    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_list_item, parent, false);
        CardsViewHolder vh = new CardsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        String content = data.get(position).getTitle();
        holder.title.setText(content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
