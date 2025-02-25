📋 To-Do List with Spring Boot

This is a REST API project for managing tasks (To-Do List) using Spring Boot and JPA with a MySQL or H2 database.

🚀 Features

Full CRUD operations for tasks (Create, Read, Update, Delete).

Uses Spring Boot, Spring Data JPA, and Spring Web.

Configurable database with MySQL or H2 (for testing).

Structured based on layered architecture (Domain, Application, Infrastructure).

🛠️ Technologies Used

Java 17

Spring Boot 3

Spring Data JPA

MySQL / H2 Database

Lombok (to reduce boilerplate code)

📦 Installation

1️⃣ Clone the Repository

 git clone https://github.com/your-username/todolist-springboot.git
 cd todolist-springboot

2️⃣ Configure the Database

If using MySQL, edit src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/todolist_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

If you prefer using H2 (in-memory database for testing):

spring.datasource.url=jdbc:h2:mem:todolist_db
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

3️⃣ Run the Application

mvn spring-boot:run

The API will be available at: http://localhost:8080/api/tasks

📌 API Endpoints

Method

Endpoint

Description

GET

/api/tasks

Retrieve all tasks

GET

/api/tasks/{id}

Retrieve a task by ID

POST

/api/tasks

Create a new task

PUT

/api/tasks/{id}

Update a task

DELETE

/api/tasks/{id}

Delete a task

📤 Example POST Request
