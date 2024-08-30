# Dine API

Dine API is a RESTful API designed for comprehensive restaurant management. Implemented with Kotlin and Ktor, it uses PostgreSQL as the database to support various aspects of restaurant operations. It provides CRUD operations to manage a wide range of restaurant-related data.

## Technologies Used
- **Kotlin**: A modern, expressive, and safe programming language.
- **Ktor**: A framework for building asynchronous server applications.
- **PostgreSQL**: A powerful, open-source relational database management system.

## Features
- **CRUD Operations**: Create, read, update, and delete records for various restaurant entities.
- **Dynamic Configuration**: Configures the application using settings from a `.env` file.

## Installation and Setup
1. **Clone the Repository:**

    ```bash
    git clone https://github.com/cawtoz/dine-api.git
    cd dine-api
    ```

2. **Configure the `.env` File:**

    Copy the content of `.env.example` to a new file named `.env` in the root of the project:
    ```bash
    cp .env.example .env
    ```
    Then, open the `.env` file and update the placeholder values with your PostgreSQL database configuration details:

    ```env
    DATABASE_URL=jdbc:postgresql://localhost:5432/dine
    DATABASE_DRIVER=org.postgresql.Driver
    DATABASE_USER=your_db_user
    DATABASE_PASSWORD=your_db_password
    SERVER_PORT=8080
    ```

3. **Build and Run the Project:**

    Ensure you have [Gradle](https://gradle.org/install/) installed. Then, execute:
    ```bash
    ./gradlew run
    ```
    The server will start on the port specified in the `.env` file.

## Contributing

Contributions are welcome! Since the repository is not publicly writable, please follow these steps to suggest changes:

1. Fork this repository. For detailed instructions, refer to the [GitHub documentation on forking a repository](https://docs.github.com/es/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo#forking-a-repository).

2. Clone your forked repository to your local machine:
     ```bash
     git clone https://github.com/your-username/dine-api.git
     cd dine-api
     ```

3. Create a new branch for your changes:
     ```bash
     git checkout -b your-username
     ```

4. Implement your changes or new features. Be sure to test your code thoroughly.

5. Commit your changes with a descriptive message:
     ```bash
     git add .
     git commit -m "Add feature or fix description"
     ```

6. Push your changes to your forked repository:
     ```bash
     git push origin your-username
     ```

7. Create a pull request. For detailed instructions, refer to the [GitHub documentation on creating a pull request](https://docs.github.com/es/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request).

8. I will review your pull request and provide feedback or request changes. Be responsive to review comments and make any necessary adjustments.

By following these steps, you help improve the Dine API and contribute to its development!

