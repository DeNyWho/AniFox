package com.example.anifox

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        val valueInPixels =
            resources.getDimension(R.dimen.marginFragment).roundToInt()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.myListFragment2, R.id.profileFragment, R.id.animeFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE

                    fragment.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(0, 0, 0, valueInPixels)
                    }
                }

                else -> {
                    bottomNavigationView.visibility = View.GONE
                    fragment.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(0, 0, 0, 0)
                    }
                }
            }
            bottomNavigationView.setupWithNavController(navController)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
