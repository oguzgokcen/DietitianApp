package com.example.dieticianapp.presentation.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.dieticianapp.R
import com.example.dieticianapp.databinding.FragmentLoginBinding
import com.example.dieticianapp.model.BaseResponse
import com.example.dieticianapp.model.Login
import com.example.dieticianapp.domain.ViewState
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by this.viewBinding(FragmentLoginBinding::bind)
    private val viewModel: AccountViewModel by viewModels()  //by ile birkere oluşturulur ve her seferinde çağrılır.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() = with(binding) {
        btLogin.setOnClickListener {
                viewModel.setLogin(Login(binding.tvTckn.text.toString(), binding.tvPassword.text.toString()))
        }
        btRegister.setOnClickListener {

        }
    }

    private fun initObserver() = with(viewModel){
        viewLifecycleOwner.lifecycleScope.launch {
            uiStateLogin.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { viewState ->
                    when (viewState) {
                        is ViewState.Success -> {
                            val response = viewState.result as BaseResponse.Success
                            Log.v("ViewState.Success", response.data.toString())
                        }

                        is ViewState.Error -> {
                            val responseError = viewState.error
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