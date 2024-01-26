package com.zali.aparat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import com.zali.aparat.R
import com.zali.aparat.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import com.zali.aparat.util.BackPressedHandler


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // Initialize NavController here
        navController = findNavController(R.id.nav_host_fragment)
        observeNavElements(navController)
    }

    private fun observeNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.splashFragment -> {
                    hideBothNavigation()
                }

                R.id.homeFragment -> {
                    showBothNavigation()
                }
                R.id.movieDetailFragment -> {
                    hideBothNavigation()
                }
            }
        }
    }

    private fun hideBothNavigation() {
        binding.bottomNav.visibility = View.GONE
    }
    private fun showBothNavigation() {
        binding.bottomNav.visibility = View.VISIBLE
    }

}