package com.example.coroutinepractice.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.IncidentFragmentBinding
import com.example.coroutinepractice.responses.Item
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.viewModels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class IncidentFragment:BaseFragment() {
    private lateinit var binding: IncidentFragmentBinding
    private lateinit var mAdapter: IncidentAdapter
    private lateinit var recyclerView: RecyclerView
    private var items = ArrayList<Item>()
    private var myViewModel = MyViewModel()
    private val TAG = "ITEMS"
    private var incidentString = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = IncidentFragmentBinding.inflate(inflater, container, false)

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
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
    }

    private fun getIncidentsFromApi() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                myViewModel.getIncidents()
                myViewModel.incidents.observe(viewLifecycleOwner, Observer {response ->
                    when(response){
                       is Resource.Success -> {
                            hideProgressBar()
                           response.data?.let {newsResponse ->
                               items = newsResponse.items as ArrayList<Item>
                               getIncidents(items)
                           }
                       }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let {message ->
                                Timber.tag("ErrorIncident").e("An error occured in incidents: $message")

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

    private fun getIncidents(response: ArrayList<Item>) {
        for (i in response[0].rows.indices){
            incidentString.add(response[0].rows[i] as ArrayList<String>)
        }
        setAdapter(incidentString)
        Timber.d("IncidentString", incidentString)
    }


    private fun setAdapter(items: ArrayList<ArrayList<String>>) {
        mAdapter = IncidentAdapter(items)
        recyclerView.adapter = mAdapter
    }


}