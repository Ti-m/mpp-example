package org.greeting

class SomeKotlinClass(private val dependencieImplementedInSwift: SomeKotlinInterface) {

    fun someFunctionImplementedInKotlin() {
        dependencieImplementedInSwift.functionWithCallback(
            callback = { status, content ->
                log(status.toString())
                log(content)
            }
        )
    }
}