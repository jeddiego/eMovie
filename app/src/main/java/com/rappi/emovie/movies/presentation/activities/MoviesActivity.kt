package com.rappi.emovie.movies.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rappi.emovie.R
import com.rappi.emovie.databinding.ActivityEmovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    private lateinit var bind: ActivityEmovieBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityEmovieBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_movies) as NavHostFragment

        navController = navHostFragment.navController.also { it.setGraph(R.navigation.navigation_emovie, intent.extras) }

    }

    override fun onBackPressed() = when (navController.currentDestination?.id) {
        R.id.moviesHomeFragment -> finish()
        else -> super.onBackPressed()
    }
}