# Rental Product - App Service 😊

This is an **OAuth2 Fullstack Example** built with **Keycloak**, **Angular**, and **Spring Boot**. This project demonstrates how to implement OAuth2 authentication and authorization using Keycloak as the Identity Provider.

## Project Structure

```plaintext
/project-springboot-ng-rentalProduct-app-service
├── rentalProduct        # Spring Boot backend
│   └── (files...)
└── keycloak       # Keycloak configuration
    └── (files...)
```

## Technologies Used

- **Angular 17**: Frontend framework for building dynamic web applications.
- **Spring Boot**: Java framework for building backend services.
- **Keycloak**: Open-source Identity and Access Management for modern applications.
- **OAuth2**: Protocol for authorization.
- **PostgreSQL**: Database for storing user data (configured with Keycloak).

## Getting Started

Follow these instructions to set up your project locally.

### Prerequisites

- Docker
- Maven
- Java 21

### Setup Keycloak

1. Navigate to the **keycloak** folder.
2. Modify the `Dockerfile` or `docker-compose.yml` (e.g., adjust the `postgres_data` volume) as needed.
3. Start up PostgreSQL and Keycloak via:
   ```bash
   docker-compose up --build
   ```
4. Import the realm configuration using the `dev-test-realm-realm.json` file. This includes clients, users, roles, etc. 
5. Default Realm: **dev-test-realm**
   - Username: **testuser-1**
   - Password: **testuser1**
6. You can create and configure your own realm using the Keycloak admin console.
7. Check if the Keycloak admin console is reachable at: [http://localhost:8180/](http://localhost:8180/).
<!--
### Angular Web Application

1. The Angular web application is located in the **webapp** folder.
2. It uses **angular-oauth2-oidc** for OAuth2 integration.
3. The `main.ts` file bootstraps the web application by providing the HTTP client and `OAuthService`, along with the necessary configuration for silent token refresh and loading the discovery document.
4. The `AppComponent` demonstrates logout functionality and calling a protected API with the access token.
-->

### Spring Boot Backend

1. The Spring Boot backend is located in the **backend** folder.
2. Ensure you have Maven and Java 21 installed.
3. The `SecurityConfig` class configures the security filter chain:
   - Enables CORS.
   - Ensures all requests are authenticated.
   - Configures the application as an OAuth2 resource server (verifying access tokens via the JWT issuer).
   - Uses a custom JWT converter to extract relevant data from the JWT.
4. The `application.properties` file is configured with the JWT issuer, pointing to the locally running Keycloak.
5. The `CustomJwt` class contains all relevant information extracted from the JWT bearer token.
6. The `HelloController` has a basic GET endpoint that returns a message, but only for authorized users with the authority **ROLE_fullstack-developer**. CORS is configured to work with the locally running Angular web application.

## Features

- **OAuth2 Authentication**: Secure user authentication using Keycloak.
- **Angular Frontend**: Dynamic and responsive user interface built with Angular.
- **Spring Boot Backend**: RESTful APIs with secure access.
- **CORS Configuration**: Ensures frontend and backend can communicate securely.
- **Role-Based Access Control**: Access control based on user roles.

### Contributing

Contributions are welcome! Please follow these steps:

    Fork the repository.
    Create a new branch (git checkout -b feature/YourFeature).
    Make your changes and commit them (git commit -m 'Add some feature').
    Push to the branch (git push origin feature/YourFeature).
    Open a pull request.

### License

This project is licensed under the Tor Production 😊 License.