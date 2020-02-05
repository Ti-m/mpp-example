package org.greeting

class AnotherKotlinClass(private val dependencieImplementedInSwift: AnotherKotlinInterface) {

    fun someFunctionImplementedInKotlin() {
        dependencieImplementedInSwift.function()
    }
}