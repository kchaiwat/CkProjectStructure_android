package com.example.ckprojectstructure_android.data.api

import io.reactivex.Observable
import io.reactivex.functions.Function

import java.util.concurrent.TimeUnit

// https://stackoverflow.com/questions/22066481/rxjava-can-i-use-retry-but-with-delay

class RetryWithDelay(private val maxRetries: Int, private val retryDelayMillis: Int) :
    Function<Observable<out Throwable>, Observable<*>> {
    private var retryCount: Int = 0

    init {
        this.retryCount = 0
    }

    override fun apply(attempts: Observable<out Throwable>): Observable<*> {
        return attempts
            .flatMap { throwable ->
                if (++retryCount < maxRetries) {
                    // When this Observable calls onNext, the original
                    // Observable will be retried (i.e. re-subscribed).
                    Observable.timer(
                        retryDelayMillis.toLong(),
                        TimeUnit.MILLISECONDS
                    )
                } else Observable.error<Any>(throwable)

                // Max retries hit. Just pass the error along.
            }
    }
}