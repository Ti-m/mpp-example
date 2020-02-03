//
//  ViewController.swift
//  iosApp
//
//  Created by jetbrains on 12/04/2018.
//  Copyright © 2018 JetBrains. All rights reserved.
//

import UIKit
import NetworkExtension
import greeting

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        let product = Factory().create(config: ["user": "JetBrains"])
        label.text = product.description
        
        //creates vpn config if none available
        NETunnelProviderManager.loadAllFromPreferences { (profiles, error) in
            if profiles!.count == 0 {
                print("No profiles")
                let newProfile = NETunnelProviderManager()
                newProfile.localizedDescription = "Test Profile"
                newProfile.isEnabled = true
                let tunnelProviderProtocol = NETunnelProviderProtocol()
                tunnelProviderProtocol.providerBundleIdentifier = "<your appid of the extension>"//change to your appid of the extension
                tunnelProviderProtocol.serverAddress = "DEFAULT"
                newProfile.protocolConfiguration = tunnelProviderProtocol
                newProfile.saveToPreferences(completionHandler: nil)
            }
        }
        
        
        let test = Test()
        test.doSomething()//This works
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBOutlet weak var label: UILabel!
}

