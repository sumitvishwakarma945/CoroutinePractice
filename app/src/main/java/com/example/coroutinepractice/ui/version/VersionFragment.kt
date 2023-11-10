package com.example.coroutinepractice.ui.version

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coroutinepractice.BR
import com.example.coroutinepractice.R
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.VersionFragmentBinding
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.ui.incidents.IncidentActivity
import com.example.coroutinepractice.viewModels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class VersionFragment:BaseFragment() {
    @JvmField
    @Inject var myViewModel = MyViewModel()
    private lateinit var binding: VersionFragmentBinding
    private var versionRequestItem = VersionRequestItem()
//    private var myViewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = VersionFragmentBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.version_fragment, container, false)
        //dataBinding using ObservableFields (dataBinding step1)
        binding.myViewModel = myViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        //Set the bindingVariable with already generated BR.java file (dataBinding step2)
        val bindingVariable = BR.myViewModel
        //Set the variable with ViewModel (dataBinding step 3)
        binding.setVariable(bindingVariable, myViewModel)
        //Need to attach lifecycleOwner because of which changes happen in xml (dataBinding Step 4)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        binding.btnHitVersionApi.setOnClickListener {
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
                myViewModel.comments.observe(viewLifecycleOwner, Observer {
                    val appVersion = it.status
                    myViewModel.version.set(appVersion)
                })
            }catch (e:Exception){
                Timber.d(e.message)
            }

        }
    }
}