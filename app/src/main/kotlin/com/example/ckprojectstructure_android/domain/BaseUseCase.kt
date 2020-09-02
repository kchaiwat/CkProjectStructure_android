package com.example.ckprojectstructure_android.domain

import kotlinx.coroutines.Job

abstract class BaseUseCase {

    protected var job: Job? = null

    fun clear() {
        job?.cancel()
    }
}