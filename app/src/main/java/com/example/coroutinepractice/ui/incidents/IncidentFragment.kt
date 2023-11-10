package com.example.coroutinepractice.ui.incidents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.R
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.IncidentFragmentBinding
import com.example.coroutinepractice.responses.Incident
import com.example.coroutinepractice.utils.NotificationHelper
import com.example.coroutinepractice.utils.NotificationReceiver
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.viewModels.IncidentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IncidentFragment:BaseFragment() {
    @Inject lateinit var binding: IncidentFragmentBinding
    @Inject lateinit var mAdapter: IncidentAdapter
    @JvmField
    @Inject var myViewModel = IncidentViewModel()
    private lateinit var recyclerView: RecyclerView
//    private var myViewModel = MyViewModel()
    private var incidentFromModelClass:ArrayList<Incident> = ArrayList()
    private val mReceiver:BroadcastReceiver = NotificationReceiver()
    private val intentFilter = IntentFilter("com.example.coroutinepractice")
    private val listenToBroadcastsFromOtherApps = false
    private val receiverFlags = if (listenToBroadcastsFromOtherApps) {
        ContextCompat.RECEIVER_EXPORTED
    } else {
        ContextCompat.RECEIVER_NOT_EXPORTED
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.incident_fragment, container, false)
        ContextCompat.registerReceiver(requireContext(),mReceiver,intentFilter,receiverFlags)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        binding.recyclerViewIncidents.layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerViewIncidents
        getIncidentsFromApi()

    }

    private fun setViewModel() {
        myViewModel = ViewModelProvider(this)[IncidentViewModel::class.java]
    }

    private fun getIncidentsFromApi() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                myViewModel.getIncidents()

                myViewModel.incidentsResponse.observe(viewLifecycleOwner) { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { newsResponse ->
                                incidentFromModelClass = Incident().toIncident(newsResponse)
                            }
                            callNotification(requireContext())
                            setAdapter(incidentFromModelClass)
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Timber.tag("ErrorIncident")
                                    .e("An error occurred in incidents: $message")

                            }
                        }

                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }

            }catch (e:Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callNotification(context: Context) {
        val notificationHelper = NotificationHelper(context)
        notificationHelper.showNotification()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun setAdapter(items: ArrayList<Incident>) {
        mAdapter = IncidentAdapter(items)
        recyclerView.adapter = mAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(mReceiver)
    }


}