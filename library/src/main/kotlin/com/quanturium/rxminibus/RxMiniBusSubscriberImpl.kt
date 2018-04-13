package com.quanturium.rxminibus

import io.reactivex.disposables.CompositeDisposable

class RxMiniBusSubscriberImpl(private val miniBus: RxMiniBus) : RxMiniBusSubscriber {

    private val subscriptions = CompositeDisposable()

    override fun <E> subscribeOnEvent(eventClass: Class<E>, action: (E) -> Unit) {
        subscriptions.add(
                miniBus.onEvent(eventClass, action)
        )
    }

    override fun <E> subscribeOnEventMainThread(eventClass: Class<E>, action: (E) -> Unit) {
        subscriptions.add(
                miniBus.onEventMainThread(eventClass, action)
        )
    }

    override fun unsubscribe() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }
}