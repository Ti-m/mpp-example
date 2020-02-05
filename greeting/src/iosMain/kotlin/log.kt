package org.greeting

import platform.Foundation.NSLog

actual fun log(text: String) {
    NSLog(text)
}
