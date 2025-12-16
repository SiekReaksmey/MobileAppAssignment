package com.example.mymobileappassignment

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymobileappassignment.databinding.ActivityTestColdBinding

class TestCold : AppCompatActivity() {
    lateinit var binding: ActivityTestColdBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTestColdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var intent = intent
        var listItem = intent.getSerializableExtra("STUDENT", Product::class.java) as Product
        binding.apply {
            //lblId.text = listItem.id.toString() + " " + listItem.name + " " + listItem.score.toString();
        }
    }
}