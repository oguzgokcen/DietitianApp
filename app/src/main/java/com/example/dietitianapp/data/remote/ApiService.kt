package com.example.dietitianapp.data.remote
import com.example.dietitianapp.model.Login
import com.example.dietitianapp.model.Patient
import com.example.dietitianapp.model.Register
import com.example.dietitianapp.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    fun setLogin(@Body login: Login): Call<TokenResponse>

    @POST("register/dietician")
    fun setRegister(@Body register: Register): Call<TokenResponse>

    @GET("dieticians/patients")
    fun getPatients(@Header("Authorization") token:String):Call<List<Patient>>

    @PUT("patients/{id}")
    fun savePatient(@Header("Authorization") token:String,@Path("id") id:String):Call<Void>

}