package com.example.coroutinepractice.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.base.BaseFragment
import com.example.coroutinepractice.databinding.IncidentFragmentBinding
import com.example.coroutinepractice.responses.Incident
import com.example.coroutinepractice.responses.Item
import com.example.coroutinepractice.viewModels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncidentFragment:BaseFragment() {
    private lateinit var binding: IncidentFragmentBinding
    private lateinit var mAdapter: IncidentAdapter
    private lateinit var recyclerView: RecyclerView
    private var myViewModel = MyViewModel()
    private lateinit var items:List<Item>
    private lateinit var incidents:ArrayList<Incident>
//    private lateinit var items:List<Leads>

//    private lateinit var rows:List<List<String>>

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
        getIncidentsFromApi()
        getIncidents(items)
//        getRowItems()
        setAdapter(items)
    }

    private fun setViewModel() {
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
    }

    private fun getIncidentsFromApi() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                myViewModel.getIncidents()
                myViewModel.incidents.observe(viewLifecycleOwner){
                    items = it.items
                }

            }catch (e:Exception){
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getIncidents(items: List<Item>) {
        for (i in items[0].rows.indices){
            incidents.addAll(items[0].rows)
        }
    }

//    private fun getRowItems() {
//        items = listOf(
//            Leads("1","First","Asset1"),
//            Leads("2","First","Asset2"),
//            Leads("3","First","Asset3"),
//            Leads("4","First","Asset4"),
//            Leads("5","First","Asset5"),
//            Leads("6","First","Asset6")
//        )
//    }

    private fun setAdapter(items: List<Item>) {
        binding.recyclerViewIncidents.layoutManager = LinearLayoutManager(context)
        mAdapter = IncidentAdapter(items)
        recyclerView = binding.recyclerViewIncidents
        recyclerView.adapter = mAdapter
    }


}