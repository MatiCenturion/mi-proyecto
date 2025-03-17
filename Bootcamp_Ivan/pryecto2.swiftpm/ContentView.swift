import SwiftUI


class UserGreeting: ObservableObject {
    @Published var greetingText: String
    
    init(userName: String) {
        self.greetingText = "Hola, \(userName)!"
    }
}

struct ContentView: View {
    
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
