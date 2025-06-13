# MCP Server

This is a Spring Boot application that provides an API to query the Estimated Time of Arrival (ETA) for freight documents.

## Prerequisites

- Java Development Kit (JDK) 1.8 or higher
- Apache Maven 3.2+

## Building the Application

To build the application, navigate to the project root directory and run the following Maven command:

\`\`\`bash
mvn clean install
\`\`\`

This will compile the code, run tests, and package the application into a JAR file in the \`target\` directory.

## Running the Application

Once the application is built, you can run it using the following command:

\`\`\`bash
java -jar target/mcp-server-0.0.1-SNAPSHOT.jar
\`\`\`

The server will start on the default port (usually 8080).

## API Endpoints

### Get Estimated Time of Arrival (ETA)

Retrieves the ETA for a specific freight document.

- **URL:** \`/api/freight/{documentNumber}/eta\`
- **Method:** \`GET\`
- **Path Parameters:**
  - \`documentNumber\`: (String) The unique identifier of the freight document.
- **Success Response:**
  - **Code:** 200 OK
  - **Content:** \`"YYYY-MM-DDTHH:mm:ss"\` (e.g., \`"2024-07-28T14:30:00"\`)
- **Error Response:**
  - **Code:** 404 Not Found
  - **Content:**
    \`\`\`json
    {
        "status": "NOT_FOUND",
        "message": "Freight document with number '{documentNumber}' not found.",
        "error": "Document not found"
    }
    \`\`\`

## H2 Database Console

For development and testing purposes, an in-memory H2 database is used. You can access its console when the application is running:

- **URL:** \`http://localhost:8080/h2-console\`
- **JDBC URL:** \`jdbc:h2:mem:mcpdb\`
- **Username:** \`sa\`
- **Password:** (leave blank)

This allows you to inspect the database schema and data.

## Technologies Used

- Spring Boot 2.1.18.RELEASE (Compatible with Java 8)
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Java 1.8
