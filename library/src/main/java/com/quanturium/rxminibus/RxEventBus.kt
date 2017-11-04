package com.quanturium.rxminibus

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface RxEventBus {

    fun post(event: Any)

    fun <T> onEvent(eventClass: Class<T>, action: Consumer<T>): Disposable

    fun <T> onEventMainThread(eventClass: Class<T>, action: Consumer<T>): Disposable
}