package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.factory.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(viewModelFactory: ViewModelFactory)

}