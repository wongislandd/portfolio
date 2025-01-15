import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        AppModule.kt.initializeKoin(context: nil)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}