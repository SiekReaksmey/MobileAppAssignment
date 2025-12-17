package com.example.mymobileappassignment

import android.os.Bundle
import android.view.View
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
        binding.btnNavigation.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding.btnNavigation.visibility = View.GONE
    }

    fun navigateToProductDetail(name: String, price: String, imageRes: Int) {
        val fragment = AddToCardFragment.newInstance(name, price)
        val args = fragment.arguments ?: Bundle()
        args.putInt("IMAGE_RES_ID", imageRes)
        args.putBoolean("IS_EDIT_MODE", false)
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigateToProductDetailForEdit(name: String, price: String, imageResId: Int, quantity: Int) {
        val fragment = AddToCardFragment.newInstance(name, price)
        val args = fragment.arguments ?: Bundle()
        args.putInt("IMAGE_RES_ID", imageResId)
        args.putBoolean("IS_EDIT_MODE", true)
        args.putInt("CURRENT_QUANTITY", quantity)
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigateToCart() {
        showBottomNav()
        replaceFragment(CartFragment())
        // Set cart icon as selected/active in bottom navigation after fragment is replaced
        // This ensures the icon appears active when navigating to cart
        binding.btnNavigation.post {
            binding.btnNavigation.selectedItemId = R.id.btnCart
        }
    }
}
