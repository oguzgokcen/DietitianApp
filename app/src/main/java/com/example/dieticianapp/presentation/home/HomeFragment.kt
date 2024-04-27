package com.example.dieticianapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dieticianapp.R
import com.example.dieticianapp.data.local.DataStoreManager
import com.example.dieticianapp.databinding.FragmentHomeBinding
import com.example.dieticianapp.databinding.FragmentLoginBinding
import com.example.dieticianapp.presentation.account.AccountViewModel
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by this.viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()  //by ile birkere oluşturulur ve her seferinde çağrılır.
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}