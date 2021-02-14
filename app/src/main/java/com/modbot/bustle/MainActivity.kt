package com.modbot.bustle

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.modbot.bustle.databinding.ActivityMainBinding

@Suppress("DEPRECATION", "DEPRECATED_IDENTITY_EQUALS")
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)


        /**
         * Change status bar according to screen
         */
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

        /**
         * Request for location permission
         */
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeStatusBarColor(blue_color: Boolean = false) {
        if (android.os.Build.VERSION.SDK_INT <= 21)
            return
        if (blue_color) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
            window.decorView.systemUiVisibility = 0
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * Requests for location permission
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}