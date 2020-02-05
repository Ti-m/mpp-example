package org.greeting

actual fun log(text: String) {
    println(text)
}