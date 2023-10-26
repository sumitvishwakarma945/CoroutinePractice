package com.example.coroutinepractice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.coroutinepractice.R
import com.example.coroutinepractice.databinding.HomeFragmentBinding
import com.example.coroutinepractice.ui.version.VersionFragment


class HomeFragment:Fragment(R.layout.home_fragment) {
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textHomeFragment.text = "Welcome to Home Page Fragment"
        binding.btnOpenVersionFragment.setOnClickListener {
            //replacing fragment by getting activity.supportFragmentManager
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.home_container_view, VersionFragment())
                addToBackStack(null)
            }
        }
    }

}