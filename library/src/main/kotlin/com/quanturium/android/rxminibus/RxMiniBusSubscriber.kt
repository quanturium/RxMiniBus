package com.quanturium.android.rxminibus

interface RxMiniBusSubscriber {

    fun <E> subscribeOnEvent(eventClass: Class<E>, action: (E) -> Unit)

    fun <E> subscribeOnEventMainThread(eventClass: Class<E>, action: (E) -> Unit)

    fun unsubscribe()

}