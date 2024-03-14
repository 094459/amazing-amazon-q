## Spring Boot GitHub API Demo

This project demonstrates how to build a RESTful API for retrieving GitHub repository data using Spring Boot.

### Overview

The MyRestController class contains an endpoint that calls the GitHub API to fetch the last 10 updated repositories for a given organization. It maps the response to a list of Repository objects and returns them.

The Repository class models a GitHub repo with name and URL fields. A RestTemplate is used to make the HTTP request and map the JSON response.

### Setup

1. Clone the repository
2. Install Java and Maven
3. Navigate to the project directory
4. Run `mvn spring-boot:run` to start the application

### Usage

The API can be accessed at http://localhost:8080/lastUpdatedRepos.

This will return a JSON array of the last 10 updated repositories.

### Improvements

Potential areas for improvement include:

* Adding authentication to call private GitHub APIs
* Caching repository data for better performance
* Adding error handling for API failures
* Adding API documentation with Swagger
* Containerizing the app for deployment