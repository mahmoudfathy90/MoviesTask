package com.example.hatlytask.movieScreen.presentation.myApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.hatlytask.movieScreen.presentation.di.component.ApplicationComponent
import com.example.hatlytask.movieScreen.presentation.di.component.DaggerApplicationComponent
import com.example.hatlytask.movieScreen.presentation.di.component.DaggerNetworkComponent
import com.example.hatlytask.movieScreen.presentation.di.component.NetworkComponent
import com.example.hatlytask.movieScreen.util.Constants


class MyApplication : Application() {

    private lateinit var appComponent: ApplicationComponent
    lateinit var networkComponent: NetworkComponent


    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationContext(this)
            .builder()

        networkComponent = DaggerNetworkComponent.builder()
            .application(this)
            .baseUrl(Constants.BASE_URL)
            .builder()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
