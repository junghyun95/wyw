package com.greedy.wouldyouwalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.wouldyouwalk.databinding.ActivityJoinBinding
import com.greedy.wouldyouwalk.databinding.ActivityMainBinding

class JoinActivity : AppCompatActivity() {

    val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}