package org.greeting

interface SomeKotlinInterface {
    @Throws
    fun functionWithCallback(callback: ((status: Int, content: String) -> Unit))

}