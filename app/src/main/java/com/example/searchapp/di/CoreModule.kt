/*
 * (c) Binate Station Private Limited. All rights reserved.
 */

package com.example.searchapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class CoreModule(val application: Application) {

    @Reusable
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Reusable
    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

}
