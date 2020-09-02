package com.example.ckprojectstructure_android.data.di

import com.example.ckprojectstructure_android.data.repository.ApiRepository
import com.example.ckprojectstructure_android.data.repository.ApiRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<ApiRepository> { ApiRepositoryImpl(get(), get(), get()) }
}