package com.example.loginpage.model

import com.google.firebase.Timestamp

data class Message(
    val id:String,
    val message:String?, val recieved:Boolean?,
    val timestamp: Timestamp
)
