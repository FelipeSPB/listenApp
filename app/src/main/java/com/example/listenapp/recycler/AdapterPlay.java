package com.example.listenapp.recycler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.listenapp.R;
import com.example.listenapp.model.Playlist;

import java.util.ArrayList;


public class AdapterPlay extends RecyclerView.Adapter<PlaylistsViewHolder> implements Filterable {
    ArrayList<Playlist> dataSet;
    ArrayList<Playlist> dataSetFull;
    Activity activity;

    public AdapterPlay(ArrayList dataSet) {
        this.dataSet = dataSet;
        this.dataSetFull = new ArrayList<Playlist>(dataSet);
    }

    @NonNull
    @Override
    public PlaylistsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        activity =(Activity) viewGroup.getContext();
        View layout = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.playlists_viewholder, viewGroup, false);
        return new PlaylistsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistsViewHolder ViewHolder, int position) {

        final Playlist playlist = dataSet.get(position);
        ViewHolder.textViewContent.setText(playlist.getPlaylistName());
        ViewHolder.textViewQuantity.setText(playlist.getQuantity().toString());
        if (playlist.getPlaylistImage() == null) {
            ViewHolder.imageView.setImageResource(R.drawable.ic_vinyl_record);
        }
        else{
            ViewHolder.imageView.setImageResource(playlist.getPlaylistImage());
        }

            ViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
    }

    @Override
    public int getItemCount() {

        return dataSet.size();

    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Playlist> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Playlist item : dataSet) {
                    if (item.getPlaylistName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((ArrayList<Playlist>) results.values);
            notifyDataSetChanged();
        }
    };
    public void updateDataSet(){
        dataSetFull.clear();
        dataSetFull.addAll(dataSet);
    }


}
