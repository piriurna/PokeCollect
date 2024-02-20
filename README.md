# PokeCollect

PokeCollect is a mobile app built with Kotlin Multiplatform Mobile (KMM) that offers a captivating Pokemon game experience. Users can engage in battles with random wild Pokemon, collect them in their Pokedex, and explore the vast world of Pokemon. The app leverages the PokeApi public API to provide real-time data and employs clean architecture principles for a scalable and maintainable codebase.

## Features

- **Kotlin Multiplatform Mobile (KMM):** Utilizes KMM to share code between Android and iOS platforms, allowing for efficient development and maintenance.

- **Dependency Injection with Koin:** Koin is used as the dependency injector, providing a lightweight and easy-to-use framework for managing dependencies in the application.

- **Compose for UI (Android):** The app's user interface is built using Jetpack Compose, a modern Android UI toolkit that simplifies UI development with a declarative syntax.

- **Compose Navigation:** Implements Compose Navigation to handle navigation within the Android app, ensuring a smooth and intuitive user experience.

- **SQLDelight for Database:** The app uses SQLDelight for database management, providing a type-safe way to interact with the database and ensuring data integrity.

- **Clean Architecture:** Adheres to clean architecture principles, separating concerns into layers such as presentation, domain, and data to achieve maintainability and testability.

- **MVVM (Model-View-ViewModel):** Follows the MVVM architectural pattern for the Android module, promoting separation of concerns and making the codebase more modular.

- **Pokemon Game Features:**
  - **Battling:** Engage in battles with random wild Pokemon, showcasing a turn-based combat system.
  - **Collecting:** Build your Pokedex by capturing and collecting a variety of Pokemon species.

- **UseCases for Domain Communication:** Implements UseCases to facilitate communication between the domain layer and the ViewModel, ensuring a clear and organized flow of data and business logic.
