package com.example.dieticianapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dieticianapp.domain.ViewState
import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Patient
import com.example.dieticianapp.usecase.home.ListPatientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listPatientsUseCase: ListPatientsUseCase
) : ViewModel() {
    private val _uiStateLogin: MutableStateFlow<ViewState<BaseResponse<List<Patient>>>> =
        MutableStateFlow(ViewState.Loading)
    val uiStateLogin = _uiStateLogin.asStateFlow()

    fun getPatients(){
        listPatientsUseCase.execute().map {
            when(val responseData: BaseResponse<List<Patient>> = it) {
                is BaseResponse.Success -> {
                    ViewState.Success(responseData)
                }
                is BaseResponse.Error -> {
                    ViewState.Error(responseData.error.message.toString())
                }
            }
        }.onEach {data ->
            _uiStateLogin.emit(data)
        }.launchIn(viewModelScope)
    }
}