package com.example.ckprojectstructure_android.util.extension

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(composite: CompositeDisposable): Boolean = composite.add(this)

fun <E> ArrayList<E>.toObservable(): Observable<ArrayList<E>> {
    return Observable.just(this)
}

fun <K, V> LinkedHashMap<K, V>.toObservable(): Observable<LinkedHashMap<K, V>> {
    return Observable.just(this)
}

fun <K, V> HashMap<K, V>.toObservable(): Observable<HashMap<K, V>> {
    return Observable.just(this)
}

fun <E> LinkedHashSet<E>.toObservable(): Observable<LinkedHashSet<E>> {
    return Observable.just(this)
}

fun <E> HashSet<E>.toObservable(): Observable<HashSet<E>> {
    return Observable.just(this)
}