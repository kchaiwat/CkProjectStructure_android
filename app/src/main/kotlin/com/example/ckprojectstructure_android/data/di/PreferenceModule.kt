package com.example.ckprojectstructure_android.data.di

import android.content.Context
import com.example.ckprojectstructure_android.data.preference.TokenPreference
import com.example.ckprojectstructure_android.data.preference.UserPreference
import org.koin.dsl.module

val preferenceModule = module {
    single { provideTokenPreference(get()) }
    single { provideUserPreference(get()) }
}

fun provideTokenPreference(context: Context): TokenPreference {
    return TokenPreference(context)
}

fun provideUserPreference(context: Context): UserPreference {
    return UserPreference(context)
}