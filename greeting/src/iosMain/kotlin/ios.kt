package org.greeting

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSLog
import platform.darwin.*
import platform.posix.uname
import platform.posix.utsname
import kotlin.coroutines.CoroutineContext

actual class Platform actual constructor() {
    actual val platform: String = "iOS"
}

actual class Product(actual val user: String) {
    val model: String = memScoped {
        val systemInfo = alloc<utsname>()
        uname(systemInfo.ptr)
        systemInfo.machine.toKString()
    }

    fun iosSpecificOperation() {
        println("I am $model")
    }

    override fun toString() = "iOS product of $user for $model"
}

actual object Factory {
    actual fun create(config: Map<String, String>) =
        Product(config["user"]!!)

    actual val platform: String = "ios"
}

actual val MainDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())
internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}

actual fun log(text: String) {
    NSLog("vpn-service $text")
}