package com.quanturium.rxminibus

import io.reactivex.functions.Consumer

interface RxEventBusSubscriber {

    fun <E> subscribeOnEvent(eventClass: Class<E>, action: Consumer<E>)

    fun <E> subscribeOnEventMainThread(eventClass: Class<E>, action: Consumer<E>)

    fun unsubscribe()

}