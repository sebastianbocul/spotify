package com.sebix.spotify.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sebix.spotify.R
import com.sebix.spotify.models.Song

class SongsAdapter(
    private val context: Context,
    private val songs: ArrayList<Song>
) :
    RecyclerView.Adapter<SongsAdapter.SongViewHolder>() {
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val currentItem: Song = songs[position]
        holder.name.text = currentItem.name
        // use glide here
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun getItem(position: Int): Song {
        return songs[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class SongViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val imageView: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            imageView = itemView.findViewById(R.id.image)
            imageView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }
    }
}