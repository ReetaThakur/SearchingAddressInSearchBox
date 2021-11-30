package com.reeta.searchingbox.kotlinCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reeta.searchingbox.repository.Repository
import com.reeta.searchingbox.response.ResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel():ViewModel() {

    private val repo=Repository()
    private val mutableLiveData=MutableLiveData<MainUIModel>()
    val liveData:LiveData<MainUIModel> = mutableLiveData

    fun callApi(){
        CoroutineScope(Dispatchers.IO).launch {
            val response:ResponseDTO= repo.getAddress()
            if (response.data.addressList!=null){
                mutableLiveData.postValue(MainUIModel.onSuccess(response))
            }else{
                mutableLiveData.postValue(MainUIModel.onFailure("Error"))
            }
        }
    }

}