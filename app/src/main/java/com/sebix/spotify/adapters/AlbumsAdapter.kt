package com.sebix.spotify.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sebix.spotify.R
import com.sebix.spotify.models.Album

class AlbumsAdapter(
    private val context: Context,
    private val albums: ArrayList<Album>
) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentItem: Album = albums[position]
        holder.name.text = currentItem.name
        holder.artist.text = currentItem.artist
        // use glide here
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun getItem(position: Int): Album {
        return albums[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class AlbumViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val artist: TextView
        val imageView: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            artist = itemView.findViewById(R.id.artist)
            imageView = itemView.findViewById(R.id.image)
            itemView.setOnClickListener {
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