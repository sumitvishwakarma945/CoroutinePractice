package com.example.coroutinepractice.ui.incidents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.R
import com.example.coroutinepractice.databinding.ItemIncidentBinding
import com.example.coroutinepractice.responses.Incident
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class IncidentAdapter @Inject constructor(val items:ArrayList<Incident>) :RecyclerView.Adapter<IncidentAdapter.ViewHolder>(){

    inner class ViewHolder(val itemIncidentBinding: ItemIncidentBinding): RecyclerView.ViewHolder(itemIncidentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemIncidentBinding>(LayoutInflater.from(parent.context), R.layout.item_incident, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incident = items[position]
        holder.itemIncidentBinding.incident = incident

        //Using executePendingBindings to avoid little flickering of recyclerView when configuration of screen changes.
        holder.itemIncidentBinding.executePendingBindings()
    }
}