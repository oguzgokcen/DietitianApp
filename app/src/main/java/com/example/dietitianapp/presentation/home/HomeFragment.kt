package com.example.dietitianapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dietitianapp.R
import com.example.dietitianapp.data.local.DataStoreManager
import com.example.dietitianapp.databinding.FragmentHomeBinding
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),SwipeRefreshLayout.OnRefreshListener {
    private val binding by this.viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()  //by ile birkere oluşturulur ve her seferinde çağrılır.
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    val activity:MainActivity by lazy {
        requireActivity() as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.binding.swipe.setOnRefreshListener(this)
    }
    override fun onRefresh() {
        Log.v("HomeFragment", "refresh")
        activity.binding.swipe.isRefreshing = false
    }


}