package com.akash.posts

import com.akash.posts.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PostsApplication : DaggerApplication() {

    /**
     * Method to initialize Dagger dependency injection throughout the lifecycle of the application
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}