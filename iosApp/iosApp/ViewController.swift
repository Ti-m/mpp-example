//
//  ViewController.swift
//  iosApp
//
//  Created by jetbrains on 12/04/2018.
//  Copyright © 2018 JetBrains. All rights reserved.
//

import UIKit
import greeting

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        let product = Factory().create(config: ["user": "JetBrains"])
        label.text = product.description
        
        //This works
        let anotherKotlinInterfaceImplementationInSwift = AnotherInterfaceImplementationInSwift()
        let anotherKotlinClass = AnotherKotlinClass(dependencieImplementedInSwift: anotherKotlinInterfaceImplementationInSwift)
        anotherKotlinClass.someFunctionImplementedInKotlin()
        
        //This crashes
        let someKotlinInterfaceImplementationInSwift = SomeInterfaceImplementationInSwift()
        let someKotlinClass = SomeKotlinClass(dependencieImplementedInSwift: someKotlinInterfaceImplementationInSwift)
        someKotlinClass.someFunctionImplementedInKotlin()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBOutlet weak var label: UILabel!
}

