package com.reeta.searchingbox.repository

import com.reeta.searchingbox.response.Network
import com.reeta.searchingbox.response.Network.city
import com.reeta.searchingbox.response.Network.queryString
import com.reeta.searchingbox.response.ResponseDTO

class Repository {

        suspend fun getAddress(): ResponseDTO {
        return Network.getApiService().getInstance(queryString,city)
    }

}