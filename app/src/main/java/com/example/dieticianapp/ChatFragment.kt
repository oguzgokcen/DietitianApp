package com.example.dieticianapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.adapters.MessageAdapter
import com.example.loginpage.model.Message

class ChatFragment : Fragment() {
    private lateinit var messageRView:RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton:ImageView
    private lateinit var messageAdapter:MessageAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_chat, container, false)

        messageRView = view.findViewById(R.id.chatRView)
        messageBox = view.findViewById(R.id.chatEText)
        sendButton = view.findViewById(R.id.chatSendBtn)
        val messageList = arrayListOf(
            Message("aaa","111"),
            Message("bbb","222")
        )
        messageAdapter = MessageAdapter(this.requireContext(),messageList,"111")
        messageRView.layoutManager = LinearLayoutManager(this.requireContext())
        messageRView.adapter = messageAdapter
        sendButton.setOnClickListener{
            val message = messageBox.text.toString()
            if(!message.isNullOrEmpty())messageList.add(Message(message,"111"))
            messageBox.setText("")
            messageAdapter.notifyDataSetChanged()
        }
        return view
    }
}