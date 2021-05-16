/*
 * (c) Binate Station Private Limited. All rights reserved.
 */

package com.example.searchapp.di

import android.accounts.AccountManager
import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideApplication(): Application

    fun provideApplicationContext(): Context

}
