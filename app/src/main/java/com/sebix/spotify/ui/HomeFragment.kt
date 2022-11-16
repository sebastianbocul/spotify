package com.sebix.spotify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sebix.spotify.R
import com.sebix.spotify.adapters.AlbumsAdapter
import com.sebix.spotify.models.Album
import com.sebix.spotify.models.Song


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateRecyclerViewsWithTestData(R.id.albumsNewRecyclerView)
        updateRecyclerViewsWithTestData(R.id.albumsFavRecyclerView)
    }

    private fun updateRecyclerViewsWithTestData(id: Int) {
        val recyclerView = requireView().findViewById<RecyclerView>(id)
        val horizontalLayoutManager =
            LinearLayoutManager(requireView().context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        val albums = createExampleAlbumsList()
        val adapter = AlbumsAdapter(requireContext(), albums)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : AlbumsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val navController = Navigation.findNavController(requireView())
                val extras = Bundle()
                val album = albums[position]
                extras.putParcelable("album", album)
                navController.navigate(R.id.action_homeFragment_to_songsFragment, extras)
            }
        })
    }

    private fun createExampleAlbumsList(): ArrayList<Album> {
        val albums = arrayListOf<Album>()
        for (x in 0..10) {
            albums.add(
                Album(
                    "album$x",
                    "artist$x",
                    "https://images.template.net/wp-content/uploads/2017/02/04000423/Album-Icons.jpg?width=450"
                ),
            )
        }
        return albums
    }
}