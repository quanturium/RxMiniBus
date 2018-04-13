package com.quanturium.rxminibus

import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RxMiniBusTest {

    @Test
    fun `events are sent by the bus`() {

        //given
        val bus = RxMiniBusImpl(Schedulers.newThread())
        val subscriber = RxMiniBusSubscriberImpl(bus)
        val event = CustomEvent()
        val atomicBoolean = AtomicBoolean(false)

        //when
        subscriber.subscribeOnEvent(CustomEvent::class.java) { it: CustomEvent ->
            assertEquals(event, it, "wrong value received")
            atomicBoolean.set(true)
        }
        bus.post(event)

        //then
        assertTrue(atomicBoolean.get(), "Event is received")
    }

    @Test
    fun `events are received on the correct thread`() {

        //given
        val bus = RxMiniBusImpl(Schedulers.newThread())
        val subscriber = RxMiniBusSubscriberImpl(bus)
        val event = CustomEvent()
        val atomicBoolean = AtomicBoolean(false)

        //when
        subscriber.subscribeOnEventMainThread(CustomEvent::class.java) { it: CustomEvent ->
            assertEquals(event, it, "Wrong value received")
            assertTrue(Thread.currentThread().name.startsWith("RxNewThreadScheduler"), "Not the right thread")
            atomicBoolean.set(true)
        }
        bus.post(event)
        Thread.sleep(1000)

        //then
        assertTrue(atomicBoolean.get(), "Event is received")
    }

    class CustomEvent
}