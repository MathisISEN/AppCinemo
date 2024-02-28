package com.example.appcinemo.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.appcinemo.R
import com.example.appcinemo.ui.fragments.MovieFragment
import com.example.cinemo.ui.fragments.SearchFragment
import com.example.cinemo.ui.fragments.TvShowFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,BottomNavigationView.OnNavigationItemSelectedListener{

    lateinit var bottomNavMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        bottomNavMenu = findViewById(R.id.bottomNavMenu)
        bottomNavMenu.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_activityMain, MovieFragment())
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeMenu -> {
                val fragment = MovieFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.tvShowMenu -> {
                val fragment = TvShowFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.searchMenu -> {
                val fragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer_activityMain, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
        }
        return false
    }


}