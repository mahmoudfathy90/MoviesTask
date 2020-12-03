package com.example.hatlytask.movieScreen.presentation.di.component



import com.example.hatlytask.movieScreen.presentation.BaseBottomSheetFragmentWithInjector
import com.example.hatlytask.movieScreen.presentation.BaseFragmentWithInjector
import com.example.hatlytask.movieScreen.presentation.di.module.ApplicationModule
import com.example.hatlytask.movieScreen.presentation.di.module.ListModule
import com.example.hatlytask.movieScreen.presentation.di.module.NetworkModule
import com.example.hatlytask.movieScreen.presentation.di.module.ViewModelModule
import com.example.hatlytask.movieScreen.presentation.myApp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class
        , ListModule::class]
)
interface NetworkComponent {


  fun inject(fragment: BaseFragmentWithInjector)
  fun inject(activity: BaseBottomSheetFragmentWithInjector)

    @Component.Builder
    interface NetworkBuilder {
        fun builder(): NetworkComponent
        @BindsInstance
        fun application(app: MyApplication): NetworkBuilder
        @BindsInstance
        fun baseUrl(baseUrl: String): NetworkBuilder

    }
}