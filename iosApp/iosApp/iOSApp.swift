import SwiftUI

@main
struct iOSApp: App {

    init() {
        AppModuleKt.initializeKoin(context: nil)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}