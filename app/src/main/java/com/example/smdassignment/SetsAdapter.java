package com.example.smdassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.SetsViewHolder> implements Filterable {
    ArrayList<Sets> data;
    ArrayList<Sets> filteredSets;
    private SetsItemClickListener listener;
    private Filter filter;

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new SetsFilter();
        }
        return filter;
    }
    public SetsAdapter(ArrayList<Sets> dataset, SetsItemClickListener ls)
    {data = dataset;
        filteredSets=dataset;
    listener=ls;}
    @NonNull
    @Override
    public SetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sets_list_item, parent, false);
        SetsAdapter.SetsViewHolder vh = new SetsAdapter.SetsViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SetsViewHolder holder, int position) {
        String setTitle = filteredSets.get(position).getTitle();
        int numberOfCards = filteredSets.get(position).getNumOfCards();
        holder.title.setText(setTitle);
        holder.numOfCards.setText("Flash Cards: " + String.valueOf(numberOfCards));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return filteredSets.size();
    }

    public class SetsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView numOfCards;
        public SetsViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.setsTitle);
            numOfCards = (TextView) v.findViewById(R.id.CardsCountText);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) v.getTag();
                    listener.onClick(filteredSets.get(pos), pos);
                }
            });
        }

    }
    public interface SetsItemClickListener{
        public void onClick(Sets n, int pos);
    }

    private class SetsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Sets> filteredList = new ArrayList<Sets>();
                for(int i=0; i < data.size(); i++){
                    if(data.get(i).getTitle().contains(constraint)){
                        filteredList.add(data.get(i));
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            }
            else{
                results.count = data.size();
                results.values = data;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredSets = (ArrayList<Sets>) results.values;
            notifyDataSetChanged();
        }

    }
}
