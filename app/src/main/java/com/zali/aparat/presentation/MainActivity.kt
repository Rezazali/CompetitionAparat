package com.zali.aparat.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.zali.aparat.R
import com.zali.aparat.databinding.ActivityMainBinding
import com.zali.aparat.util.BackPressedHandler


class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private var currentFragment: Fragment? = null

    private  val TAG = "MainActivity"

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
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        currentFragment = navHostFragment.childFragmentManager.fragments.lastOrNull()
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
    override fun onBackPressed() {
        val fragment = currentFragment
        if (fragment is BackPressedHandler) {
            fragment.onBackPressed()
            Log.d(TAG, "onBackPressed: Handled by fragment")
        } else {
            //super.onBackPressed()
            Log.d(TAG, "onBackPressed: Handled by activity")
        }
    }


}