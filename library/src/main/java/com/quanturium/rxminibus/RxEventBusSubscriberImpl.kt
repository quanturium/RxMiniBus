package com.quanturium.rxminibus

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class RxEventBusSubscriberImpl(private val eventBus: RxEventBus) : RxEventBusSubscriber {

    private val subscriptions = CompositeDisposable()

    override fun <E> subscribeOnEvent(eventClass: Class<E>, action: Consumer<E>) {
        subscriptions.add(
                eventBus.onEvent(eventClass, action)
        )
    }

    override fun <E> subscribeOnEventMainThread(eventClass: Class<E>, action: Consumer<E>) {
        subscriptions.add(
                eventBus.onEventMainThread(eventClass, action)
        )
    }

    override fun unsubscribe() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }
}