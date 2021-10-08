package com.akash.posts.di

import android.app.Application
import com.akash.posts.PostsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    FragmentsBuilderModule::class,
    AppModule::class]
)
interface AppComponent: AndroidInjector<PostsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}