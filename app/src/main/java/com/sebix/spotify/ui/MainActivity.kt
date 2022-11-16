package com.sebix.spotify.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sebix.spotify.R
import com.sebix.spotify.models.Song

class MainActivity : AppCompatActivity() {
    var playSongImageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.main_view)
        bottomNavigationView.setupWithNavController(navController)
        playSongImageView = findViewById(R.id.playSongImageView)
        setListeners()
    }

    private fun setListeners() {
        playSongImageView?.setOnClickListener {
            playSongImageView!!.setImageResource(R.drawable.ic_baseline_play_arrow_black_24)
        }
    }

    fun showPlayView(song: Song) {
        playSongImageView!!.setImageResource(R.drawable.ic_baseline_pause_24)
        findViewById<LinearLayout>(R.id.playSongLayout).visibility = View.VISIBLE
        findViewById<TextView>(R.id.playSongName).text = song.name
    }
}