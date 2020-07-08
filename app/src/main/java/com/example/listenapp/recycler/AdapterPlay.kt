package com.example.listenapp.recycler

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import com.example.listenapp.R
import com.example.listenapp.custom.newPanel
import com.example.listenapp.custom.recyclerAdapter
import com.example.listenapp.custom.toJsonArray
import com.example.listenapp.model.Playlist
import custom.VerticalRecycler
import custom.onClick
import java.util.*

class AdapterPlay(dataSet: ArrayList<Playlist>) : RecyclerView.Adapter<PlaylistsViewHolder>(), Filterable {
    var dataSet: ArrayList<Playlist>
    lateinit var dataSetFull: ArrayList<Playlist>
    var activity: Activity? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PlaylistsViewHolder {
        activity = viewGroup.context as Activity
        val layout = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.playlists_viewholder, viewGroup, false)
        return PlaylistsViewHolder(layout)
    }

    override fun onBindViewHolder(ViewHolder: PlaylistsViewHolder, position: Int) {
        val playlist = dataSet[position]
        ViewHolder.textViewContent.text = playlist.playlistName
        ViewHolder.textViewQuantity.text = playlist.quantity.toString()
        ViewHolder.imageView.setImageResource(playlist.playlistImage)
        ViewHolder.cardView.onClick {
            newPanel(R.layout.playlist) {
                val closeBtn = findViewById<ImageView>(R.id.panel_close)
                val recycler = findViewById<VerticalRecycler>(R.id.fav_musics)
                val adapter = recyclerAdapter<ItemViewFavorites>(playlist.musicList)

                recycler?.adapter = adapter

                closeBtn?.onClick { dismiss() }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<Playlist>()
            if (constraint.isEmpty()) {
                filteredList.addAll(dataSetFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in dataSet) {
                    if (item.playlistName.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            dataSet.clear()
            dataSet.addAll(results.values as ArrayList<Playlist>)
            notifyDataSetChanged()
        }
    }

    fun updateDataSet() {
        dataSetFull.clear()
        dataSetFull.addAll(dataSet)
    }

    init {
        this.dataSet = dataSet
        dataSetFull = ArrayList<Playlist>(dataSet)
    }
}