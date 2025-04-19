# Hotel Migration POC

This Spring Boot project demonstrates migrating hotel data from a legacy DB2-style system (`ocu_hotels`) to a modern Azure SQL-style system (`individual_hotels`) using the **Chain of Responsibility** design pattern.

## Tech Stack
- Java 17
- Spring Boot 3.2.x
- H2 In-Memory DB (simulates both DB2 and Azure SQL)
- REST API with migration logic

## Run Instructions
1. Clone or download the project
2. Run `HotelMigrationApplication`
3. Use Postman or CURL with the included collection to test

## Preloaded Data
- `H1001` is in **Azure** (individual_hotels)
- `H2002` is in **DB2** (ocu_hotels)

## Endpoints
- `GET /hotels/{id}` - Retrieves from Azure, if not found checks DB2 and migrates
- `PUT /hotels` - Updates or migrates from DB2 if needed
- `DELETE /hotels/{id}` - Deletes from Azure or DB2