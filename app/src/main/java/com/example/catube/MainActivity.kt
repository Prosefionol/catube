package com.example.catube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.catube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActionBar {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return goUp() || super.onSupportNavigateUp()
    }

    override fun setTitle(title: String) {
        val correctTitle = if (title.length > 30) {
            "${title.substring(0, 29)}..."
        } else {
            title
        }
        this.supportActionBar?.title = correctTitle
    }

    override fun setupBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun goUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main) as NavHostFragment
        navController = navHostFragment.navController
        return navController.navigateUp()
    }
}
