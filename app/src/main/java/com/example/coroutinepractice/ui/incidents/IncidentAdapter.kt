package com.example.coroutinepractice.ui.incidents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinepractice.databinding.ItemIncidentBinding
import com.example.coroutinepractice.responses.Incident
import com.example.coroutinepractice.responses.Item
import kotlinx.android.synthetic.main.item_incident.view.title_asset_id
import kotlinx.android.synthetic.main.item_incident.view.title_asset_name
import kotlinx.android.synthetic.main.item_incident.view.title_lead_id

class IncidentAdapter(private val items: ArrayList<Incident>) :RecyclerView.Adapter<IncidentAdapter.ViewHolder>(){



    inner class ViewHolder(private val itemIncidentBinding: ItemIncidentBinding): RecyclerView.ViewHolder(itemIncidentBinding.root)


//    class ViewHolder(private val itemIncidentBinding: ItemIncidentBinding): RecyclerView.ViewHolder(itemIncidentBinding.root) {
////        fun bindData(data: Incident){
////            itemIncidentBinding.textLeadId.text = data.lead_id
////            itemIncidentBinding.textAssetId.text = data.asset_id
////            itemIncidentBinding.textAssetName.text = data.asset_name
//////            for (i in items.indices){
//////                itemIncidentBinding.textLeadId.text = items[i].rows[0].toString()
//////            }
////        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIncidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindData(items[position])
        val incident = items[position]
        holder.itemView.apply {
          title_lead_id.text = incident.lead_id
          title_asset_id.text = incident.asset_id
          title_asset_name.text = incident.asset_name
        }
    }


}