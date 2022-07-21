package il.co.syntax.finalkotlinproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentByTag("fragment") as NavHostFragment
        navController = navHostFragment.navController
        val bottomNav = binding.bottomNav
        NavigationUI.setupWithNavController(bottomNav, navController)
        navController.addOnDestinationChangedListener { _, item, _ ->

        }
    }
}