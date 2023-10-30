package com.example.coroutinepractice.ui.incidents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.databinding.ItemIncidentBinding
import com.example.coroutinepractice.responses.Incident
import com.example.coroutinepractice.responses.Item
import com.example.coroutinepractice.responses.Leads

class IncidentAdapter(private val items: List<Item>) :RecyclerView.Adapter<IncidentAdapter.ViewHolder>(){
    lateinit var arrayList: List<Incident>
    val incidentItems = this.items

    class ViewHolder(private val itemIncidentBinding: ItemIncidentBinding): RecyclerView.ViewHolder(itemIncidentBinding.root) {
        fun bindData(data: Item){
            itemIncidentBinding.textLeadId.text = data.rows.toString()
            itemIncidentBinding.textAssetId.text = data.rows.toString()
            itemIncidentBinding.textAssetName.text = data.rows.toString()
//            for (i in items.indices){
//                itemIncidentBinding.textLeadId.text = items[i].rows[0].toString()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIncidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }


}