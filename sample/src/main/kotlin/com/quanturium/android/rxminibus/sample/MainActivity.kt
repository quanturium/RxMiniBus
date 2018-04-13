package com.quanturium.android.rxminibus.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.quanturium.android.rxminibus.RxMiniBus
import com.quanturium.android.rxminibus.RxMiniBusImpl
import com.quanturium.android.rxminibus.RxMiniBusSubscriber
import com.quanturium.android.rxminibus.RxMiniBusSubscriberImpl
import com.quanturium.rxminibus.sample.R
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private val miniBus: RxMiniBus = RxMiniBusImpl(AndroidSchedulers.mainThread())
    private val miniBusSubscriber: RxMiniBusSubscriber = RxMiniBusSubscriberImpl(miniBus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener({
            miniBus.post(TestEvent())
        })

        miniBusSubscriber.subscribeOnEvent(TestEvent::class.java, {
            Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show()
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        miniBusSubscriber.unsubscribe()
    }
}
