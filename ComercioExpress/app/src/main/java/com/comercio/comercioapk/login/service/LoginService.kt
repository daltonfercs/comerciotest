package com.comercio.comercioapk.login.service

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginService {

    /*
        @GET("ListaProductoApi.php")
        suspend fun initLogin():LoginResponse*/

    @FormUrlEncoded
    @POST("LoginApi.php")
    suspend fun initLogin(
        @Field("usuario") usuario: String,
        @Field("clave") clave: String
    ): LoginResponse

}