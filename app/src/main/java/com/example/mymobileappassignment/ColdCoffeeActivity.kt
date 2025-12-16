package com.example.mymobileappassignment

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ColdCoffeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Show the cold coffee menu as this Activity's content
        setContentView(R.layout.fragment_cold_cafee)

        val btnColdCafee1Capuucina = findViewById<LinearLayout>(R.id.btnColdCafee1Capuucina)

        // Open the AddToCardFragment when the first card is clicked
        btnColdCafee1Capuucina.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(
                    android.R.id.content,
                    AddToCardFragment.newInstance("Cappuccino", "1.5")
                )
                .addToBackStack(null)
                .commit()
        }
    }
}
