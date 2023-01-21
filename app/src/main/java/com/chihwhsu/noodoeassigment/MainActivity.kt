package com.chihwhsu.noodoeassigment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.chihwhsu.noodoeassigment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var showMenu = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setToolBar()
    }

    private fun setToolBar() {

        setSupportActionBar(binding.toolBar)
        invalidateOptionsMenu()
        setToolBarItemVisibility()

        binding.toolBar.setNavigationOnClickListener {
            findNavController(R.id.myNavHostFragment).navigateUp()
        }

        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.time_zone -> {
                    findNavController(R.id.myNavHostFragment).navigate(NavigationDirections.actionGlobalTimeZoneFragment())
                    true
                }
                else -> {
                    Toast.makeText(this, "${it.itemId}", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    private fun setToolBarItemVisibility() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        val navController = navHostFragment!!.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.loginFragment -> {
                    binding.toolBar.navigationIcon = null
                    showMenu = false
                    invalidateOptionsMenu()
                }

                R.id.parkingLotsFragment -> {
                    binding.toolBar.navigationIcon = null
                    showMenu = true
                    invalidateOptionsMenu()
                }

                R.id.timeZoneFragment -> {
                    binding.toolBar.navigationIcon =
                        getDrawable(R.drawable.ic_baseline_arrow_back_ios_24)
                    showMenu = false
                    invalidateOptionsMenu()
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu?.findItem(R.id.time_zone)?.isVisible = showMenu
        return true
    }

}