package com.comercio.comercioapk.di.activity

import android.app.Application
import com.comercio.comercioapk.base.ResponseHandler
import com.comercio.comercioapk.db.ComercioDB
import com.comercio.comercioapk.login.service.LoginService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object ActivityModule {


    @Provides
    @Singleton
    internal fun provideLoginService(@Named("apiRemote") retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }


    @Provides
    @Singleton
    internal fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Provides
    @Singleton
    internal fun provideComercioDB(app: Application) = ComercioDB.getInstance(app)


}