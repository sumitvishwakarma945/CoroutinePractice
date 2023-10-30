package com.example.coroutinepractice

class QueryUtils {
    //get List of incidents
    companion object {
        const val get_incident_list = "select id as lead_id,asset.id as asset_id,asset.lookupName as asset_name from CO.Lead where CSE.id=20517 and Lead_Status.id != 40"
    }
}