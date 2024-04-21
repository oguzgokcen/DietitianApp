package com.example.dieticianapp.data.remote

import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.channels.SendChannel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallBack<T>(private val responseChannel: SendChannel<BaseResponse<T>>): Callback<T> {


    override fun onResponse(call: Call<T>, response: Response<T>) {
        if(response.isSuccessful){
            val body = response.body()
            if(body != null) {
                responseChannel.trySend(BaseResponse.Success(body))
            }else {
                responseChannel.trySend(BaseResponse.Error(ErrorResponse(0, "Body is Null")))
            }
        } else {
            val errorBody = response.errorBody()
            val errorResponse = Gson().fromJson(errorBody?.string(), ErrorResponse::class.java)
            if (errorResponse == null) {
                responseChannel.trySend(BaseResponse.Error(ErrorResponse(0, "Bilinmeyen bir hata oluştu.")))
                return
            }
            responseChannel.trySend(BaseResponse.Error(errorResponse))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
       responseChannel.trySend(BaseResponse.Error(ErrorResponse(message = t.localizedMessage)))
    }


}