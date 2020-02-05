//
//  SomeInterfaceImplementation.swift
//  iosApp
//
//  Created by Tim Bröhan on 05.02.20.
//

import Foundation
import greeting

class SomeInterfaceImplementationInSwift : SomeKotlinInterface {
    func functionWithCallback(callback: @escaping (KotlinInt, String) -> Void) throws { //This throws produces a crash Thread 1: EXC_BAD_ACCESS (code=1, address=0x10)
        callback(0, "Foo")
    }
}

/*
 Thread 1 Queue : com.apple.main-thread (serial)
 #0    0x0000000106c215ff in thunk for @escaping @callee_unowned @convention(block) (@unowned GreetingInt, @unowned NSString) -> () ()
 #1    0x0000000106c213bc in SomeInterfaceImplementationInSwift.functionWithCallback(callback:) at /Users/timb/projects/mpp-example-exceptions/iosApp/iosApp/SomeInterfaceImplementation.swift:13
 #2    0x0000000106c214c5 in @objc SomeInterfaceImplementationInSwift.functionWithCallback(callback:) ()
 #3    0x0000000106f9e7d8 in ___lldb_unnamed_symbol3$$greeting ()
 #4    0x0000000106f992be in kfun:org.greeting.SomeKotlinClass.someFunctionImplementedInKotlin() at /Users/timb/projects/mpp-example-exceptions/greeting/src/commonMain/kotlin/SomeKotlinClass.kt:6
 #5    0x0000000106f9e6dd in objc2kotlin.65 at /Users/timb/projects/mpp-example-exceptions/greeting/src/iosMain/kotlin/<compiler-generated>:1
 #6    0x0000000106c20510 in ViewController.viewDidLoad() at /Users/timb/projects/mpp-example-exceptions/iosApp/iosApp/ViewController.swift:21

 */
