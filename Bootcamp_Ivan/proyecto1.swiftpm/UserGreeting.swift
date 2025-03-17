//
//  UserGreeting.swift
//  proyecto1
//
//  Created by bootcamp on 2025-02-18.
//


import SwiftUI

// Crear una clase que maneje el texto
class UserGreeting: ObservableObject {
    @Published var greetingText: String
    
    init(userName: String) {
        self.greetingText = "Hola, \(userName)!"
    }
}

struct ContentView: View {
    // Usamos @StateObject para crear y mantener el objeto UserGreeting
    @StateObject private var userGreeting = UserGreeting(userName: "Usuario")
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            
            // Mostrar el saludo usando la propiedad greetingText de la clase
            Text(userGreeting.greetingText)
                .font(.title)
                .padding()
        }
    }
}

@main
struct MyApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()  // Asegúrate de que el ContentView está dentro de la ventana principal
        }
    }
}


