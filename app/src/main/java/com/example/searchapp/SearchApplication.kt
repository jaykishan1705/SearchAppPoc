/*
 * (c) Binate Station Private Limited. All rights reserved.
 */

package com.example.searchapp

import android.app.Application
import com.example.searchapp.di.*

class SearchApplication : Application(), SearchComponentProvider, CoreComponentProvider {

    override fun getCoreComponent(): CoreComponent {
        return DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
    }


    override fun onCreate() {
        super.onCreate()
    }

    override fun getSearchComponent(): SearchComponent {
        return DaggerSearchComponent.builder()
            .coreComponent(getCoreComponent())
            .build()
    }

}
