package com.greedy.wouldyouwalk

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.greedy.wouldyouwalk.databinding.FragmentRouterecordBinding

class RouteRecordFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentRouterecordBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRouterecordBinding.inflate(inflater, container, false)

        return binding.root
    }

}