package com.comercio.comercioapk.di

import com.comercio.comercioapk.base.MyCallAdapterFactory
import com.comercio.comercioapk.util.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Singleton
    @Provides
    @Named("apiLocal")
    internal fun provideRetrofitLocal(
        okhttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): Retrofit {
        val baseUrl = "http://10.0.2.16:8080"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(gson)
            .build()
    }

    @Singleton
    @Provides
    @Named("apiRemote")
    internal fun provideRetrofitRemote(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:80/comercioApi/api/")
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(MyCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providePrivateOkHttpClient(): OkHttpClient {
        val interceptor2 = HttpLoggingInterceptor()
        interceptor2.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor2)
        // client.sslSocketFactory(getSSLConfig(context)!!.socketFactory, getTrustManager()!!)
        client.connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.MINUTES)
        client.readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.MINUTES)
        client.writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.MINUTES)
        return client.build()
    }




}