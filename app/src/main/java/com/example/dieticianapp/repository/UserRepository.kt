package com.example.dieticianapp.repository

import com.example.dieticianapp.data.remote.ApiService
import com.example.dieticianapp.data.remote.CallBack
import com.example.dieticianapp.di.IoDispatcher
import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Login
import com.example.dieticianapp.model.Register
import com.example.dieticianapp.model.TokenResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun setRegister(register: Register): Flow<BaseResponse<TokenResponse>> = callbackFlow {
        apiService.setRegister(register).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)

    fun setLogin(login: Login): Flow<BaseResponse<TokenResponse>> = callbackFlow {
        apiService.setLogin(login).enqueue(CallBack(this.channel))
        awaitClose { close() }
    }.flowOn(ioDispatcher)
}