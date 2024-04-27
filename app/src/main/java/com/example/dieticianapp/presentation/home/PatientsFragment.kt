package com.example.dieticianapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.dieticianapp.R
import com.example.dieticianapp.data.local.DataStoreManager
import com.example.dieticianapp.databinding.FragmentHomeBinding
import com.example.dieticianapp.databinding.FragmentPatientsBinding
import com.example.dieticianapp.domain.ViewState
import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Patient
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PatientsFragment : Fragment(R.layout.fragment_patients) {
    private val binding by this.viewBinding(FragmentPatientsBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPatients()
        initObserver()
    }

    private fun initListener() {
        TODO("Not yet implemented")
    }

    private fun initObserver() = with(viewModel){
        viewLifecycleOwner.lifecycleScope.launch {
            uiStateLogin.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { viewState ->
                    when (viewState) {
                        is ViewState.Success -> {
                            val response = viewState.result as BaseResponse.Success
                            binding.loadingView.visibility = View.GONE
                            Log.v("ViewState.Success", response.data.toString())
                            val patientList = response.data

                        }

                        is ViewState.Error -> {
                            val responseError = viewState.error
                            binding.loadingView.visibility = View.GONE
                            Log.v("ViewState.Error", responseError)
                        }

                        is ViewState.Loading -> {
                            Log.v("ViewState.Loading", "ViewState.Loading")
                        }
                    }
                }
        }
    }


}