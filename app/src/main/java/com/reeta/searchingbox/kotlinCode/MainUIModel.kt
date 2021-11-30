package com.reeta.searchingbox.kotlinCode

import com.reeta.searchingbox.response.ResponseDTO

sealed class MainUIModel {
    data class onSuccess(val responseDTO: ResponseDTO):MainUIModel()
    data class onFailure(val error:String):MainUIModel()
}