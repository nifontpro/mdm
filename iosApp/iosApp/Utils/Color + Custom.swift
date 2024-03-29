//
//  Color + Custom.swift
//  iosApp
//
//  Created by Nifont on 14.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension Color {
    static let textPrimary = Color("primaryTextColor")
    static let textSecondary = Color("secondaryTextColor")
    static let backgroundPrimary = Color("backgroundPrimary")
    static let backgroundSecondary = Color("secondaryBackgroundColor")
    static let textAction = Color("actionTextColor")
    static let tintColor = Color("tintColor")
    
    init(hex: UInt, alpha: Double = 1) {
            self.init(
                .sRGB,
                red: Double((hex >> 16) & 0xff) / 255,
                green: Double((hex >> 08) & 0xff) / 255,
                blue: Double((hex >> 00) & 0xff) / 255,
                opacity: alpha)
        }
}
