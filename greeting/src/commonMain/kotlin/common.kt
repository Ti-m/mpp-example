package org.greeting

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

expect class Platform() {
    val platform: String
}

class Greeting {
    fun greeting(): String = "Hello, ${Platform().platform}"
}
expect fun log(text: String)
expect val MainDispatcher: CoroutineDispatcher

class Test {
    fun doSomething() {
        GlobalScope.launch(MainDispatcher) {
            log("1000")
        }
    }
}