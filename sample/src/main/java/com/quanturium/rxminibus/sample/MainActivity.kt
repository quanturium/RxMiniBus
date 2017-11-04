package com.quanturium.rxminibus.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.quanturium.rxminibus.RxEventBus
import com.quanturium.rxminibus.RxEventBusImpl
import com.quanturium.rxminibus.RxEventBusSubscriber
import com.quanturium.rxminibus.RxEventBusSubscriberImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity() {

    private val eventBus: RxEventBus = RxEventBusImpl(AndroidSchedulers.mainThread())
    private val eventBusSubscriber: RxEventBusSubscriber = RxEventBusSubscriberImpl(eventBus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventBusSubscriber.subscribeOnEvent(TestEvent::class.java, Consumer {
            println(it)
        })
        eventBus.post(TestEvent())
        eventBus.post(TestEvent())
    }

}
