package com.quanturium.android.rxminibus

import io.reactivex.disposables.Disposable

interface RxMiniBus {

    fun post(event: Any)

    fun <E> onEvent(eventClass: Class<E>, action: (E) -> Unit): Disposable

    fun <E> onEventMainThread(eventClass: Class<E>, action: (E) -> Unit): Disposable
}