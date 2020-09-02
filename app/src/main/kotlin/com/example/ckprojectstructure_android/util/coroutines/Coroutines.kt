package com.example.ckprojectstructure_android.util.coroutines

import kotlinx.coroutines.*

object Coroutines {

    fun main(scope: suspend () -> Unit): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            scope()
        }
    }

    fun io(scope: suspend () -> Unit): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            scope()
        }
    }

    fun default(scope: suspend () -> Unit): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            scope()
        }
    }

    @ExperimentalCoroutinesApi
    fun unconfined(scope: suspend () -> Unit): Job {
        return CoroutineScope(Dispatchers.Unconfined).launch {
            scope()
        }
    }
}