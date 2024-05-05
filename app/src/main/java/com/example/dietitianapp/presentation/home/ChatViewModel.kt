package com.example.dietitianapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietitianapp.domain.ViewState
import com.example.dietitianapp.model.BaseResponse
import com.example.dietitianapp.model.Patient
import com.example.loginpage.model.Message
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor():ViewModel() {
    val messageList = MutableLiveData<List<Message>>()
    private val db: FirebaseFirestore = Firebase.firestore

    private val _uiStateListChat: MutableStateFlow<ViewState<BaseResponse<List<Patient>>>> =
        MutableStateFlow(ViewState.Loading)
    val uiStateListChat = _uiStateListChat.asStateFlow()
    fun getMessages():Boolean{
        var isSuccess = false
        //val response:BaseResponse<List<Message>> DO later
        db.collection("messages")
            .addSnapshotListener{result,error ->
                val documents = result?.documents ?: run {
                    isSuccess = false
                    return@addSnapshotListener
                }
                val result = documents.map{document->
                    document.data?.let {
                        Message(
                            it["id"].toString(),
                            it["text"].toString(),
                            it["received"] as Boolean,
                            it["timestamp"] as Timestamp
                        )
                    }?:run{
                        isSuccess = false
                        return@addSnapshotListener
                    }
                }
                messageList.postValue(result)
            }
        return isSuccess
    }
    fun addMessage(messageText: String):Boolean{
        val message = Message(UUID.randomUUID().toString(),messageText,true, Timestamp(Date()))
        var isSuccess = false
        db.collection("messages")
            .add(message)
            .addOnSuccessListener {
                getMessages()
                isSuccess = true
            }
            .addOnFailureListener {
                isSuccess = false
            }
        return isSuccess
    }

}