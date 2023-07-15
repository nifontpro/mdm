//
//  LoginView.swift
//  iosApp
//
//  Created by Nifont on 14.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct LoginView: View {
    
    let viewState: OAuthViewState
            let eventHandler: (OAuthEvent) -> Void
    
    var body: some View {
        VStack {
            VStack {
                Spacer().frame(height: 36)
                Text("Login Now")
                    .foregroundColor(.textPrimary)
                    .fontWeight(.bold)
                    .font(.system(size: 24))
                
                Text("Welcome to Medalist")
                    .foregroundColor(.textPrimary.opacity(0.5))
                    .fixedSize(horizontal: false, vertical: true) // Для многострочности
                    .multilineTextAlignment(.center)
                    .padding(EdgeInsets(top: 16, leading: 30, bottom: 0, trailing: 30))


                Spacer().frame(height: 50)
                
                CommonTextField(hint: "Login", enabled: true, isSecure: false) { newValue in
                    eventHandler(.EmailChanged(value: newValue))
                }
                
                Spacer().frame(height: 24)
                
                CommonTextField(hint: "Password", enabled: true, isSecure: true) { newValue in
                    
                }
                
            }

            Spacer()

            HStack {
                
            }
        }
        
    }
}
