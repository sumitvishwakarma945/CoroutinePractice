package com.example.coroutinepractice.responses

import com.google.gson.annotations.SerializedName

class Incident {
    @SerializedName("lead_id")
    var lead_id:String? = null

    @SerializedName("asset_id")
    var asset_id:String? = null

    @SerializedName("asset_name")
    var asset_name:String? = null

    fun toIncident(incidentResponse: IncidentResponse?):ArrayList<Incident>{
        val incidents:ArrayList<Incident> = ArrayList()
        if (incidentResponse != null) {
            for (i in 0 until incidentResponse.items[0].rows.size){
                val incident = Incident()
                incident.lead_id = incidentResponse.items[0].rows[i][0]
                incident.asset_id = incidentResponse.items[0].rows[i][1]
                incident.asset_name = incidentResponse.items[0].rows[i][2]
                incidents.add(incident)
            }
        }
        return incidents
    }
}
