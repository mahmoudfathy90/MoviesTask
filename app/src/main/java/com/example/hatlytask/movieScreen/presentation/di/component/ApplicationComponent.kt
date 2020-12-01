package com.example.hatlytask.movieScreen.presentation.di.component


import com.example.hatlytask.movieScreen.presentation.di.module.ApplicationModule
import com.example.hatlytask.movieScreen.presentation.myApp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun injectApp(myApp: MyApplication)

    @Component.Builder
    interface Builder {
        fun builder(): ApplicationComponent
        @BindsInstance
        fun applicationContext(appContext: MyApplication): Builder
    }
}