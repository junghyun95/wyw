package com.greedy.wouldyouwalk


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.tabs.TabLayoutMediator
import com.greedy.wouldyouwalk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)






        val fragmentList = listOf(MainFragment(), TimerFragment(), RouteRecordFragment(), ParkInfoFragment(), SettingsFragment())

        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList

        binding.viewPager.adapter = adapter

        var tabTitles = listOf<String>("메인", "산책기록", "경로기록", "공원정복", "설정")

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }



}