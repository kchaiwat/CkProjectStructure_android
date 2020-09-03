package com.example.ckprojectstructure_android.presentation

import androidx.lifecycle.ViewModel
import com.example.ckprojectstructure_android.util.delegate.SingleLiveEvent
import com.example.ckprojectstructure_android.util.extension.toLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

//    protected val result = MutableLiveData<ApiState<R>>()
//
//    // Make this as open so that mock instances can mock this method
//    open fun result(): MutableLiveData<ApiState<R>> {
//        return result
//    }

    private val _showLoading = SingleLiveEvent<Unit>()
    val showLoading = _showLoading.toLiveData()

    private val _dismissLoading = SingleLiveEvent<Unit>()
    val dismissLoading = _dismissLoading.toLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    internal fun postShowLoading() {
        _showLoading.value = Unit
    }

    internal fun postDismissLoading() {
        _dismissLoading.value = Unit
    }
}