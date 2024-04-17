package com.example.dieticianapp.usecase.user

import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Register
import com.example.dieticianapp.model.TokenResponse
import com.example.dieticianapp.repository.UserRepository
import com.getir.patika.foodcouriers.common.domain.SingleParaMeterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SingleParaMeterUseCase<Register, Flow<BaseResponse<TokenResponse>>> {

    override fun execute(param: Register): Flow<BaseResponse<TokenResponse>> = userRepository.setRegister(param)

}