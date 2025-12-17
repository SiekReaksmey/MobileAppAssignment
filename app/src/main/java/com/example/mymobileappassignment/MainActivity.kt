package com.example.mymobileappassignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mymobileappassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        hideBottomNav()
        replaceFragment(LandingFragment())

        // Bottom Navigation Listener
        binding.btnNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.btnHome -> {
                    showBottomNav()
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.btnMenu -> {
                    showBottomNav()
                    replaceFragment(MenuFragment())
                    true
                }

                R.id.btnUserProfile -> {
                    showBottomNav()
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.btnCart -> {
                    showBottomNav()
                    replaceFragment(CartFragment())
                    true
                }

                R.id.btnLove -> {
                    showBottomNav()
                    replaceFragment(FavoriteFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    fun showBottomNav() {
        binding.navContainer.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding.navContainer.visibility = View.GONE
    }
}
