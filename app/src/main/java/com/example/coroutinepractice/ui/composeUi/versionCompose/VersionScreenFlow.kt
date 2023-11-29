package com.example.coroutinepractice.ui.composeUi.versionCompose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutinepractice.R
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.theme.PracticeTheme
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.viewModels.MyViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun VersionScreenFlow() {
    var version by remember {
        mutableStateOf("")
    }
    val versionRequestItem = VersionRequestItem()
    val myViewModel = MyViewModel()
    val coroutineScope = rememberCoroutineScope()

    val versionResponse = myViewModel.commentsFLow.collectAsState()

//    LaunchedEffect(Unit){
//        versionRequestItem.Version_Name = "1"
//        versionRequestItem.Version_Number = "1.0"
//        versionRequestItem.Login = "GMM3671"
//        myViewModel.getComments(versionRequestItem)
//    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(), color = colorResource(id = R.color.black)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = version, color = colorResource(id = R.color.dark_yellow))
            Button(
                onClick = {
                    myViewModel.getCommentsFlow(versionRequestItem)
//                    version = versionResponse.value.data?.status.toString()
                       version = callVersionApiFLow(versionRequestItem, myViewModel, coroutineScope, versionResponse)
                    Log.d("VersionValue", version)
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_yellow))
            ) {
                Text(text = "Click on me Version FLow", color = colorResource(id = R.color.black))
            }
        }
    }

}

fun callVersionApiFLow(
    versionRequestItem: VersionRequestItem,
    myViewModel: MyViewModel,
    coroutineScope: CoroutineScope,
    versionResponse: State<Resource<Comments>>,
): String {
    var status = ""
    versionRequestItem.Version_Name = "1"
    versionRequestItem.Version_Number = "1.0"
    versionRequestItem.Login = "GMM3671"
    myViewModel.getCommentsFlow(versionRequestItem)
    status = when(versionResponse.value){
        is Resource.Success -> {
            versionResponse.value.data?.status.toString()
        }else ->{
            "No data found"
        }
    }
    return status
}


@Preview
@Composable
fun VersionScreenFlowPreview() {
    PracticeTheme {
        VersionScreenFlow()
    }
}