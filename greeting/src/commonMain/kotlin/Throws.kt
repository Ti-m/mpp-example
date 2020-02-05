package org.greeting

//Allows @Throws Annotation in common
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
expect annotation class Throws(vararg val exceptionClasses: kotlin.reflect.KClass<out kotlin.Throwable>)