package com.example.dieticianapp.data.remote
import com.example.dieticianapp.model.Login
import com.example.dieticianapp.model.Patient
import com.example.dieticianapp.model.Register
import com.example.dieticianapp.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun setLogin(@Body login: Login): Call<TokenResponse>

    @POST("register/dietician")
    fun setRegister(@Body register: Register): Call<TokenResponse>

    @GET("dieticians/patients")
    fun getPatients():Call<List<Patient>>

}