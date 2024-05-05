package com.example.dietitianapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietitianapp.R
import com.example.dietitianapp.databinding.FragmentChatBinding
import com.example.dietitianapp.presentation.adapter.MessageAdapter
import com.example.loginpage.model.Message
import com.google.firebase.Timestamp
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.UUID
@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {
    private val binding by this.viewBinding(FragmentChatBinding::bind)
    private val viewModel: ChatViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messageList = arrayListOf(
            Message(UUID.randomUUID().toString(),"Merhaba. Yeni Diet programınızı gönderdim",false, Timestamp(Date())
            ),
            Message(UUID.randomUUID().toString(),"Tamamdır, Teşekkür Ederim",true, Timestamp(Date()))
        )
        val messageAdapter = MessageAdapter(this.requireContext(),messageList)
        initObserver()
        val getMessages = viewModel.getMessages()
        if (!getMessages){
            Toast.makeText(requireContext(),"Mesajlar Getirilemedi",Toast.LENGTH_SHORT).show()
        }
        with(binding){
            rvChat.layoutManager = LinearLayoutManager(requireContext())
            rvChat.adapter = messageAdapter
            btnSend.setOnClickListener{
                val message = etChat.text.toString()
                if(!message.isNullOrEmpty()){
                    val result= viewModel.addMessage(message)
                    if (!result){
                        Toast.makeText(requireContext(),"Mesaj Gönderilemedi",Toast.LENGTH_SHORT).show()
                    }
                }
                etChat.setText("")
                messageAdapter.notifyDataSetChanged()
            }
        }
    }

    fun initObserver() {
        val messages = Observer<List<Message>> {
            binding.rvChat.adapter = MessageAdapter(requireContext(), it.toList())
        }
        viewModel.messageList.observe(viewLifecycleOwner, messages)
    }
}