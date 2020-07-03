package com.mauricio.moviles_bg2m

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/products/{id}.json")
    fun getProducts(@Path("id") id: String) : Call<Products>

}