package com.example.coroutinepractice.ui.version

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.VersionFragmentBinding
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.ui.incidents.IncidentActivity
import com.example.coroutinepractice.viewModels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class VersionFragment:BaseFragment() {
    private lateinit var binding: VersionFragmentBinding
    private var versionRequestItem = VersionRequestItem()
    private lateinit var myViewModel:MyViewModel
    private var appVersion:String? = null
    private var versionApp = "VERSION_APP"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(versionApp, appVersion)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VersionFragmentBinding.inflate(inflater, container, false)

        //Saving state of variable on configuration change
        if(savedInstanceState!=null){
            appVersion = savedInstanceState.getString(versionApp)
            binding.textVersionFragment.text = appVersion
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        binding.btnOpenVersionFragment.setOnClickListener {
            callVersion()
        }
        binding.btnOpenIncidentActivity.setOnClickListener {
            val intent = Intent(context, IncidentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setViewModel() {
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
    }

    private fun callVersion() {
        lifecycleScope.launch(Dispatchers.Main){
            try {
                versionRequestItem.Version_Name = "1"
                versionRequestItem.Version_Number = "1.0"
                versionRequestItem.Login = "GMM3671"
                myViewModel.getComments(versionRequestItem)
                myViewModel.comments.observe(viewLifecycleOwner) {
                    appVersion = it.status
                    binding.textVersionFragment.text = appVersion
                }
            }catch (e:Exception){
                Timber.d(e.message)
            }

        }
    }
}