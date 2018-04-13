package com.quanturium.rxminibus

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class RxMiniBusImpl(private val observingMainThreadScheduler: Scheduler) : RxMiniBus {
    private val bus = PublishSubject.create<Any>().toSerialized()

    override fun post(event: Any) {
        bus.onNext(event)
    }

    /**
     *
     */
    override fun <E> onEvent(eventClass: Class<E>, action: (E) -> Unit): Disposable {
        return bus
                .ofType<E>(eventClass)
                .subscribe(action)
    }

    override fun <E> onEventMainThread(eventClass: Class<E>, action: (E) -> Unit): Disposable {
        return bus
                .ofType<E>(eventClass)
                .observeOn(observingMainThreadScheduler)
                .subscribe(action)
    }
}
