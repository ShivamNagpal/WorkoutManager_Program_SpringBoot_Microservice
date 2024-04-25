package com.nagpal.shivam.workout.manager.utils

import org.slf4j.MDC
import java.io.Closeable

object Slf4jUtils {
    class MDCMultiCloseable(private val keys: Set<String>) : Closeable {
        override fun close() {
            keys.forEach { MDC.remove(it) }
        }
    }

    fun mdcPut(key: String, value: String): MDCMultiCloseable {
        MDC.put(key, value)
        return MDCMultiCloseable(setOf(key))
    }

    fun mdcPutMulti(entryMap: Map<String, String>): MDCMultiCloseable {
        entryMap.forEach {
            MDC.put(it.key, it.value)
        }
        return MDCMultiCloseable(entryMap.keys)
    }
}
