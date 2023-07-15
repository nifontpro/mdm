//
//  LoginScreen.swift
//  iosApp
//
//  Created by Nifont on 14.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct LoginScreen: View {
    private let loginViewModel = OAuthViewModel()
    
    var body: some View {
        ObservingView(statePublisher:
            statePublisher(loginViewModel.viewStates())) {viewState in
            LoginView(viewState: viewState) {event in
                loginViewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}
