package com.example.dieticianapp.usecase.home

import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Patient
import com.example.dieticianapp.repository.DieticianRepository
import com.getir.patika.foodcouriers.common.domain.NoParaMeterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListPatientsUseCase @Inject constructor(
    private val dietitianRepository: DieticianRepository
): NoParaMeterUseCase<Flow<BaseResponse<List<Patient>>>> {
    override fun execute():Flow<BaseResponse<List<Patient>>> = dietitianRepository.getPatients()
}