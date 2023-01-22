package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        @Remote remoteDataSource: DataSource
    ): Repository {
        return DefaultRepository(
            RemoteDataSource()
        )
    }

    @Singleton
    @Remote
    @Provides
    fun provideRemoteDataSource(
    ): DataSource {
        return RemoteDataSource()
    }


    // Annotation
    @Qualifier
    annotation class Remote
}