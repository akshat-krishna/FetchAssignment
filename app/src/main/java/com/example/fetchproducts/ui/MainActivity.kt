package com.example.fetchproducts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchproducts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load ListFragment
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, ListFragment())
            .commit()
    }
}