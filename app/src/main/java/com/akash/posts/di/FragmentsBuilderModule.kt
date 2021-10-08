package com.akash.posts.di

import com.akash.posts.ui.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}