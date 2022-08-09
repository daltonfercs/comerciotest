package com.comercio.comercioapk.di

import android.app.Application
import com.comercio.comercioapk.ComercioApp
import com.comercio.comercioapk.di.activity.ActivityBuildersModule
import com.comercio.comercioapk.di.activity.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<ComercioApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}