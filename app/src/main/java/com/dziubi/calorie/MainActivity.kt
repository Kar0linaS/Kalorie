package com.dziubi.calorie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dziubi.calorie.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val mainVm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavVisibilty(mainVm.isBottomNavVisible)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationMenu, navController)

        binding.przycisk.setOnClickListener {
            setBottomNavVisibilty(false)
            navController.navigate(R.id.dodaj)
        }
    }

    fun navigateToEdit() {
        setBottomNavVisibilty(false)
        navController.navigate(R.id.edytuj)
    }

    fun setBottomNavVisibilty(bool: Boolean) {
        mainVm.isBottomNavVisible = bool

        val isVisible = when (bool) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
        binding.cardView.visibility = isVisible
        binding.przycisk.visibility = isVisible

    }

}



