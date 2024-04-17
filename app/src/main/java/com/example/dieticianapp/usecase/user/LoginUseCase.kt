package com.example.dieticianapp.usecase.user

import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Login
import com.example.dieticianapp.model.TokenResponse
import com.example.dieticianapp.repository.UserRepository
import com.getir.patika.foodcouriers.common.domain.SingleParaMeterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SingleParaMeterUseCase<Login, Flow<BaseResponse<TokenResponse>>> {

    override fun execute(param: Login): Flow<BaseResponse<TokenResponse>> = userRepository.setLogin(param)

}