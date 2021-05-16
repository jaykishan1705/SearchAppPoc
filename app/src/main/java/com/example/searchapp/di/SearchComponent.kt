/*
 * (c) Binate Station Private Limited. All rights reserved.
 */

package com.example.searchapp.di

import com.example.searchapp.MainActivity
import com.example.searchapp.ui.search.SearchFragment
import dagger.Component

@SearchScopes
@Component(
    modules = [SearchModule::class, SearchViewModelModule::class],
    dependencies = [CoreComponent::class]
)
interface SearchComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
}
