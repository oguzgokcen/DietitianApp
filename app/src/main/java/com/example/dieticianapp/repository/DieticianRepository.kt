package com.example.dieticianapp.repository

import com.example.dieticianapp.data.remote.ApiService
import com.example.dieticianapp.data.remote.CallBack
import com.example.dieticianapp.di.IoDispatcher
import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Patient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DieticianRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    fun getPatients(): Flow<BaseResponse<List<Patient>>> = callbackFlow {
        apiService.getPatients().enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)
}