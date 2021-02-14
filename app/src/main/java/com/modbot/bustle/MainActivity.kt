package com.modbot.bustle

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.modbot.bustle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme) //Splash Screen

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Bustle)
        /**
         * Set night mode to false
         */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        /**
         * Binding the views
         */
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Setup navigation and connect with  toolbar and fragments
         */
        val toolBarDestinations = setOf(
            R.id.routeFragment,
            R.id.busStopTimelineFragment,
            R.id.busDetailsFragment
        )
        val blueStatusBarDestinations = setOf(
            R.id.loginDetailsFragment,
            R.id.loginMainFragment,
            R.id.loginSignUp
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in toolBarDestinations) {
                changeStatusBarColor()
                binding.toolbar.visibility = View.VISIBLE
            } else if (destination.id in blueStatusBarDestinations) {
                binding.toolbar.visibility = View.GONE
                changeStatusBarColor(true)
            } else {
                changeStatusBarColor()
                binding.toolbar.visibility = View.GONE
            }
        }
    }

    private fun changeStatusBarColor(blue_color: Boolean = false) {
        if (android.os.Build.VERSION.SDK_INT <= 21)
            return
        if (blue_color) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
            window.decorView.setSystemUiVisibility(0);
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}