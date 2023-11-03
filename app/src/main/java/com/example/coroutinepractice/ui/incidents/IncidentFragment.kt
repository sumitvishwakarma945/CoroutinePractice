package com.example.coroutinepractice.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.R
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.IncidentFragmentBinding
import com.example.coroutinepractice.responses.Incident
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.viewModels.IncidentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class IncidentFragment:BaseFragment() {
    private lateinit var binding: IncidentFragmentBinding
    private lateinit var mAdapter: IncidentAdapter
    private lateinit var recyclerView: RecyclerView
//    private var myViewModel = MyViewModel()
    private var myViewModel = IncidentViewModel()
    private var incidentFromModelClass:ArrayList<Incident> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.incident_fragment, container, false)
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

                myViewModel.incidentsResponse.observe(viewLifecycleOwner, Observer {response ->
                    when(response){
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let {newsResponse ->
                                incidentFromModelClass = Incident().toIncident(newsResponse)
                            }
                            setAdapter(incidentFromModelClass)
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let {message ->
                                Timber.tag("ErrorIncident").e("An error occurred in incidents: $message")

                            }
                        }
                        is Resource.Loading ->{
                            showProgressBar()
                        }
                    }
                })

            }catch (e:Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
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


}