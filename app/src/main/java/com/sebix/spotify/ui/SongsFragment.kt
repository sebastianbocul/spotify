package com.sebix.spotify.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sebix.spotify.R
import com.sebix.spotify.adapters.AlbumsAdapter
import com.sebix.spotify.adapters.SongsAdapter
import com.sebix.spotify.models.Album
import com.sebix.spotify.models.Song

class SongsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val album = arguments?.getParcelable<Album>("album")
        album?.name?.let {
            view.findViewById<TextView>(R.id.albumName).text = it
        }
        updateRecyclerViewsWithTestData()
    }

    private fun updateRecyclerViewsWithTestData() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.songsRecyclerView)
        val horizontalLayoutManager =
            LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        val songs = createExampleSongsList()
        val adapter = SongsAdapter(requireContext(), songs)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : SongsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                (activity as MainActivity).showPlayView(songs[position])
            }
        })
    }

    private fun createExampleSongsList(): ArrayList<Song> {
        val songs = arrayListOf<Song>()
        for (x in 0..20) {
            songs.add(
                Song(
                    "name$x",
                    "artist$x",
                    "https://images.template.net/wp-content/uploads/2017/02/04000423/Album-Icons.jpg?width=450"
                )
            )
        }
        return songs
    }
}