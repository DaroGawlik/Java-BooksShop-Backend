# Books Shop Project: Back-End Development

Welcome to the Java Spring Boot back-end repository of My Project. 
This repository contains the server-side code and RESTful APIs built using the Spring Boot framework, with a MySQL database back-end.
For the front-end implementation, please visit the [Angular-BooksShop repository](https://github.com/DaroGawlik/Angular-BooksShop). 

## Project Overview

- **RESTful APIs:** The back-end exposes a set of RESTful APIs to facilitate communication between the Angular front-end and the server.

- **Data Storage:** Using a MySQL database to securely store book details, user information, and order history.

- **Security:** Ensuring a secure shopping environment through advanced measures, including token-based user authentication and authorization.

- **Spring Data JPA:** Utilizing Spring Data JPA for simplified database operations, making the backend efficient and scalable.

In a nutshell: the server-side of my application encapsulates all the essential components necessary for a web shop.

## Instructions

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- Java Development Kit (JDK): [Download and Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- MySQL: [Download and Install MySQL](https://dev.mysql.com/downloads/)

### Database Configuration

1. Fill in the `application.yml` file in the `src/main/resources` directory with your database connection details:

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://your_database_host:your_database_port/your_database_name
       username: your_database_username
       password: your_database_password

### Running the Application

1. Clone this repository:
   ```
   git clone https://github.com/DaroGawlik/Java-BooksShop-Backend.git
   ```
2. Navigate to the project directory:
   ```
   cd Java-BooksShop-Backend
   ```
3. Run the application using the Maven Wrapper:
   ```
   ./mvnw spring-boot:run
   ```
