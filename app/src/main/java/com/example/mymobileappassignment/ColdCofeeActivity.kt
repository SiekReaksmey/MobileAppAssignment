package com.example.mymobileappassignment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymobileappassignment.databinding.ActivityTestColdBinding

class ColdCofeeActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestColdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTestColdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnColdCafee1Capuucina = findViewById<LinearLayout>(R.id.btnColdCafee1Capuucina)

        binding.apply {
            btnColdCafee1Capuucina.setOnClickListener {
                val intent = Intent(this@ColdCofeeActivity, AddToCardFragment::class.java)
                startActivity(intent)
            }
        }
    }
}
