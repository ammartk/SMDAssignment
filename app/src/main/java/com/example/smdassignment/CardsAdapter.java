package com.example.smdassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> implements Filterable {

    ArrayList<Cards> data;
    ArrayList<Cards> filteredData;
    CardItemListener listener;
    private Filter filter;

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CardsFilter();
        }
        return filter;
    }
    public class CardsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public CardsViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.Cardtitle);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) v.getTag();
                    listener.onClickListener(filteredData.get(pos), pos);
                }
            });
        }
    }

    public CardsAdapter(ArrayList<Cards> myDataset, CardItemListener ls) {
        data = myDataset;
        filteredData = myDataset;
        listener = ls;
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
        String content = filteredData.get(position).getTitle();
        holder.title.setText(content);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }
    public interface CardItemListener{
        public void onClickListener(Cards n, int pos);
    }

    private class CardsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Cards> filteredList = new ArrayList<Cards>();
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
            filteredData = (ArrayList<Cards>) results.values;
            notifyDataSetChanged();
        }

    }
}
